package com.danielbernal.transactions.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.danielbernal.transactions.domain.TransactionDo;
import com.danielbernal.transactions.exception.OldTransactionException;
import com.danielbernal.transactions.util.DateUtil;

/**
 * Implementation of the operations a transaction can perform
 * @author dbernalbazzani
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private StatisticService statisticService;

    @Value("${transaction.valid.time}")
    private Long validTime;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    public TransactionServiceImpl() {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createTransaction (TransactionDo transactionDo) throws OldTransactionException {

    	LOGGER.info("Creating a new transaction");
        validateTransaction(transactionDo);
        statisticService.addTransactionToStatistic(transactionDo);
    }

    /**
     * Validates if the transaction is older than 60 seconds or not
     * @param transactionDo Transaction to be validated
     * @throws OldTransactionException thrown if the transaction is older than 60 seconds
     */
    private void validateTransaction(TransactionDo transactionDo) throws OldTransactionException {

    	LOGGER.info("Validating the transaction");
        Long currentTimestamp = DateUtil.converToTimeStamp(LocalDateTime.now());
        Long transactionTime = transactionDo.getTimestamp() + validTime;
        if (transactionTime < currentTimestamp) {
        	LOGGER.error("Transaction is older than " + validTime + "ms");
            throw new OldTransactionException("The transaction is older than " + validTime + "ms");
        }
    }

}
