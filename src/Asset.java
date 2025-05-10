import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public abstract class Asset implements Serializable {
    private String name;
    private int quantity;
    private Date purchaseDate;
    private float purchasePrice;
    private Boolean zakatApplicable;

    public Asset(String Name, int Quantity, Date PurchaseDate, float PurchasePrice, Boolean ZakatApplicable) {
        this.name = Name;
        this.quantity = Quantity;
        this.purchaseDate = PurchaseDate;
        this.purchasePrice = PurchasePrice;
        this.zakatApplicable = ZakatApplicable;
    }

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

    // hook method for derived classes to edit specific asset details
    protected abstract void editSpecificDetails(Scanner scanner);

    @Override
    public String toString() {
        return "Name: " + name + ", Quantity: " + quantity + ", PurchaseDate: " + purchaseDate +
               ", PurchasePrice: " + purchasePrice + ", ZakatApplicable: " + zakatApplicable;
    }

    // default getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Boolean isZakatApplicable() {
        return zakatApplicable;
    }

    public void setZakatApplicable(Boolean zakatApplicable) {
        this.zakatApplicable = zakatApplicable;
    }
}
