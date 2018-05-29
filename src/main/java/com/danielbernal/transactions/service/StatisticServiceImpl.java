package com.danielbernal.transactions.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.danielbernal.transactions.domain.StatisticDo;
import com.danielbernal.transactions.domain.TransactionDo;

/**
 * Implementation of the operations a statistic can perform
 * @author dbernalbazzani
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    private StatisticDo statisticDo;

    private Object lock = new Object();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticServiceImpl.class);

    public StatisticServiceImpl() {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public StatisticDo obtainStatistics() {

    	LOGGER.info("Obtaining the statistic");
        return statisticDo != null ? statisticDo : new StatisticDo(0d, 0d, 0d, 0d, 0L);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTransactionToStatistic(TransactionDo transactionDo) {

    	LOGGER.debug("Adding a transaction to the statistic");
        synchronized (lock) {
            if (statisticDo == null) {
            	LOGGER.debug("Creating a new statistic");
                statisticDo =
                    new StatisticDo(transactionDo.getAmount(), transactionDo.getAmount(), transactionDo.getAmount(),
                                    transactionDo.getAmount(), 1);
            } else {
            	LOGGER.debug("Adding this transaction values to the statistic");
                double max = Math.max(transactionDo.getAmount(), statisticDo.getMax());
                double min = Math.min(transactionDo.getAmount(), statisticDo.getMin());
                double sum = statisticDo.getSum() + transactionDo.getAmount();
                double avg = sum / (statisticDo.getCount() + 1L);

                statisticDo = new StatisticDo(sum, avg, max, min, statisticDo.getCount() + 1L);
            }
        }
    }
}
