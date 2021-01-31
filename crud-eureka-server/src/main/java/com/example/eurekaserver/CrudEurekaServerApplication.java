package com.example.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CrudEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudEurekaServerApplication.class, args);
	}

}
