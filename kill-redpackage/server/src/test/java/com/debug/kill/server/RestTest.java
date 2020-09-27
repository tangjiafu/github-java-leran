package com.debug.kill.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RestTest {
	@Resource
	RestTemplate restTemplate;

	@Test
	void test(){

	}
}
