import java.util.Date;
import java.util.Scanner;
// Asset Factory class used for creating asset
public class AssetFactory {
    public static Asset createAsset() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter asset type (stocks, crypto, real estate, gold): ");
        String assetType = scanner.nextLine();
        System.out.println("Enter asset name: ");
        String assetName = scanner.nextLine();
        System.out.println("Enter asset quantity: ");
        int assetQuantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Date purchaseDate = new Date();
        System.out.println("Enter asset purchase price: ");
        float purchasePrice = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        System.out.println("Is Zakat applicable? (true/false): ");
        boolean zakatApplicable = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        System.out.println("Creating asset with type: " + assetType + " ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        switch (assetType.trim().toLowerCase()) {
            case "crypto":
                System.out.println("Enter CryptoSymbol: ");
                String cryptoSymbol = scanner.nextLine();
                System.out.println("Enter Exchange: ");
                String cryptoExchange = scanner.nextLine();
                return new Crypto(assetName, assetQuantity, purchaseDate, purchasePrice, zakatApplicable, cryptoSymbol, cryptoExchange);
            case "stocks":
                System.out.println("Enter StockSymbol: ");
                String stockSymbol = scanner.nextLine();
                System.out.println("Enter Exchange: ");
                String stockExchange = scanner.nextLine();
                return new Stocks(assetName, assetQuantity, purchaseDate, purchasePrice, zakatApplicable, stockSymbol, stockExchange);
            case "real estate":
                System.out.println("Enter Location: ");
                String location = scanner.nextLine();
                System.out.println("Enter PropertyType: ");
                String propertyType = scanner.nextLine();
                return new RealState(assetName, assetQuantity, purchaseDate, purchasePrice, zakatApplicable, location, propertyType);
            case "gold":
                System.out.println("Enter Karat: ");
                String karat = scanner.nextLine();
                System.out.println("Enter WeightInGrams: ");
                float weightInGrams = scanner.nextFloat();
                return new Gold(assetName, assetQuantity, purchaseDate, purchasePrice, zakatApplicable, karat, weightInGrams);
            default:
                System.out.println("Invalid asset type: " + assetType + ". Asset creation failed.");
                return null;
        }
    }
}