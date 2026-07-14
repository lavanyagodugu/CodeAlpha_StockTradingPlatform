package service;

import model.Holding;
import model.Portfolio;
import model.Stock;
import model.User;

/**
 * Manages portfolio-related operations such as tracking holdings
 * and calculating portfolio performance.
 */
public class PortfolioService {

    /**
     * Prints a formatted table showing all holdings in the given
     * user's portfolio, including symbol, company name, quantity,
     * average buy price, current price, and total value.
     *
     * @param user the user whose portfolio should be displayed
     */
    public void displayPortfolio(User user) {
        Portfolio portfolio = user.getPortfolio();

        if (portfolio.getHoldings().isEmpty()) {
            System.out.println("No holdings in portfolio.");
            return;
        }

        System.out.printf("%-10s %-25s %-10s %-18s %-15s %-15s%n",
                "Symbol", "Company Name", "Quantity", "Avg Buy Price", "Current Price", "Total Value");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (Holding holding : portfolio.getHoldings()) {
            Stock stock = holding.getStock();
            double totalValue = holding.getQuantity() * stock.getCurrentPrice();

            System.out.printf("%-10s %-25s %-10d %-18.2f %-15.2f %-15.2f%n",
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    holding.getQuantity(),
                    holding.getAverageBuyPrice(),
                    stock.getCurrentPrice(),
                    totalValue);
        }
    }

    /**
     * Calculates the total current market value of all holdings in
     * the given user's portfolio.
     *
     * @param user the user whose portfolio value should be calculated
     * @return the total current value of all holdings
     */
    public double calculatePortfolioValue(User user) {
        double totalValue = 0.0;
        for (Holding holding : user.getPortfolio().getHoldings()) {
            totalValue += holding.getQuantity() * holding.getStock().getCurrentPrice();
        }
        return totalValue;
    }

    /**
     * Calculates the total amount invested across all holdings in
     * the given user's portfolio, based on average buy price.
     *
     * @param user the user whose total investment should be calculated
     * @return the total amount invested
     */
    public double calculateInvestment(User user) {
        double totalInvestment = 0.0;
        for (Holding holding : user.getPortfolio().getHoldings()) {
            totalInvestment += holding.getQuantity() * holding.getAverageBuyPrice();
        }
        return totalInvestment;
    }

    /**
     * Calculates the profit or loss of the given user's portfolio by
     * comparing the current portfolio value against the total amount
     * invested.
     *
     * @param user the user whose profit or loss should be calculated
     * @return the difference between current portfolio value and
     *         total investment; positive indicates profit, negative
     *         indicates loss
     */
    public double calculateProfitLoss(User user) {
        return calculatePortfolioValue(user) - calculateInvestment(user);
    }
}