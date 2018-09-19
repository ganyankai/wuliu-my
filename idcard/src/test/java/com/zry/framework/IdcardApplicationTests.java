package com.zry.framework;

import com.zry.framework.domain.Idcard;
import com.zry.framework.service.IdcardCheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdcardApplicationTests {

	@Test
	public void contextLoads() {
		Idcard idcard=new Idcard();
		idcard.setIdcard("4222361254789955212");
		idcard.setName("张三");
//		IdcardCheck.checkByThirdApi(idcard);
	}

}
