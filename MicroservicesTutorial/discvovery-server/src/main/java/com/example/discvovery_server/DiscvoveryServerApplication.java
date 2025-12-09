package com.example.discvovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscvoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscvoveryServerApplication.class, args);
	}

}
