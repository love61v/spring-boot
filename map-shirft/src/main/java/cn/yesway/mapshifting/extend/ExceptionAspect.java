package cn.yesway.mapshifting.extend;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {
    private static Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    @Pointcut("@annotation(org.springframework.stereotype.Service)")
    public void serviceExceptionPoint() {
        //do nothing here
    };

    @AfterThrowing(pointcut = "serviceExceptionPoint()", throwing = "exception")
    public void logServiceException(Exception exception) {
        logger.info("service操作出现异常,异常信息如下：" + exception.getMessage(), exception);
    }
}
