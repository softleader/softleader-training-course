package com.example.demo;

import java.util.UUID;
import lombok.Data;

@Data
public class Student {
	String id;
	String name;

//	void generateId() {
//		this.id = UUID.randomUUID().toString();
//	}
}
