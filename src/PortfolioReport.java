import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Generates a detailed report of an investor's portfolios, including asset information
 * and zakat applicability. The report is saved to a file with a timestamp in the filename.
 */
public class PortfolioReport extends ReportGenerator {
    private List<Portfolio> portfolios;
    private Investor investor;

    /**
     * Constructs a PortfolioReport for the specified investor and portfolios.
     *
     * @param portfolios the list of portfolios to include in the report
     * @param investor the investor whose portfolios are being reported
     */
    public PortfolioReport(List<Portfolio> portfolios, Investor investor) {
        super();
        this.portfolios = portfolios;
        this.investor = investor;
    }

    /**
     * Generates a comprehensive portfolio report and saves it to a file.
     * The report includes details for each portfolio, its assets, values,
     * and zakat applicability. The filename is generated automatically with
     * a timestamp.
     */
    @Override
    public void generateReport() {
        String fileName = generateFileName("portfolio_summary");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("=== Portfolio Summary Report ===\n");
            writer.write("Generated on: "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("Investor: " + investor.getName() + "\n\n");

            for (Portfolio portfolio : portfolios) {
                writer.write("Portfolio: " + portfolio.toString() + "\n");
                writer.write(String.format("%-20s %-10s %-15s %-15s %-15s\n",
                        "Asset Name", "Quantity", "Price (USD)", "Total Value", "Zakat Applicable"));
                writer.write("-".repeat(85) + "\n");

                double portfolioTotal = 0;
                for (Asset asset : portfolio.getAssets()) {
                    double totalValue = asset.getPurchasePrice() * asset.getQuantity();
                    portfolioTotal += totalValue;

                    writer.write(String.format("%-20s %-10d %-15.2f %-15.2f %-15s\n",
                            asset.getName(),
                            asset.getQuantity(),
                            asset.getPurchasePrice(),
                            totalValue,
                            asset.isZakatApplicable() ? "Zakat: Yes" : "Zakat: No"));
                }

                writer.write("-".repeat(85) + "\n");
                writer.write(String.format("Portfolio Total Value: $%.2f\n\n", portfolioTotal));
            }

            System.out.println("Portfolio report generated: " + fileName);

        } catch (IOException e) {
            System.err.println("Error generating portfolio report: " + e.getMessage());
        }
    }
}