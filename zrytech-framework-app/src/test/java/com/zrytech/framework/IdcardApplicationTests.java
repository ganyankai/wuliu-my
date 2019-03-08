package com.zrytech.framework;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@Slf4j*/
public class IdcardApplicationTests {

	@Test
	public void contextLoads() {
	/*	Idcard idcard=new Idcard();
		idcard.setIdcard("4222361254789955212");
		idcard.setName("张三");*/
//		IdcardCheck.checkByThirdApi(idcard);
      Double d=123.123d;

		BigDecimal bigDecimal=new BigDecimal(d);
		System.out.println(bigDecimal);
	}

}
