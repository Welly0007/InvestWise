/**
 * A utility class for calculating and displaying Zakat obligations
 * on investment portfolios according to Islamic financial principles.
 * Zakat is calculated at a fixed rate of 2.5% on applicable assets.
 */
public class ZakatEstimator {
    /** The fixed Zakat rate (2.5%) to be applied to applicable assets */
    private static final double ZAKAT_RATE = 0.025; // 2.5%

    /**
     * Calculates the Zakat obligation for a single asset.
     * Returns zero if the asset is not Zakat-applicable.
     *
     * @param asset the asset to calculate Zakat for
     * @return the Zakat amount for the asset (0 if not applicable)
     * @throws IllegalArgumentException if asset is null
     */
    public double calculateAssetZakat(Asset asset) {
        if (!asset.isZakatApplicable()) {
            return 0.0;
        }

        double assetValue = asset.getPurchasePrice() * asset.getQuantity();
        return assetValue * ZAKAT_RATE;
    }

    /**
     * Calculates the total Zakat obligation for an entire portfolio
     * by summing Zakat for all applicable assets in the portfolio.
     *
     * @param portfolio the portfolio to calculate Zakat for
     * @return the total Zakat amount for the portfolio
     * @throws IllegalArgumentException if portfolio is null
     */
    public double calculatePortfolioZakat(Portfolio portfolio) {
        double totalZakat = 0.0;
        for (Asset asset : portfolio.getAssets()) {
            totalZakat += calculateAssetZakat(asset);
        }
        return totalZakat;
    }

    /**
     * Displays detailed Zakat calculation information for a portfolio
     * including individual asset Zakat amounts and the total obligation.
     * Only shows assets that are Zakat-applicable.
     *
     * @param portfolio the portfolio to display Zakat details for
     * @throws IllegalArgumentException if portfolio is null
     */
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