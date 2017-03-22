package cn.yesway.mapshifting.extend;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
@Order(3)
public class WebLogAspect {
	private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	 ThreadLocal<Long> timeThreadLocal = new ThreadLocal<Long>();
	
	@Pointcut("execution(public * cn.yesway.mapshifting.controller.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		timeThreadLocal.set(System.currentTimeMillis());
		
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return;
		}
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("ip:{},url-{}",getIpAddr(request),request.getRequestURL().toString());
		String qualifiedName = joinPoint.getSignature().getDeclaringTypeName().concat(".")
				+ joinPoint.getSignature().getName().concat("()");
		logger.info("{},args:{}",qualifiedName,JSON.toJSONString(joinPoint.getArgs()));
	}
	
	/*@Around("webLog()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		
		if(method.getAnnotation(ResponseBody.class) != null){
			logger.info("返回结果:{}",JSON.toJSONString(joinPoint.proceed()));
		}
	}*/
	
	@AfterReturning("webLog()")
	public void doAfterReturning(JoinPoint joinPoint) throws Throwable {
		logger.info("time: {}", (System.currentTimeMillis() - timeThreadLocal.get()) + "ms");
	}
	
	/**
	 * 获取当前网络ip
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request){
		String ipAddress = request.getHeader("x-forwarded-for");
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
					//根据网卡取本机配置的IP
					InetAddress inet=null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress= inet.getHostAddress();
				}
			}
			//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if(ipAddress!=null && ipAddress.length()>15){
				if(ipAddress.indexOf(",")>0){
					ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
				}
			}
			return ipAddress; 
	}

}