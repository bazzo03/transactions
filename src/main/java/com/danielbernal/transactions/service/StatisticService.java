package com.danielbernal.transactions.service;

import com.danielbernal.transactions.domain.StatisticDo;
import com.danielbernal.transactions.domain.TransactionDo;

/**
 * Service declaration of the operations that a statistic can do
 * @author dbernalbazzani
 */
public interface StatisticService {
	
	/**
	 * Obtain the statistic of the transactions
	 * @return the statistic
	 */
    public StatisticDo obtainStatistics();
    
    /**
     * Adds the transaction to the statistic
     * @param transactionDo to be added to the statistic
     */
    public void addTransactionToStatistic(TransactionDo transactionDo);
}
