public class ZakatEstimator {
    private static final double ZAKAT_RATE = 0.025; // 2.5%

    public double calculateAssetZakat(Asset asset) {
        if (!asset.isZakatApplicable()) {
            return 0.0;
        }

        double assetValue = asset.getPurchasePrice() * asset.getQuantity();
        return assetValue * ZAKAT_RATE;
    }

    public double calculatePortfolioZakat(Portfolio portfolio) {
        double totalZakat = 0.0;
        for (Asset asset : portfolio.getAssets()) {
            totalZakat += calculateAssetZakat(asset);
        }
        return totalZakat;
    }

    public void displayZakatDetails(Portfolio portfolio) {
        System.out.println("\n=== Zakat Calculation Details ===");
        System.out.println("Portfolio: " + portfolio);

        for (Asset asset : portfolio.getAssets()) {
            if (asset.isZakatApplicable()) {
                double assetZakat = calculateAssetZakat(asset);
                System.out.printf("Asset: %s - Zakat Amount: %.2f%n",
                        asset.getName(), assetZakat);
            }
        }

        System.out.printf("Total Portfolio Zakat: %.2f%n",
                calculatePortfolioZakat(portfolio));
    }
}