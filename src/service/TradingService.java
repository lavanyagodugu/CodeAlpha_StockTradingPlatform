package service;

import model.Holding;
import model.Portfolio;
import model.Stock;
import model.Transaction;
import model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Handles the execution of buy and sell trade operations for users.
 */
public class TradingService {

    /**
     * Buys the given quantity of a stock on behalf of the specified
     * user. Validates the requested quantity and available balance,
     * updates or creates the corresponding holding, records a BUY
     * transaction in the user's history, and deducts the purchase
     * amount from the user's available balance.
     *
     * @param user     the user making the purchase
     * @param stock    the stock to buy
     * @param quantity the number of shares to buy
     */
    public void buyStock(User user, Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Quantity must be greater than zero.");
            return;
        }

        Portfolio portfolio = user.getPortfolio();
        double totalCost = quantity * stock.getCurrentPrice();

        if (portfolio.getAvailableBalance() < totalCost) {
            System.out.printf("Insufficient balance. Required: %.2f, Available: %.2f%n",
                    totalCost, portfolio.getAvailableBalance());
            return;
        }

        portfolio.setAvailableBalance(portfolio.getAvailableBalance() - totalCost);

        Holding existingHolding = findHolding(portfolio, stock);
        if (existingHolding != null) {
            int newQuantity = existingHolding.getQuantity() + quantity;
            double newAverageBuyPrice = ((existingHolding.getAverageBuyPrice() * existingHolding.getQuantity())
                    + (stock.getCurrentPrice() * quantity)) / newQuantity;

            existingHolding.setQuantity(newQuantity);
            existingHolding.setAverageBuyPrice(newAverageBuyPrice);
        } else {
            Holding newHolding = new Holding(stock, quantity, stock.getCurrentPrice());
            portfolio.getHoldings().add(newHolding);
        }

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                stock,
                "BUY",
                quantity,
                stock.getCurrentPrice(),
                LocalDateTime.now());
        user.getTransactionHistory().add(transaction);

        System.out.println("Successfully purchased " + quantity + " share(s) of " + stock.getSymbol() + ".");
    }

    /**
     * Sells the given quantity of a stock on behalf of the specified
     * user. Validates the requested quantity and current ownership,
     * updates or removes the corresponding holding, records a SELL
     * transaction in the user's history, and credits the sale amount
     * to the user's available balance.
     *
     * @param user     the user selling the stock
     * @param stock    the stock to sell
     * @param quantity the number of shares to sell
     */
    public void sellStock(User user, Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Quantity must be greater than zero.");
            return;
        }

        Portfolio portfolio = user.getPortfolio();
        Holding existingHolding = findHolding(portfolio, stock);

        if (existingHolding == null) {
            System.out.println("You do not own any shares of " + stock.getSymbol() + ".");
            return;
        }

        if (existingHolding.getQuantity() < quantity) {
            System.out.println("Insufficient shares. You only own " + existingHolding.getQuantity()
                    + " share(s) of " + stock.getSymbol() + ".");
            return;
        }

        double saleAmount = quantity * stock.getCurrentPrice();
        portfolio.setAvailableBalance(portfolio.getAvailableBalance() + saleAmount);

        int remainingQuantity = existingHolding.getQuantity() - quantity;
        if (remainingQuantity == 0) {
            portfolio.getHoldings().remove(existingHolding);
        } else {
            existingHolding.setQuantity(remainingQuantity);
        }

        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                stock,
                "SELL",
                quantity,
                stock.getCurrentPrice(),
                LocalDateTime.now());
        user.getTransactionHistory().add(transaction);

        System.out.println("Successfully sold " + quantity + " share(s) of " + stock.getSymbol() + ".");
    }

    /**
     * Searches the given portfolio for an existing holding of the
     * specified stock.
     *
     * @param portfolio the portfolio to search
     * @param stock     the stock to look for
     * @return the matching holding, or {@code null} if the portfolio
     *         does not contain the given stock
     */
    private Holding findHolding(Portfolio portfolio, Stock stock) {
        List<Holding> holdings = portfolio.getHoldings();
        for (Holding holding : holdings) {
            if (holding.getStock().getSymbol().equalsIgnoreCase(stock.getSymbol())) {
                return holding;
            }
        }
        return null;
    }
}