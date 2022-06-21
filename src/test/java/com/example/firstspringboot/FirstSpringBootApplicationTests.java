package com.example.firstspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FirstSpringBootApplicationTests {

	static String hello = "";
	String world = "";

	@BeforeAll
	public static void beforeAll(){
		System.out.println("beforeAll");
		hello = "Hello";
	}

	@BeforeEach
	public void beforeEach(){
		System.out.println("beforeEach");
		world = "World";
	}
	@Test
	void contextLoads() {
		Assertions.assertThat(true).isTrue();
		hello = "Hello1";
		System.out.println("Test #1");
	}

}
