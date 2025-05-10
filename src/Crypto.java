import java.util.Date;
import java.util.Scanner;

public class Crypto extends Asset {
    private String cryptoSymbol;
    private String exchange;

    public Crypto(String name, int quantity, Date purchaseDate, float purchasePrice, Boolean zakatApplicable, String cryptoSymbol, String exchange) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.cryptoSymbol = cryptoSymbol;
        this.exchange = exchange;
    }

    public String getCryptoSymbol() {
        return cryptoSymbol;
    }

    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return super.toString() + ", CryptoSymbol: " + cryptoSymbol + ", Exchange: " + exchange;
    }

    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new CryptoSymbol: ");
        this.setCryptoSymbol(scanner.nextLine());
        System.out.println("Enter new Exchange: ");
        this.setExchange(scanner.nextLine());
    }
}