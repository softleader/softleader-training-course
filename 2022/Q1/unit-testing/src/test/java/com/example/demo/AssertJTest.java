package com.example.demo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//@TestMethodOrder(MethodOrderer.DisplayName.class)
class AssertJTest {

	@Disabled
	@Test
	@DisplayName("空的測試")
	void test() {
		Assertions.fail("...");
	}

	@Test
	void assertNumbers() {
		// arrange
		int number = 1;

		// act

		// assert
		Assertions.assertThat(number)
				.isPositive()
				.isNotNegative()
				.isGreaterThanOrEqualTo(0)
				.isIn(1, 2, 3);
	}

	@Test
	void assertStrings() {
		String string = "hello world";
		Assertions.assertThat(string)
				.isNotBlank()
				.isLowerCase()
				.isEqualToIgnoringCase("HELLO WORLD")
				.contains("hello");
	}

	@Test
	void assertBoolean() {
		boolean bool = true;
		Assertions.assertThat(bool)
				.isTrue();
	}

	@Test
	void assertLocalDate() {
		LocalDate date = LocalDate.now();
		Assertions.assertThat(date)
				.isToday()
				.isBetween(date.minusDays(1), date.plusDays(1))
				.isBeforeOrEqualTo(LocalDate.now());
	}

	@Test
	void assertOptional() {
		var opt = Optional.of("1");
		Assertions.assertThat(opt)
				.isPresent()
				.isNotEmpty()
				.get().isEqualTo("1");
	}

	@Disabled
	@Test
	void softAssertions() {
		try (var assertions = new AutoCloseableSoftAssertions()) {
			assertions.assertThat("hello").isUpperCase();
			assertions.assertThat(0).isPositive();
		}
	}

	@Test
	void assertObject() {
		HelloEntity hello = new HelloEntity("hello", "h");

		Assertions.assertThat(hello)
//				.extracting("name", "nickname")
				.extracting(HelloEntity::getName, HelloEntity::getNickname)
				.contains("hello", "h");
	}

	@Test
	void assertCollection() {
		var list = List.of(
				new HelloEntity("a", "a1"),
				new HelloEntity("b", "b1")
		);

		Assertions.assertThat(list)
				.isNotEmpty()
				.hasSize(2)
				.extracting(HelloEntity::getName, HelloEntity::getNickname)
				.contains(
						Assertions.tuple("a", "a1"),
						Assertions.tuple("b", "b1")
				);
	}

	@Test
	void assertMap() {
		var map = Map.of(
				"a", "a1",
				"b", "b1",
				"c", "c1"
		);

		Assertions.assertThat(map)
				.isNotEmpty()
				.hasSize(3)
				.containsKeys("a", "b", "c");

		Assertions.assertThat(map)
				.matches(actual -> {
							// value = key + "1"
//					boolean pass = true;
//					for (String key : actual.keySet()) {
//						final var value = actual.get(key);
//						if (pass && value.equals(key + "1")) {
//							pass = true;
//						} else {
//							pass = false;
//						}
//					}
							return actual.entrySet().stream()
									.allMatch(entry -> entry.getValue().equals(entry.getKey() + "1"));
						},
						"value equals to key + '1'"
				);
	}

	@Test
	void assertException() {
		Assertions.assertThatThrownBy(() -> {
			List.of(1, 2).get(2);
		}).isInstanceOf(IndexOutOfBoundsException.class);
	}

	@Test
	void assertExceptionType() {
		Assertions.assertThatExceptionOfType(IndexOutOfBoundsException.class)
				.isThrownBy(() -> List.of().get(1));
	}

	@Test
	void assertCommonExceptionType() {
		Assertions.assertThatNullPointerException()
				.isThrownBy(() -> {
					Object obj = null;
					obj.toString();
				});
	}

	@Test
	void assertCatchException() {
		var throwable = Assertions.catchThrowable(() -> {
			Object obj = null;
			obj.toString();
		});

		Assertions.assertThat(throwable)
				.isInstanceOf(NullPointerException.class);
	}

	@Test
	void assertRootCauseException() {
		var throwable = Assertions.catchThrowable(() -> {
					throw new IllegalStateException("hello",
							new IllegalArgumentException("world"));
				}
		);

		Assertions.assertThat(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("hello")
				.hasRootCauseExactlyInstanceOf(IllegalArgumentException.class)
				.hasRootCauseMessage("world");
	}


	@AllArgsConstructor
	@Data
	class HelloEntity {

		String name;
		String nickname;
	}

}
