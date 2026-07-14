package ui;

/**
 * Responsible for formatting and printing tabular data to the
 * console.
 */
public class TablePrinter {

    /** Width used for the bordered title/menu box. */
    private static final int BOX_WIDTH = 78;

    /**
     * Total width of the market data table, in characters. Must
     * stay in sync with the row format used by MarketService
     * ("| %-9s | %-29s | %-21.2f |") so that borders and data rows
     * line up exactly.
     */
    private static final int MARKET_TABLE_WIDTH = 69;

    /**
     * Total width of the portfolio table, in characters. Must stay
     * in sync with the row format used by PortfolioService
     * ("| %-9s | %-24s | %-9d | %-14.2f | %-14.2f | %-14.2f |") so
     * that borders and data rows line up exactly.
     */
    private static final int PORTFOLIO_TABLE_WIDTH = 103;

    /**
     * Total width of the transaction table, in characters. The
     * transaction ID column is sized to comfortably hold a
     * standard 36-character UUID without breaking the border
     * alignment.
     */
    private static final int TRANSACTION_TABLE_WIDTH = 115;

    /** Width used for the plain-text summary separator. */
    private static final int SUMMARY_WIDTH = 62;

    /**
     * Prints a centered section title enclosed in a bordered box.
     *
     * @param title the title text to display
     */
    public void printHeader(String title) {
        String border = ColorConsole.CYAN + solidBorder(BOX_WIDTH) + ColorConsole.RESET;
        System.out.println(border);
        System.out.println(ColorConsole.CYAN + "|" + centerText(title, BOX_WIDTH - 2) + "|" + ColorConsole.RESET);
        System.out.println(border);
    }

    /**
     * Prints a bordered box line matching the width used by
     * {@link #printHeader(String)}, typically used to close a
     * bordered section.
     */
    public void printBoxBorder() {
        System.out.println(ColorConsole.CYAN + solidBorder(BOX_WIDTH) + ColorConsole.RESET);
    }

    /**
     * Prints a horizontal separator line, for example around a
     * plain-text financial summary block.
     */
    public void printSeparator() {
        System.out.println("-".repeat(SUMMARY_WIDTH));
    }

    /**
     * Prints the bordered column header row for market data tables.
     * Column widths match the row format used by
     * {@code MarketService.displayMarketData()}.
     */
    public void printMarketHeader() {
        String border = dashBorder(MARKET_TABLE_WIDTH);
        System.out.println(border);
        System.out.printf("| %-9s | %-29s | %-21s |%n", "Symbol", "Company Name", "Current Price (Rs.)");
        System.out.println(border);
    }

    /**
     * Prints the closing border row for market data tables.
     */
    public void printMarketFooter() {
        System.out.println(dashBorder(MARKET_TABLE_WIDTH));
    }

    /**
     * Prints the bordered column header row for portfolio data
     * tables. Column widths match the row format used by
     * {@code PortfolioService.displayPortfolio()}.
     */
    public void printPortfolioHeader() {
        String border = dashBorder(PORTFOLIO_TABLE_WIDTH);
        System.out.println(border);
        System.out.printf("| %-9s | %-24s | %-9s | %-14s | %-14s | %-14s |%n",
                "Symbol", "Company Name", "Quantity", "Avg Buy Price", "Current Price", "Total Value");
        System.out.println(border);
    }

    /**
     * Prints the closing border row for portfolio data tables.
     */
    public void printPortfolioFooter() {
        System.out.println(dashBorder(PORTFOLIO_TABLE_WIDTH));
    }

    /**
     * Prints the bordered column header row for transaction history
     * tables. The transaction ID column is wide enough to hold a
     * full UUID without breaking column alignment.
     */
    public void printTransactionHeader() {
        String border = dashBorder(TRANSACTION_TABLE_WIDTH);
        System.out.println(border);
        System.out.printf("| %-38s | %-8s | %-6s | %-10s | %-14s | %-20s |%n",
                "Transaction ID", "Symbol", "Type", "Quantity", "Price/Share", "Timestamp");
        System.out.println(border);
    }

    /**
     * Prints a single, aligned transaction row within the bordered
     * transaction table.
     *
     * @param transactionId the transaction's unique identifier
     * @param symbol        the stock symbol involved
     * @param type           the transaction type ("BUY" or "SELL")
     * @param quantity       the number of shares involved
     * @param pricePerShare  the price per share at the time of the transaction
     * @param timestamp      the formatted timestamp of the transaction
     */
    public void printTransactionRow(String transactionId, String symbol, String type,
                                     int quantity, double pricePerShare, String timestamp) {
        System.out.printf("| %-38s | %-8s | %-6s | %-10d | %-14.2f | %-20s |%n",
                transactionId, symbol, type, quantity, pricePerShare, timestamp);
    }

    /**
     * Prints the closing border row for transaction history tables.
     */
    public void printTransactionFooter() {
        System.out.println(dashBorder(TRANSACTION_TABLE_WIDTH));
    }

    /**
     * Builds a horizontal border line of the given total width,
     * using plain ASCII dashes for maximum compatibility across
     * terminals, including Windows Command Prompt.
     *
     * @param width the total width of the border, including the
     *              leading and trailing corner characters
     * @return the border line
     */
    private String dashBorder(int width) {
        return "+" + "-".repeat(width - 2) + "+";
    }

    /**
     * Builds a horizontal border line of the given total width,
     * using plain ASCII equals signs for section titles.
     *
     * @param width the total width of the border, including the
     *              leading and trailing corner characters
     * @return the border line
     */
    private String solidBorder(int width) {
        return "+" + "=".repeat(width - 2) + "+";
    }

    /**
     * Centers the given text within a field of the specified width,
     * padding evenly with spaces on both sides.
     *
     * @param text  the text to center
     * @param width the total width of the field
     * @return the centered text, padded with spaces
     */
    private String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int totalPadding = width - text.length();
        int leftPadding = totalPadding / 2;
        int rightPadding = totalPadding - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
}