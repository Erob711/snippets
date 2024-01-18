package com.snippets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class SnippetsApplicationTests {

//	@Test
//	void contextLoads() {
//	}


	@Test
	public void checkSpringVersion() {
		assertEquals(SpringVersion.getVersion(), SpringVersion.getVersion());
	}
}
