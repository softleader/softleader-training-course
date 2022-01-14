package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

	final HelloService service;

//	public HelloController(HelloService service) {
//		this.service = service;
//	}

	@GetMapping("/hello/{name}")
	String hello(@PathVariable("name") String name) {
		return service.hello(name) + "!";
	}

	// 新增學生 API
	// POST '/students'
	// body: Student { String id, String name, }
	// response Student w/ id

	@PostMapping("/students")
	Student createStudent(@RequestBody Student student) {
		return service.createStudent(student);
	}

}
