package model;

/**
 * Represents a single stock holding within a portfolio, including
 * quantity and cost basis information.
 */
public class Holding {

    /** The stock associated with this holding. */
    private Stock stock;

    /** Number of shares currently held. */
    private int quantity;

    /** Average price per share paid across all purchases. */
    private double averageBuyPrice;

    /**
     * Constructs a new Holding with the given details.
     *
     * @param stock           the stock associated with this holding
     * @param quantity        the number of shares held
     * @param averageBuyPrice the average purchase price per share
     */
    public Holding(Stock stock, int quantity, double averageBuyPrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
    }

    /**
     * Returns the stock associated with this holding.
     *
     * @return the stock
     */
    public Stock getStock() {
        return stock;
    }

    /**
     * Sets the stock associated with this holding.
     *
     * @param stock the stock to set
     */
    public void setStock(Stock stock) {
        this.stock = stock;
    }

    /**
     * Returns the number of shares held.
     *
     * @return the quantity held
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the number of shares held.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the average buy price per share.
     *
     * @return the average buy price
     */
    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    /**
     * Sets the average buy price per share.
     *
     * @param averageBuyPrice the average buy price to set
     */
    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    /**
     * Returns a string representation of the holding.
     *
     * @return a string describing this holding
     */
    @Override
    public String toString() {
        return "Holding{" +
                "stock=" + stock +
                ", quantity=" + quantity +
                ", averageBuyPrice=" + averageBuyPrice +
                '}';
    }
}
