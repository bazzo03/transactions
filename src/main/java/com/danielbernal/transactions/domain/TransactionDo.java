package com.danielbernal.transactions.domain;

/**
 * Domain object to represent the transaction
 * @author dbernalbazzani
 *
 */
public class TransactionDo {

    private long timestamp;

    private double amount;

    public long getTimestamp() {
        return timestamp;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Constructor of the DO
     * @param timestamp
     * @param amount
     */
    public TransactionDo(long timestamp, double amount) {

        this.timestamp = timestamp;
        this.amount = amount;
    }

}
