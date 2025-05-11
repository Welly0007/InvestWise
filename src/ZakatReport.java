import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A report generator that creates detailed Zakat calculation reports for investment portfolios.
 * The report includes asset values, Zakat eligibility, and individual/aggregate Zakat amounts.
 */
public class ZakatReport extends ReportGenerator {
    private Portfolio portfolio;
    private ZakatEstimator zakatEstimator;

    /**
     * Constructs a ZakatReport for the specified portfolio.
     * Initializes the Zakat estimator utility.
     *
     * @param portfolio the portfolio to generate the Zakat report for
     */
    public ZakatReport(Portfolio portfolio) {
        super();
        this.portfolio = portfolio;
        this.zakatEstimator = new ZakatEstimator();
    }

    /**
     * Generates a comprehensive Zakat report and saves it to a file.
     * The report includes:
     * - Header with generation timestamp and portfolio information
     * - Detailed table of assets showing values and Zakat calculations
     * - Summary of total Zakat payable
     * The file is saved with a timestamped filename in the reports directory.
     */
    @Override
    public void generateReport() {
        String fileName = generateFileName("zakat_report");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Zakat Calculation Report ===\n");
            writer.write("Generated on: "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("Portfolio: " + portfolio.toString() + "\n");
            writer.write("Owner: " + portfolio.getOwner().getName() + "\n\n");

            writer.write(String.format("%-20s %-15s %-15s %-15s\n",
                    "Asset Name", "Value (USD)", "Zakat Eligible", "Zakat Amount"));
            writer.write("-".repeat(70) + "\n");

            double totalZakat = 0;
            for (Asset asset : portfolio.getAssets()) {
                double assetValue = asset.getPurchasePrice() * asset.getQuantity();
                double zakatAmount = asset.isZakatApplicable() ? zakatEstimator.calculateAssetZakat(asset) : 0;
                totalZakat += zakatAmount;

                writer.write(String.format("%-20s %-15.2f %-15s %-15.2f\n",
                        asset.getName(),
                        assetValue,
                        asset.isZakatApplicable() ? "Yes" : "No",
                        zakatAmount));
            }

            writer.write("-".repeat(70) + "\n");
            writer.write(String.format("\nTotal Zakat Payable: $%.2f\n", totalZakat));

            System.out.println("Zakat report generated: " + fileName);

        } catch (IOException e) {
            System.err.println("Error generating Zakat report: " + e.getMessage());
        }
    }
}