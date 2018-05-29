package com.danielbernal.transactions.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.danielbernal.transactions.util.DateUtil;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest
public class DateUtilTest {
	
	@Test
	public void converToTimeStamp_shouldSuccess() {
		LocalDateTime date = LocalDateTime.now();
		Long dateLong = DateUtil.converToTimeStamp(date);
		Long expected = date.toEpochSecond(ZoneOffset.UTC) * 1000;
		Assert.assertEquals(expected, dateLong);
	}

}
