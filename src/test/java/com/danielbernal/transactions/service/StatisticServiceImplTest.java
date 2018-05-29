package com.danielbernal.transactions.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.danielbernal.transactions.domain.StatisticDo;
import com.danielbernal.transactions.domain.TransactionDo;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticServiceImplTest {

	@Autowired
	private StatisticServiceImpl statisticService;

	@Test
	public void obtainStatistics_noTransactions_shouldSuccess() {

		StatisticDo actual = statisticService.obtainStatistics();
		StatisticDo expected = new StatisticDo(0, 0, 0, 0, 0);
		Assert.assertEquals(expected.getAvg(), actual.getAvg(), 0.001);
		Assert.assertEquals(expected.getCount(), actual.getCount());
		Assert.assertEquals(expected.getMax(), actual.getMax(), 0.001);
	}

	@Test
	public void obtainStatistics_withTransactions_shouldSuccess() {

		TransactionDo transactionDo = new TransactionDo(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000,
				1250L);
		TransactionDo transactionDo2 = new TransactionDo(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000,
				1250L);
		statisticService.addTransactionToStatistic(transactionDo);
		statisticService.addTransactionToStatistic(transactionDo2);

		StatisticDo actual = statisticService.obtainStatistics();
		StatisticDo expected = new StatisticDo(1250d * 2L, 1250d, 1250d, 1250d, 2);

		Assert.assertEquals(expected.getAvg(), actual.getAvg(), 0.001);
		Assert.assertEquals(expected.getCount(), actual.getCount());
		Assert.assertEquals(expected.getMax(), actual.getMax(), 0.001);
	}
}
