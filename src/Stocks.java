import java.util.Date;
import java.util.Scanner;

/**
 * Represents a stock asset in the investment portfolio.
 * Extends the base Asset class and adds stock-specific properties
 * including stock symbol and exchange information.
 */
public class Stocks extends Asset {
    /** The ticker symbol of the stock (e.g., "AAPL" for Apple) */
    private String stockSymbol;
    
    /** The stock exchange where the stock is traded (e.g., "NASDAQ", "NYSE") */
    private String exchange;

    /**
     * Constructs a new Stocks asset with specified properties.
     *
     * @param name              The name of the stock/company
     * @param quantity          The number of shares held
     * @param purchaseDate      The date when the stock was purchased
     * @param purchasePrice     The purchase price per share
     * @param zakatApplicable   Whether Zakat applies to this stock
     * @param stockSymbol       The ticker symbol of the stock
     * @param exchange          The exchange where the stock is traded
     */
    public Stocks(String name, int quantity, Date purchaseDate, float purchasePrice,
                 Boolean zakatApplicable, String stockSymbol, String exchange) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.stockSymbol = stockSymbol;
        this.exchange = exchange;
    }

    /**
     * Gets the stock's ticker symbol.
     *
     * @return The stock symbol (e.g., "MSFT" for Microsoft)
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * Sets the stock's ticker symbol.
     *
     * @param stockSymbol The new stock symbol
     */
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     * Gets the exchange where the stock is traded.
     *
     * @return The name of the stock exchange
     */
    public String getExchange() {
        return exchange;
    }

    /**
     * Sets the exchange where the stock is traded.
     *
     * @param exchange The new stock exchange
     */
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    /**
     * Implements the abstract method to edit stock-specific details.
     * Prompts the user for new stock symbol and exchange values.
     *
     * @param scanner The Scanner object used for user input
     */
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Stock Symbol: ");
        this.setStockSymbol(scanner.nextLine());
        System.out.println("Enter new Exchange: ");
        this.setExchange(scanner.nextLine());
    }

    /**
     * Returns a string representation of the stock asset,
     * including both inherited properties and stock-specific properties.
     *
     * @return A formatted string containing all stock details
     */
    @Override
    public String toString() {
        return super.toString() + 
               ", StockSymbol: " + stockSymbol + 
               ", Exchange: " + exchange;
    }

}
