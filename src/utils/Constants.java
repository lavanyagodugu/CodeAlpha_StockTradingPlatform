package utils;

/**
 * Holds application-wide constant values used across the
 * trading platform.
 */
public final class Constants {

    /** Default starting cash balance assigned to a new user's portfolio. */
    public static final double DEFAULT_BALANCE = 100000.0;

    /** Path to the folder used for storing application data files. */
    public static final String DATA_FOLDER = "data/";

    /** Path to the file used for storing portfolio data. */
    public static final String PORTFOLIO_FILE = "data/portfolio.txt";

    /** Path to the file used for storing transaction history data. */
    public static final String TRANSACTION_FILE = "data/transactions.txt";

    /** Path to the file used for storing market data. */
    public static final String MARKET_FILE = "data/market.txt";

    /**
     * Private constructor to prevent instantiation of this
     * constants-only class.
     */
    private Constants() {
    }
}
