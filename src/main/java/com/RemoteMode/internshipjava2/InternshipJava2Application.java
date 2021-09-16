package com.RemoteMode.internshipjava2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;

@EnableFeignClients
@SpringBootApplication
public class InternshipJava2Application {

	public static void main(String[] args) {
		SpringApplication.run(InternshipJava2Application.class, args);
	}

//	@GetMapping("/hello")
//	public String hello() {
//		return String.format("Hello world");
//	}
//	athena
	//5432

}
