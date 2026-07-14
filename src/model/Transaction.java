package model;

import java.time.LocalDateTime;

/**
 * Represents a single buy or sell transaction executed on the
 * trading platform.
 */
public class Transaction {

    /** Unique identifier for this transaction. */
    private String transactionId;

    /** The stock involved in this transaction. */
    private Stock stock;

    /** Type of transaction, either "BUY" or "SELL". */
    private String transactionType;

    /** Number of shares involved in the transaction. */
    private int quantity;

    /** Price per share at the time of the transaction. */
    private double pricePerShare;

    /** Date and time when the transaction occurred. */
    private LocalDateTime timestamp;

    /**
     * Constructs a new Transaction with the given details.
     *
     * @param transactionId   the unique identifier for the transaction
     * @param stock           the stock involved in the transaction
     * @param transactionType the type of transaction ("BUY" or "SELL")
     * @param quantity        the number of shares involved
     * @param pricePerShare   the price per share at the time of the transaction
     * @param timestamp       the date and time of the transaction
     */
    public Transaction(String transactionId, Stock stock, String transactionType,
                        int quantity, double pricePerShare, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.stock = stock;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.timestamp = timestamp;
    }

    /**
     * Returns the unique identifier of this transaction.
     *
     * @return the transaction id
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the unique identifier of this transaction.
     *
     * @param transactionId the transaction id to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Returns the stock involved in this transaction.
     *
     * @return the stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Sets the stock involved in this transaction.
     *
     * @param stock the stock to set
     */
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    /**
     * Returns the type of this transaction ("BUY" or "SELL").
     *
     * @return the transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the type of this transaction ("BUY" or "SELL").
     *
     * @param transactionType the transaction type to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Returns the number of shares involved in this transaction.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the number of shares involved in this transaction.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the price per share at the time of the transaction.
     *
     * @return the price per share
     */
    public double getPricePerShare() {
        return pricePerShare;
    }

    /**
     * Sets the price per share at the time of the transaction.
     *
     * @param pricePerShare the price per share to set
     */
    public void setPricePerShare(double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    /**
     * Returns the date and time when this transaction occurred.
     *
     * @return the transaction timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the date and time when this transaction occurred.
     *
     * @param timestamp the transaction timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return a string describing this transaction
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", stock=" + stock +
                ", transactionType='" + transactionType + '\'' +
                ", quantity=" + quantity +
                ", pricePerShare=" + pricePerShare +
                ", timestamp=" + timestamp +
                '}';
    }
}
