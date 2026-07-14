package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user's investment portfolio, aggregating their
 * holdings and overall value.
 */
public class Portfolio {

    /** List of stock holdings contained in this portfolio. */
    private List<Holding> holdings;

    /** Cash balance available for trading. */
    private double availableBalance;

    /**
     * Constructs a new Portfolio with the given available balance
     * and an empty list of holdings.
     *
     * @param availableBalance the initial available cash balance
     */
    public Portfolio(double availableBalance) {
        this.holdings = new ArrayList<>();
        this.availableBalance = availableBalance;
    }

    /**
     * Returns the list of holdings in this portfolio.
     *
     * @return the list of holdings
     */
    public List<Holding> getHoldings() {
        return holdings;
    }

    /**
     * Sets the list of holdings in this portfolio.
     *
     * @param holdings the list of holdings to set
     */
    public void setHoldings(List<Holding> holdings) {
        this.holdings = holdings;
    }

    /**
     * Returns the available cash balance.
     *
     * @return the available balance
     */
    public double getAvailableBalance() {
        return availableBalance;
    }

    /**
     * Sets the available cash balance.
     *
     * @param availableBalance the available balance to set
     */
    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    /**
     * Returns a string representation of the portfolio.
     *
     * @return a string describing this portfolio
     */
    @Override
    public String toString() {
        return "Portfolio{" +
                "holdings=" + holdings +
                ", availableBalance=" + availableBalance +
                '}';
    }
}
