import java.util.Date;
import java.util.Scanner;
public class Stocks extends Asset{
    String stockSymbol;
    String exchange;
    public Stocks(String name, int quantity, Date purchaseDate, float purchasePrice, Boolean zakatApplicable, String stockSymbol, String exchange) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.stockSymbol = stockSymbol;
        this.exchange = exchange;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Stock Symbol: ");
        this.setStockSymbol(scanner.nextLine());
        System.out.println("Enter new Exchange: ");
        this.setExchange(scanner.nextLine());
    }

    @Override
    public String toString() {
        return super.toString() + ", StockSymbol: " + stockSymbol + ", Exchange: " + exchange;
    }

}
