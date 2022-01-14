package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloServiceTest {

	@Autowired
	HelloService service;

	@ParameterizedTest
	@ValueSource(strings = {"a", "b", "c"})
	void hello(String name) {
		// arrange

		// act
		var actual = service.hello(name);

		// assert
		Assertions.assertThat(actual)
				.isNotBlank()
				.isEqualTo("hello " + name);
	}

}
