import java.util.Date;
import java.util.Scanner;

public class Gold extends Asset {
    String karat;
    String weightInGrams;

    public Gold(String name, int quantity, Date purchaseDate, float purchasePrice, Boolean zakatApplicable, String karat, String weightInGrams) {
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

    public String getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(String weightInGrams) {
        this.weightInGrams = weightInGrams;
    }
    @Override
    protected void editSpecificDetails(Scanner scanner) {
        System.out.println("Enter new Karat: ");
        this.setKarat(scanner.nextLine());
        System.out.println("Enter new Weight in Grams: ");
        this.setWeightInGrams(scanner.nextLine());
    }
    @Override
    public String toString() {
        return super.toString() + ", Karat: " + karat + ", WeightInGrams: " + weightInGrams;
    }
}
