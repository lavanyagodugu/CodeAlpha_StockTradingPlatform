package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user of the trading platform, including account
 * and profile information.
 */
public class User {

    /** Unique identifier for this user. */
    private String userId;

    /** Display name of the user. */
    private String name;

    /** The portfolio owned by this user. */
    private Portfolio portfolio;

    /** History of transactions performed by this user. */
    private List<Transaction> transactionHistory;

    /**
     * Constructs a new User with the given id and name, initializing
     * an empty portfolio and transaction history.
     *
     * @param userId the unique identifier for the user
     * @param name   the display name of the user
     */
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.portfolio = new Portfolio(0.0);
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of this user.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of this user.
     *
     * @param userId the user id to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the display name of this user.
     *
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the display name of this user.
     *
     * @param name the user name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the portfolio owned by this user.
     *
     * @return the user's portfolio
     */
    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Sets the portfolio owned by this user.
     *
     * @param portfolio the portfolio to set
     */
    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Returns the transaction history of this user.
     *
     * @return the list of past transactions
     */
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    /**
     * Sets the transaction history of this user.
     *
     * @param transactionHistory the list of past transactions to set
     */
    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    /**
     * Returns a string representation of the user.
     *
     * @return a string describing this user
     */
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", portfolio=" + portfolio +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}
