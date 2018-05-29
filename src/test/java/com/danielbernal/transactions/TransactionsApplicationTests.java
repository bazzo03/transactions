package com.danielbernal.transactions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.danielbernal.transactions.controller.StatisticControllerTest;
import com.danielbernal.transactions.controller.TransactionControllerTest;
import com.danielbernal.transactions.service.StatisticServiceImplTest;
import com.danielbernal.transactions.service.TransactionServiceImplTest;
import com.danielbernal.transactions.util.DateUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TransactionControllerTest.class, StatisticControllerTest.class, StatisticServiceImplTest.class,
		TransactionServiceImplTest.class, DateUtilTest.class })
public class TransactionsApplicationTests {
}
