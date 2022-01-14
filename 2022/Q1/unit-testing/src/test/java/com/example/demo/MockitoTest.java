package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class MockitoTest {

	@Test
	void mock() {
		// arrange
		var repository =
				Mockito.mock(PolicyRepository.class);
		Mockito.when(repository.count()).thenReturn(1l);

		// act
		var actual = repository.count();

		// assert
		Assertions.assertThat(actual)
				.isPositive();
	}

	@Test
	void mockInput() {
		var mock = Mockito.mock(PolicyRepository.class);
		Mockito.when(mock.countByName(Mockito.anyString()))
				.thenReturn(1l);
		Mockito.when(mock.countByName(Mockito.eq("hi")))
				.thenReturn(2l);

		var actual = mock.countByName("hello");
		Assertions.assertThat(actual)
				.isPositive()
				.isEqualTo(1l);

		var hi = mock.countByName("hi");
		Assertions.assertThat(hi).isEqualTo(2l);
	}

	@Test
	void mockThrow() {
		// arrange
		var mock = Mockito.mock(PolicyRepository.class);
		Mockito.when(mock.countByNameLike(Mockito.anyString()))
				.thenThrow(IllegalStateException.class);

		// act
		var throwable = Assertions
				.catchThrowable(() -> mock.countByNameLike("hi"));

		// assert
		Assertions.assertThat(throwable)
				.isInstanceOf(IllegalStateException.class);
	}

	@Test
	void mockVoidThrow() {
		var mock = Mockito.mock(PolicyRepository.class);
		Mockito.doThrow(IllegalStateException.class)
				.when(mock).print();

		Assertions.assertThatIllegalStateException()
				.isThrownBy(mock::print);
	}

	@Test
	void spy() {
		var spy = Mockito.spy(PolicyRepository.class);
		Mockito.doThrow(IllegalStateException.class)
				.when(spy).print();

		Assertions.assertThatIllegalStateException()
				.isThrownBy(spy::print);

		Assertions.assertThat(spy.count()).isEqualTo(-1l);
	}

	@Test
	void spyVerify() {
		var spy = Mockito.spy(PolicyRepository.class);

		// act
		spy.call(3);

		// assert
		Mockito.verify(spy, Mockito.times(3))
				.count();
		Mockito.verify(spy, Mockito.never()).print();
	}

	@Test
	void spyVerifyOrder() {
		var spy = Mockito.spy(PolicyRepository.class);
		var inOrder = Mockito.inOrder(spy);

		// act
		spy.call(10);

		inOrder.verify(spy).call(10);
		inOrder.verify(spy, Mockito.atLeastOnce()).count();
	}

	static class PolicyRepository {

		long count() {
			return -1l;
		}

		long countByName(String name) {
			return -1l;
		}

		long countByNameLike(String name) {
			return -1l;
		}

		void print() {
		}

		void call(int num) {
			for (int i = 0; i < num; i++) {
				count();
			}
		}
	}
}
