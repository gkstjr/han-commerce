package com.toy.hancommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class HancommerceApplicationTests {

//	@Test
	void contextLoads() {

	}
	@Test
	void hanseok() {
		if(1 == 1) {
			throw new IllegalArgumentException();
		}
	}

}
