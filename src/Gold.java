import java.util.Date;
import java.util.Scanner;

public class Gold extends Asset {
    String karat;
    float weightInGrams;

    public Gold(String name, int quantity, Date purchaseDate, float purchasePrice, Boolean zakatApplicable, String karat, Float weightInGrams) {
        super(name, quantity, purchaseDate, purchasePrice, zakatApplicable);
        this.karat = karat;
        this.weightInGrams = weightInGrams;
    }

    public String getKarat() {
        return karat;
    }

    public void setKarat(String karat) {
        this.karat = karat;
    }

    public float getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(float weightInGrams) {
        this.weightInGrams = weightInGrams;
    }
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Karat: ");
        this.setKarat(scanner.nextLine());
        System.out.println("Enter new Weight in Grams: ");
        this.setWeightInGrams(scanner.nextFloat());
    }
    @Override
    public String toString() {
        return super.toString() + ", Karat: " + karat + ", WeightInGrams: " + weightInGrams;
    }
}
