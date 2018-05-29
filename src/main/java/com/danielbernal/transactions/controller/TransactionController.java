package com.danielbernal.transactions.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danielbernal.transactions.converter.TransactionConverter;
import com.danielbernal.transactions.dto.TransactionDto;
import com.danielbernal.transactions.exception.OldTransactionException;
import com.danielbernal.transactions.service.TransactionService;

/**
 * Controller to handle http request regarding transactions
 * @author dbernalbazzani
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionConverter transactionConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    
    /**
     * Request to create a transaction
     * @param transactionDto Transaction created
     * @throws OldTransactionException if the transaction is older than 60 seconds
     */
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    @ExceptionHandler({OldTransactionException.class})
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransactionDto transactionDto) throws OldTransactionException {

    	LOGGER.info("Request to create a transaction");
        transactionService.createTransaction(transactionConverter.convertToDo(transactionDto));
    }

}
