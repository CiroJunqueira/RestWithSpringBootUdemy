package com.ciro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class RestWithSpringbootUdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringbootUdemyApplication.class, args);
		
		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result= bCryptPasswordEncoder.encode("admin123");
		System.out.println("my hash " + result);*/
	}

}
