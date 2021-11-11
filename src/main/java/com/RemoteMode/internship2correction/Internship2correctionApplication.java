package com.RemoteMode.internship2correction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class Internship2correctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Internship2correctionApplication.class, args);
	}

}
