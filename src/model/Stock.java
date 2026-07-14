package model;

/**
 * Represents a tradable stock, including its identifying details
 * and market-related attributes.
 */
public class Stock {

    /** Ticker symbol uniquely identifying the stock. */
    private String symbol;

    /** Full name of the company issuing the stock. */
    private String companyName;

    /** Current market price of a single share. */
    private double currentPrice;

    /**
     * Constructs a new Stock with the given details.
     *
     * @param symbol       the ticker symbol of the stock
     * @param companyName  the name of the company
     * @param currentPrice the current market price per share
     */
    public Stock(String symbol, String companyName, double currentPrice) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
    }

    /**
     * Returns the ticker symbol of the stock.
     *
     * @return the stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the ticker symbol of the stock.
     *
     * @param symbol the stock symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the company name associated with the stock.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the company name associated with the stock.
     *
     * @param companyName the company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Returns the current market price of the stock.
     *
     * @return the current price per share
     */
    public double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Sets the current market price of the stock.
     *
     * @param currentPrice the current price per share to set
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * Returns a string representation of the stock.
     *
     * @return a string describing this stock
     */
    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
