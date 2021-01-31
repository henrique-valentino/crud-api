package com.example.gatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;


@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class CrudGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudGatewayZuulApplication.class, args);
	}

}
