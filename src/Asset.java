import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
/**
 * An abstract base class representing a financial asset with common properties and behaviors.
 * Implements Serializable to support object serialization.
 */
public abstract class Asset implements Serializable {
    private String name;
    private int quantity;
    private Date purchaseDate;
    private float purchasePrice;
    private Boolean zakatApplicable;
    /**
     * Constructs a new Asset with the specified details.
     *
     * @param Name             The name of the asset
     * @param Quantity         The quantity of the asset
     * @param PurchaseDate     The date when the asset was purchased
     * @param PurchasePrice    The purchase price of the asset
     * @param ZakatApplicable  Whether the asset is eligible for Zakat calculation
     */
    public Asset(String Name, int Quantity, Date PurchaseDate, float PurchasePrice, Boolean ZakatApplicable) {
        this.name = Name;
        this.quantity = Quantity;
        this.purchaseDate = PurchaseDate;
        this.purchasePrice = PurchasePrice;
        this.zakatApplicable = ZakatApplicable;
    }
    /**
     * Allows editing of common asset properties through user input.
     * Uses a Scanner for input and calls the abstract editSpecificDetails method
     * for asset-specific editing.
     */
    public final void editAsset() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name: ");
        this.setName(scanner.nextLine());
        System.out.println("Enter new quantity: ");
        this.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Enter new purchase price: ");
        this.setPurchasePrice(scanner.nextFloat());
        scanner.nextLine();
        System.out.println("Is Zakat applicable? (true/false): ");
        this.setZakatApplicable(scanner.nextBoolean());
        scanner.nextLine();
        // Call the hook method for derived class specific edit
        editSpecificDetails(scanner);
    }
    /**
     * Abstract method to be implemented by derived classes for editing asset-specific details.
     *
     * @param scanner The Scanner object to use for user input
     */
    // hook method for derived classes to edit specific asset details
    protected abstract void editSpecificDetails(Scanner scanner);
     /**
     * Returns a string representation of the asset.
     *
     * @return A string containing the asset's details
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Quantity: " + quantity + ", PurchaseDate: " + purchaseDate +
               ", PurchasePrice: " + purchasePrice + ", ZakatApplicable: " + zakatApplicable;
    }

    /**
     * Gets the name of the asset.
     *
     * @return The asset's name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the asset.
     *
     * @param name The new name for the asset
     */
    public void setName(String name) {
        this.name = name;
    }
     /**
     * Gets the quantity of the asset.
     *
     * @return The quantity of the asset
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Sets the quantity of the asset.
     *
     * @param quantity The new quantity for the asset
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Gets the purchase date of the asset.
     *
     * @return The purchase date
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    /**
     * Gets the purchase price of the asset.
     *
     * @return The purchase price
     */
    public float getPurchasePrice() {
        return purchasePrice;
    }
    /**
     * Sets the purchase price of the asset.
     *
     * @param purchasePrice The new purchase price
     */
    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    /**
     * Checks if the asset is Zakat applicable.
     *
     * @return true if Zakat applies, false otherwise
     */
    public Boolean isZakatApplicable() {
        return zakatApplicable;
    }
    /**
     * Sets whether the asset is Zakat applicable.
     *
     * @param zakatApplicable The Zakat applicability status
     */
    public void setZakatApplicable(Boolean zakatApplicable) {
        this.zakatApplicable = zakatApplicable;
    }
}
