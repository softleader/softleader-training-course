package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc// (print = MockMvcPrint.DEFAULT)
class HelloControllerTest {

	@Autowired
	MockMvc mockMvc;

	//	@MockBean
	@SpyBean
	HelloService service;

	@Autowired
	ObjectMapper mapper;

	@Test
	void hello() throws Exception {
		String name = "matt";

		Mockito.when(service.hello(Mockito.anyString())).thenReturn(name);

		var content = mockMvc.perform(
						MockMvcRequestBuilders.get("/hello/{name}", name)
				).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(name + "!"))
				.andReturn().getResponse().getContentAsString();

		Assertions.assertThat(content)
				.isNotEmpty()
				.endsWith("!");
	}

	@Test
	void createStudent() throws Exception {
		var student = new Student();
		student.setName("hello");

		var content = mockMvc.perform(
						MockMvcRequestBuilders.post("/students")
								.content(mapper.writeValueAsString(student))
								.contentType(MediaType.APPLICATION_JSON)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("hello")))
				.andReturn().getResponse().getContentAsString();

		var created = mapper.readValue(content, Student.class);
		Assertions.assertThat(created)
				.isNotNull()
				.hasNoNullFieldsOrProperties();
//				.hasAllNullFieldsOrProperties();

		Mockito.verify(service, Mockito.never()).hello(Mockito.anyString());
	}

}
