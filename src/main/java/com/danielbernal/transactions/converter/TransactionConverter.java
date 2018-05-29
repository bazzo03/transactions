package com.danielbernal.transactions.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.danielbernal.transactions.domain.TransactionDo;
import com.danielbernal.transactions.dto.TransactionDto;

/**
 * Converter from data transfer object to domain object
 * @author dbernalbazzani
 */
@Service
public class TransactionConverter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionConverter.class);

	/**
	 * Converts from DTO to DO
	 * @param transactionDto
	 * @return the DO converted
	 */
    public TransactionDo convertToDo (TransactionDto transactionDto) {

    	LOGGER.info("Converting to Do");
        TransactionDo transactionDo = new TransactionDo(transactionDto.getTimestamp(), transactionDto.getAmount());
        return  transactionDo;
    }

}
