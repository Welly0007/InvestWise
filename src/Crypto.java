import java.util.Date;
import java.util.Scanner;
/**
 * Represents a cryptocurrency asset, extending the base Asset class.
 * Includes cryptocurrency-specific properties like symbol and exchange.
 */
public class Crypto extends Asset {
    private String cryptoSymbol;
    private String exchange;
     /**
     * Constructs a new Crypto asset with the specified details.
     *
     * @param name             The name of the cryptocurrency
     * @param quantity         The quantity held
     * @param purchaseDate     The date of purchase
     * @param purchasePrice    The purchase price per unit
     * @param zakatApplicable  Whether Zakat applies to this asset
     * @param cryptoSymbol     The trading symbol of the cryptocurrency (e.g., BTC)
     * @param exchange         The exchange where the cryptocurrency is traded
     */
    public Crypto(String name, int quantity, Date purchaseDate, float purchasePrice, Boolean zakatApplicable, String cryptoSymbol, String exchange) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.cryptoSymbol = cryptoSymbol;
        this.exchange = exchange;
    }
    /**
     * Gets the cryptocurrency's trading symbol.
     *
     * @return The cryptocurrency symbol
     */
    public String getCryptoSymbol() {
        return cryptoSymbol;
    }
     /**
     * Sets the cryptocurrency's trading symbol.
     *
     * @param cryptoSymbol The new trading symbol
     */

    public void setCryptoSymbol(String cryptoSymbol) {
        this.cryptoSymbol = cryptoSymbol;
    }
    /**
     * Gets the exchange where the cryptocurrency is traded.
     *
     * @return The exchange name
     */

    public String getExchange() {
        return exchange;
    }
    /**
     * Sets the exchange where the cryptocurrency is traded.
     *
     * @param exchange The new exchange name
     */

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    /**
     * Returns a string representation of the cryptocurrency asset,
     * including both base asset properties and crypto-specific properties.
     *
     * @return Formatted string with all asset details
     */
    @Override
    public String toString() {
        return super.toString() + ", CryptoSymbol: " + cryptoSymbol + ", Exchange: " + exchange;
    }
    /**
     * Implements the abstract method to edit cryptocurrency-specific details.
     * Prompts the user for new crypto symbol and exchange values.
     *
     * @param scanner The Scanner object for user input
     */
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new CryptoSymbol: ");
        this.setCryptoSymbol(scanner.nextLine());
        System.out.println("Enter new Exchange: ");
        this.setExchange(scanner.nextLine());
    }
}