 
package cn.yesway.mapshifting.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *  发布服务启动类
 *
 * @version : Ver 1.0
 * @author	: <a href="mailto:358911056@qq.com">hubo</a>
 * @date	: 2015-8-4 下午7:29:37 
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class PublishServer {

	public static void main(String[] args) {
		SpringApplication.run(PublishServer.class, args);
	}
}
