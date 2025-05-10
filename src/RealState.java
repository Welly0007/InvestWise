import java.util.Date;
import java.util.Scanner;
public class RealState extends Asset {

    String location;
    String propertyType;

    public RealState(String name, int quantity, Date purchaseDate, float purchasePrice, Boolean zakatApplicable, String location, String propertyType) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.location = location;
        this.propertyType = propertyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Location: ");
        this.setLocation(scanner.nextLine());
        System.out.println("Enter new property Type: ");
        this.setPropertyType(scanner.nextLine());
    }
    @Override
    public String toString() {
        return super.toString() + ", Location: " + location + ", PropertyType: " + propertyType;
    }
}
