package com.danielbernal.transactions.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the transaction is older than 60 seconds
 * @author dbernalbazzani
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Transaction is older than 60 seconds")
public class OldTransactionException extends Exception {

	private static final long serialVersionUID = 1L;

	public OldTransactionException(String message) {

        super(message);
    }

}
