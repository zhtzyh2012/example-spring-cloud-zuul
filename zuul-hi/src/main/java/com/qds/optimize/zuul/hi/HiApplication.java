package com.qds.optimize.zuul.hi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * hi服务的入口类
 *
 * @author neutron
 */
@EnableEurekaClient
@SpringBootApplication
public class HiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiApplication.class, args);
	}
}
