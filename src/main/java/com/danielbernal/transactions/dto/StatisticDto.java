package com.danielbernal.transactions.dto;

/**
 * Data transfer object to represent the statistic
 * @author dbernalbazzani
 */
public class StatisticDto {

    private double sum;

    private double avg;

    private double max;

    private double min;

    private long count;

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public long getCount() {
        return count;
    }

    /**
     * Constructor of the DTO
     * @param sum
     * @param avg
     * @param max
     * @param min
     * @param count
     */
    public StatisticDto(double sum, double avg, double max, double min, long count) {

        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
        this.count = count;
    }
}
