import java.util.Date;
import java.util.Scanner;

/**
 * Represents a real estate asset in the investment portfolio.
 * Extends the base Asset class and adds real estate-specific properties
 * including location and property type.
 */
public class RealState extends Asset {

    /** The physical location of the property */
    private String location;
    
    /** The type of property (e.g., "Residential", "Commercial") */
    private String propertyType;

    /**
     * Constructs a new RealState asset with specified properties.
     *
     * @param name              The name/description of the property
     * @param quantity          The number of properties (typically 1)
     * @param purchaseDate      The date when the property was purchased
     * @param purchasePrice     The purchase price of the property
     * @param zakatApplicable   Whether Zakat applies to this property
     * @param location          The physical location/address of the property
     * @param propertyType      The type of property (e.g., "Apartment", "Villa")
     */
    public RealState(String name, int quantity, Date purchaseDate, float purchasePrice,
                    Boolean zakatApplicable, String location, String propertyType) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.location = location;
        this.propertyType = propertyType;
    }

    /**
     * Gets the location of the property.
     *
     * @return The property's physical location/address
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the property.
     *
     * @param location The new location/address of the property
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the type of property.
     *
     * @return The property type (e.g., "Residential", "Commercial")
     */
    public String getPropertyType() {
        return propertyType;
    }

    /**
     * Sets the type of property.
     *
     * @param propertyType The new property type
     */
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * Implements the abstract method to edit real estate-specific details.
     * Prompts the user for new location and property type values.
     *
     * @param scanner The Scanner object used for user input
     */
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Location: ");
        this.setLocation(scanner.nextLine());
        System.out.println("Enter new Property Type: ");
        this.setPropertyType(scanner.nextLine());
    }

    /**
     * Returns a string representation of the real estate asset,
     * including both inherited properties and real estate-specific properties.
     *
     * @return A formatted string containing all property details
     */
    @Override
    public String toString() {
        return super.toString() + 
               ", Location: " + location + 
               ", PropertyType: " + propertyType;
    }
}