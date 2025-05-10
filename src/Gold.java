import java.util.Date;
import java.util.Scanner;

/**
 * Represents a gold asset in the investment portfolio.
 * Extends the base Asset class and adds gold-specific properties
 * including karat purity and weight in grams.
 * 
 * <p>This class handles all gold-specific operations and maintains
 * compliance with the asset management system requirements.</p>
 */
public class Gold extends Asset {
    /** The purity of the gold in karats (e.g., "24K", "18K") */
    private String karat;
    
    /** The weight of the gold in grams */
    private float weightInGrams;

    /**
     * Constructs a new Gold asset with all required properties.
     *
     * @param name             The name or description of the gold asset
     * @param quantity         The number of gold items
     * @param purchaseDate     The date when the gold was purchased
     * @param purchasePrice    The purchase price per unit
     * @param zakatApplicable  Boolean indicating if zakat applies to this asset
     * @param karat            The purity of the gold in karats
     * @param weightInGrams    The weight of the gold in grams
     */
    public Gold(String name, int quantity, Date purchaseDate, float purchasePrice,
               Boolean zakatApplicable, String karat, Float weightInGrams) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.karat = karat;
        this.weightInGrams = weightInGrams;
    }

    /**
     * Gets the karat purity of the gold.
     *
     * @return The gold purity as a string (e.g., "24K")
     */
    public String getKarat() {
        return karat;
    }

    /**
     * Sets the karat purity of the gold.
     *
     * @param karat The new karat value (e.g., "18K", "22K")
     */
    public void setKarat(String karat) {
        this.karat = karat;
    }

    /**
     * Gets the weight of the gold in grams.
     *
     * @return The weight in grams as a float value
     */
    public float getWeightInGrams() {
        return weightInGrams;
    }

    /**
     * Sets the weight of the gold in grams.
     *
     * @param weightInGrams The new weight value in grams
     */
    public void setWeightInGrams(float weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    /**
     * Implements the abstract method to edit gold-specific details.
     * Prompts the user for new karat and weight values.
     *
     * @param scanner The Scanner object used for user input
     */
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Karat: ");
        this.setKarat(scanner.nextLine());
        System.out.println("Enter new Weight in Grams: ");
        this.setWeightInGrams(scanner.nextFloat());
    }

    /**
     * Returns a string representation of the gold asset,
     * including both inherited properties and gold-specific properties.
     *
     * @return A formatted string containing all gold asset details
     */
    @Override
    public String toString() {
        return super.toString() + 
               ", Karat: " + karat + 
               ", WeightInGrams: " + weightInGrams;
    }
}