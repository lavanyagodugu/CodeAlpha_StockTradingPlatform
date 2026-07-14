package service;

import model.Holding;
import model.Portfolio;
import model.Stock;
import model.Transaction;
import model.User;
import utils.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Responsible for persisting and loading application data
 * to and from file-based storage.
 */
public class FileStorageService {

    /** Delimiter used to separate fields within each line of a data file. */
    private static final String DELIMITER = ",";

    /**
     * Saves the given user's portfolio to {@code data/portfolio.txt}.
     * The first line stores the available balance, and each
     * subsequent line stores one holding, including the stock
     * symbol, company name, quantity, average buy price, and
     * current price.
     *
     * @param user the user whose portfolio should be saved
     */
    public void savePortfolio(User user) {
        ensureDataDirectoryExists();
        Portfolio portfolio = user.getPortfolio();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.PORTFOLIO_FILE))) {
            writer.write(String.valueOf(portfolio.getAvailableBalance()));
            writer.newLine();

            for (Holding holding : portfolio.getHoldings()) {
                Stock stock = holding.getStock();
                writer.write(String.join(DELIMITER,
                        stock.getSymbol(),
                        stock.getCompanyName(),
                        String.valueOf(holding.getQuantity()),
                        String.valueOf(holding.getAverageBuyPrice()),
                        String.valueOf(stock.getCurrentPrice())));
                writer.newLine();
            }

            System.out.println("Portfolio saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving portfolio: " + e.getMessage());
        }
    }

    /**
     * Saves the given user's transaction history to
     * {@code data/transactions.txt}. Each line stores one
     * transaction, including the transaction id, stock symbol,
     * transaction type, quantity, price per share, and timestamp.
     *
     * @param user the user whose transaction history should be saved
     */
    public void saveTransactions(User user) {
        ensureDataDirectoryExists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.TRANSACTION_FILE))) {
            for (Transaction transaction : user.getTransactionHistory()) {
                writer.write(String.join(DELIMITER,
                        transaction.getTransactionId(),
                        transaction.getStock().getSymbol(),
                        transaction.getTransactionType(),
                        String.valueOf(transaction.getQuantity()),
                        String.valueOf(transaction.getPricePerShare()),
                        transaction.getTimestamp().toString()));
                writer.newLine();
            }

            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    /**
     * Loads portfolio data from {@code data/portfolio.txt} and
     * restores the given user's available balance and holdings.
     * The first line of the file is read as the available balance,
     * and each subsequent line is parsed into a holding.
     *
     * @param user the user whose portfolio should be restored
     */
    public void loadPortfolio(User user) {
        File file = new File(Constants.PORTFOLIO_FILE);
        if (!file.exists()) {
            System.out.println("No saved portfolio found.");
            return;
        }

        Portfolio portfolio = user.getPortfolio();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String balanceLine = reader.readLine();
            if (balanceLine != null && !balanceLine.isEmpty()) {
                portfolio.setAvailableBalance(Double.parseDouble(balanceLine));
            }

            portfolio.getHoldings().clear();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split(DELIMITER);
                if (fields.length != 5) {
                    continue;
                }

                String symbol = fields[0];
                String companyName = fields[1];
                int quantity = Integer.parseInt(fields[2]);
                double averageBuyPrice = Double.parseDouble(fields[3]);
                double currentPrice = Double.parseDouble(fields[4]);

                Stock stock = new Stock(symbol, companyName, currentPrice);
                Holding holding = new Holding(stock, quantity, averageBuyPrice);
                portfolio.getHoldings().add(holding);
            }

            System.out.println("Portfolio loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading portfolio: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing portfolio data: " + e.getMessage());
        }
    }

    /**
     * Ensures that the data directory used for storing files exists,
     * creating it if necessary.
     */
    private void ensureDataDirectoryExists() {
        File directory = new File(Constants.DATA_FOLDER);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}