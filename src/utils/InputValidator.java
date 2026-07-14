package utils;

/**
 * Provides validation logic for user-supplied input throughout
 * the application.
 */
public class InputValidator {

    /**
     * Checks whether the given quantity is valid. A valid quantity
     * must be greater than zero.
     *
     * @param quantity the quantity to validate
     * @return {@code true} if the quantity is greater than zero,
     *         {@code false} otherwise
     */
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }

    /**
     * Checks whether the given amount is valid. A valid amount
     * must be greater than zero.
     *
     * @param amount the amount to validate
     * @return {@code true} if the amount is greater than zero,
     *         {@code false} otherwise
     */
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    /**
     * Checks whether the given stock symbol is valid. A valid
     * symbol must contain only uppercase letters and be between
     * 1 and 5 characters long.
     *
     * @param symbol the stock symbol to validate
     * @return {@code true} if the symbol matches the required
     *         format, {@code false} otherwise
     */
    public static boolean isValidStockSymbol(String symbol) {
        if (symbol == null) {
            return false;
        }
        return symbol.matches("[A-Z]{1,5}");
    }
}
