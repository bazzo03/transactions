package com.danielbernal.transactions.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.danielbernal.transactions.domain.TransactionDo;
import com.danielbernal.transactions.exception.OldTransactionException;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {

	@Autowired
	private TransactionServiceImpl transactionService;
	
	@Test(expected = OldTransactionException.class)
	public void createTransaction_oldTransaction_shouldFail() throws OldTransactionException {

		TransactionDo transactionDo = new TransactionDo(
				LocalDateTime.now().plusSeconds(-61L).toEpochSecond(ZoneOffset.UTC) * 1000, 1250L);
		transactionService.createTransaction(transactionDo);
	}
	
	@Test
	public void createTransaction_newTransaction_shouldSuccess() throws OldTransactionException {
		
		TransactionDo transactionDo = new TransactionDo(
				LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000, 1250L);
		transactionService.createTransaction(transactionDo);
	}

}
