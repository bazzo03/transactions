package com.danielbernal.transactions.dto;

/**
 * Data transfer object to represent the transaction
 * @author dbernalbazzani
 */
public class TransactionDto {

    private long timestamp;

    private double amount;

    public long getTimestamp() { return timestamp; }

    public double getAmount() {
        return amount;
    }

    public TransactionDto() {

    }

    /**
     * Constructor for the DTO
     * @param timestamp
     * @param amount
     */
    public TransactionDto(long timestamp, double amount) {

        this.timestamp = timestamp;
        this.amount = amount;
    }

}
