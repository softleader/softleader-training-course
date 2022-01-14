package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// HelloController
	// 	GET '/hello/{name}'
	// return helloService.hello(name)

	// HelloService
	// String hello (String name)
	//	 return hello + {name}

}
