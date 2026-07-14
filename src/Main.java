import model.Stock;
import model.Transaction;
import model.User;
import service.FileStorageService;
import service.MarketService;
import service.PortfolioService;
import service.TradingService;
import ui.ColorConsole;
import ui.ConsoleUI;
import utils.Constants;
import utils.InputValidator;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Entry point for the Stock Trading Platform console application.
 * Wires together the model, service, and ui layers and drives the
 * main menu loop.
 */
public class Main {

    /** Formatter used to display transaction timestamps in a readable form. */
    private static final DateTimeFormatter TIMESTAMP_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Starts the Stock Trading Platform application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        User user = new User("U001", "Guest");
        user.getPortfolio().setAvailableBalance(Constants.DEFAULT_BALANCE);

        MarketService marketService = new MarketService();
        PortfolioService portfolioService = new PortfolioService();
        TradingService tradingService = new TradingService();
        FileStorageService fileStorageService = new FileStorageService();
        ConsoleUI consoleUI = new ConsoleUI();
        Scanner scanner = new Scanner(System.in);

        fileStorageService.loadPortfolio(user);

        boolean running = true;
        while (running) {
            System.out.println();
            consoleUI.displayMainMenu();
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                consoleUI.displayInvalidChoice();
                continue;
            }

            switch (choice) {
                case 1:
                    marketService.displayMarketData();
                    break;
                case 2:
                    handleBuyStock(scanner, marketService, tradingService, user);
                    break;
                case 3:
                    handleSellStock(scanner, marketService, tradingService, user);
                    break;
                case 4:
                    handleViewPortfolio(portfolioService, user);
                    break;
                case 5:
                    handleViewTransactions(user);
                    break;
                case 6:
                    handleSaveData(fileStorageService, user);
                    break;
                case 7:
                    running = false;
                    consoleUI.displayExitMessage();
                    break;
                default:
                    consoleUI.displayInvalidChoice();
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Prompts the user for a stock symbol and quantity, validates
     * the input, and delegates the purchase to the trading service.
     *
     * @param scanner        the scanner used to read user input
     * @param marketService  the service used to look up stocks
     * @param tradingService the service used to execute the purchase
     * @param user           the user making the purchase
     */
    private static void handleBuyStock(Scanner scanner, MarketService marketService,
                                        TradingService tradingService, User user) {
        Stock stock = resolveStock(scanner, marketService);
        if (stock == null) {
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = readQuantity(scanner);
        if (quantity == -1) {
            return;
        }

        tradingService.buyStock(user, stock, quantity);
    }

    /**
     * Prompts the user for a stock symbol and quantity, validates
     * the input, and delegates the sale to the trading service.
     *
     * @param scanner        the scanner used to read user input
     * @param marketService  the service used to look up stocks
     * @param tradingService the service used to execute the sale
     * @param user           the user selling the stock
     */
    private static void handleSellStock(Scanner scanner, MarketService marketService,
                                         TradingService tradingService, User user) {
        Stock stock = resolveStock(scanner, marketService);
        if (stock == null) {
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = readQuantity(scanner);
        if (quantity == -1) {
            return;
        }

        tradingService.sellStock(user, stock, quantity);
    }

    /**
     * Displays the user's portfolio along with summary figures for
     * total value, total investment, and profit or loss.
     *
     * @param portfolioService the service used to compute portfolio figures
     * @param user             the user whose portfolio should be displayed
     */
    private static void handleViewPortfolio(PortfolioService portfolioService, User user) {
        portfolioService.displayPortfolio(user);

        double totalValue = portfolioService.calculatePortfolioValue(user);
        double totalInvestment = portfolioService.calculateInvestment(user);
        double profitLoss = totalValue - totalInvestment;

        System.out.printf("%nAvailable Balance: %.2f%n", user.getPortfolio().getAvailableBalance());
        System.out.printf("Total Portfolio Value: %.2f%n", totalValue);
        System.out.printf("Total Investment: %.2f%n", totalInvestment);

        if (profitLoss >= 0) {
            ColorConsole.printSuccess(String.format("Profit: %.2f", profitLoss));
        } else {
            ColorConsole.printError(String.format("Loss: %.2f", profitLoss));
        }
    }

    /**
     * Displays the user's full transaction history.
     *
     * @param user the user whose transactions should be displayed
     */
    private static void handleViewTransactions(User user) {
        if (user.getTransactionHistory().isEmpty()) {
            ColorConsole.printInfo("No transactions found.");
            return;
        }

        System.out.printf("%-36s %-10s %-6s %-10s %-15s %-20s%n",
                "Transaction ID", "Symbol", "Type", "Quantity", "Price/Share", "Timestamp");
        System.out.println("-".repeat(100));

        for (Transaction transaction : user.getTransactionHistory()) {
            System.out.printf("%-36s %-10s %-6s %-10d %-15.2f %-20s%n",
                    transaction.getTransactionId(),
                    transaction.getStock().getSymbol(),
                    transaction.getTransactionType(),
                    transaction.getQuantity(),
                    transaction.getPricePerShare(),
                    transaction.getTimestamp().format(TIMESTAMP_FORMATTER));
        }
    }

    /**
     * Persists the user's portfolio and transaction history to disk.
     *
     * @param fileStorageService the service used to save data
     * @param user               the user whose data should be saved
     */
    private static void handleSaveData(FileStorageService fileStorageService, User user) {
        fileStorageService.savePortfolio(user);
        fileStorageService.saveTransactions(user);
    }

    /**
     * Prompts for a stock symbol, validates its format, and looks it
     * up in the market. Shared by the buy and sell flows to avoid
     * duplicating symbol validation and lookup logic.
     *
     * @param scanner       the scanner used to read user input
     * @param marketService the service used to look up stocks
     * @return the matching stock, or {@code null} if the symbol was
     *         invalid or not found on the market
     */
    private static Stock resolveStock(Scanner scanner, MarketService marketService) {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine().trim().toUpperCase();

        if (!InputValidator.isValidStockSymbol(symbol)) {
            ColorConsole.printError("Invalid stock symbol. Symbol must be 1-5 uppercase letters.");
            return null;
        }

        Stock stock = marketService.getStockBySymbol(symbol);
        if (stock == null) {
            ColorConsole.printError("Stock not found: " + symbol);
        }

        return stock;
    }

    /**
     * Reads and validates a quantity value from the given scanner.
     *
     * @param scanner the scanner used to read user input
     * @return the validated quantity, or {@code -1} if the input
     *         was not a valid positive integer
     */
    private static int readQuantity(Scanner scanner) {
        String input = scanner.nextLine().trim();
        int quantity;
        try {
            quantity = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            ColorConsole.printError("Invalid quantity entered.");
            return -1;
        }

        if (!InputValidator.isValidQuantity(quantity)) {
            ColorConsole.printError("Quantity must be greater than zero.");
            return -1;
        }

        return quantity;
    }
}