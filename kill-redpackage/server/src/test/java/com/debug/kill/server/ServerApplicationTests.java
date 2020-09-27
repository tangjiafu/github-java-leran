package com.debug.kill.server;

import com.debug.kill.server.util.RedPackageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {

	@Test
	void contextLoads(){
	}

	@Test
	void test1(){
		RedPackageUtil.divideRedPackage(100, 10).forEach(System.out::println);
	}
}
