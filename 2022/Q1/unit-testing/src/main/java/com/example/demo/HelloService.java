package com.example.demo;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class HelloService {

	String hello(String name) {
		return "hello " + name;
	}

	public Student createStudent(Student student) {
		// student.generateId();
		student.setId(generateId());
//		student.setName(hello(student.getName()));
		return student;
	}

	// @VisibleForTesting
	String generateId() {
		return UUID.randomUUID().toString();
	}
}
