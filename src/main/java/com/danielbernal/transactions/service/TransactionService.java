package com.danielbernal.transactions.service;

import com.danielbernal.transactions.domain.TransactionDo;
import com.danielbernal.transactions.exception.OldTransactionException;

/**
 * Service declaration of the operations the transaction can do
 * @author dbernalbazzani
 */
public interface TransactionService {
	
	/**
	 * Creates a transaction
	 * @param transactionDo to be created
	 * @throws OldTransactionException if the transaction is older than 60 seconds
	 */
	public void createTransaction (TransactionDo transactionDo) throws OldTransactionException;
}
