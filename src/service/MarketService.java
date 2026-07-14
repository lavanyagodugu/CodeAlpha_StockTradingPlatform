package service;

import model.Stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to stock market data such as prices and
 * available stocks.
 */
public class MarketService {

    /** List of all stocks currently available on the market. */
    private List<Stock> stocks;

    /**
     * Constructs a new MarketService and initializes it with a
     * default set of well-known mock stocks.
     */
    public MarketService() {
        this.stocks = new ArrayList<>();
        stocks.add(new Stock("AAPL", "Apple Inc.", 195.50));
        stocks.add(new Stock("MSFT", "Microsoft Corporation", 415.20));
        stocks.add(new Stock("GOOGL", "Alphabet Inc.", 168.75));
        stocks.add(new Stock("AMZN", "Amazon.com Inc.", 182.30));
        stocks.add(new Stock("TSLA", "Tesla Inc.", 248.90));
        stocks.add(new Stock("META", "Meta Platforms Inc.", 505.60));
        stocks.add(new Stock("NVDA", "NVIDIA Corporation", 128.40));
        stocks.add(new Stock("NFLX", "Netflix Inc.", 675.10));
        stocks.add(new Stock("JPM", "JPMorgan Chase & Co.", 210.85));
        stocks.add(new Stock("DIS", "The Walt Disney Company", 112.35));
    }

    /**
     * Returns the list of all stocks available on the market.
     *
     * @return the list of all stocks
     */
    public List<Stock> getAllStocks() {
        return stocks;
    }

    /**
     * Finds and returns the stock matching the given symbol.
     *
     * @param symbol the ticker symbol to search for
     * @return the matching stock, or {@code null} if no stock with
     *         the given symbol exists
     */
    public Stock getStockBySymbol(String symbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null;
    }

    /**
     * Prints a formatted table of all available market data,
     * including each stock's symbol, company name, and current price.
     */
    public void displayMarketData() {
        System.out.printf("%-10s %-30s %-15s%n", "Symbol", "Company Name", "Price");
        System.out.println("----------------------------------------------------------");
        for (Stock stock : stocks) {
            System.out.printf("%-10s %-30s %-15.2f%n",
                    stock.getSymbol(), stock.getCompanyName(), stock.getCurrentPrice());
        }
    }
}
