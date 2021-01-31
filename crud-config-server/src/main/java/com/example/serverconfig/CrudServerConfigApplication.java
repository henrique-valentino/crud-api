package com.example.serverconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CrudServerConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudServerConfigApplication.class, args);
	}

}
