package tw.com.softleader.demoweb;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class DemoWebJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebJpaApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> builder
			.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
			.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
	}

}
