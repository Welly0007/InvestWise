import java.util.List;
import java.util.Scanner;

/**
 * Manages portfolio operations for an investor, including creating, viewing,
 * editing, and removing portfolios and their assets.
 * Provides a user-friendly console interface for portfolio management.
 */
public class PortfolioManager {
    private Investor investor;
    private PortfolioDatabase portfolioDatabase;
    private List<Portfolio> portfolios;
    private ZakatEstimator zakatEstimator;
    private ZakatReport zakatReport;
    private PortfolioReport portfolioReport;
    private Scanner scanner;

    /**
     * Constructs a PortfolioManager for the specified investor.
     * Initializes the portfolio database and loads the investor's portfolios.
     *
     * @param investor The investor whose portfolios will be managed
     */
    public PortfolioManager(Investor investor) {
        this.investor = investor;
        this.portfolioDatabase = new PortfolioDatabase();
        this.portfolios = portfolioDatabase.getUserPortfolios(investor);
        this.zakatEstimator = new ZakatEstimator();
        this.zakatReport = null;
        this.portfolioReport = new PortfolioReport(portfolios, investor);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the portfolio management interface.
     * Displays the main menu and processes user input until exit is requested.
     */
    public void start() {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\n========================================");
                System.out.println("           Portfolio Management          ");
                System.out.println("========================================");
                System.out.println("1. Add Portfolio");
                System.out.println("2. View Portfolios");
                System.out.println("3. Remove Portfolio");
                System.out.println("4. Manage Portfolio");
                System.out.println("5. Generate Portfolio Report");
                System.out.println("6. Exit");
                System.out.println("========================================");
                System.out.print("Choose an option (1-5): ");
                int choice = getValidIntInput();

                switch (choice) {
                    case 1:
                        addPortfolio();
                        break;
                    case 2:
                        viewPortfolios();
                        break;
                    case 3:
                        removePortfolio();
                        break;
                    case 4:
                        managePortfolio();
                        break;
                    case 5:
                        generatePortfolioReport();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("\n Exiting Portfolio Management...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }

    /**
     * Adds a new portfolio with the specified name for the investor.
     * Updates the portfolio list after creation.
     */
    private void addPortfolio() {
        System.out.print("Enter Portfolio Name: ");
        String portName = scanner.nextLine();
        Portfolio newPortfolio = new Portfolio(investor, portfolioDatabase, portName);
        System.out.println("Portfolio added successfully: " + newPortfolio);
        portfolios = portfolioDatabase.getUserPortfolios(investor);
    }

    /**
     * Displays all portfolios belonging to the investor.
     * Shows a message if no portfolios exist.
     */
    private void viewPortfolios() {
        if (portfolios.isEmpty()) {
            System.out.println("No portfolios found.");
        } else {
            System.out.println("Portfolios:");
            for (int i = 0; i < portfolios.size(); i++) {
                System.out.println((i + 1) + ". " + portfolios.get(i));
            }
        }
    }

    /**
     * Removes a selected portfolio after confirmation.
     * Displays the portfolio list and prompts for selection.
     */
    private void removePortfolio() {
        if (portfolios.isEmpty()) {
            System.out.println("No portfolios to remove.");
            return;
        }
        viewPortfolios();
        System.out.print("Enter the number of the portfolio to remove: ");
        int choice = getValidIntInput();
        if (choice < 1 || choice > portfolios.size()) {
            System.out.println("Invalid choice. Returning to menu.");
            return;
        }
        Portfolio portfolioToRemove = portfolios.get(choice - 1);
        System.out.print("Are you sure you want to remove this portfolio? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("yes")) {
            portfolioDatabase.deleteData(portfolioToRemove);
            portfolios = portfolioDatabase.getUserPortfolios(investor);
            System.out.println("Portfolio removed successfully: " + portfolioToRemove);
        } else {
            System.out.println("Portfolio removal canceled.");
        }
    }

    /**
     * Generates a comprehensive report of all portfolios and their assets.
     */
    private void generatePortfolioReport() {
        if (portfolios.isEmpty()) {
            System.out.println("No portfolios available for report generation.");
            return;
        }

        System.out.println("\nGenerating portfolio report...");
        portfolioReport = new PortfolioReport(portfolios, investor);
        portfolioReport.generateReport();
    }

    /**
     * Manages a selected portfolio.
     * Displays the portfolio list and prompts for selection.
     */
    private void managePortfolio() {
        if (portfolios.isEmpty()) {
            System.out.println("No portfolios available to manage.");
            return;
        }
        viewPortfolios();
        System.out.print("Enter the number of the portfolio to manage: ");
        int choice = getValidIntInput();
        if (choice < 1 || choice > portfolios.size()) {
            System.out.println("Invalid choice. Returning to menu.");
            return;
        }
        Portfolio selectedPortfolio = portfolios.get(choice - 1);
        manageSinglePortfolio(selectedPortfolio);
    }

    /**
     * Provides asset management interface for a specific portfolio.
     * Displays asset management menu and processes user input.
     *
     * @param portfolio The portfolio to manage
     */
    private void manageSinglePortfolio(Portfolio portfolio) {
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\n========================================");
                System.out.println("       Managing Portfolio: " + portfolio + "       ");
                System.out.println("========================================");
                System.out.println("1. Add Asset");
                System.out.println("2. View Assets");
                System.out.println("3. Edit Asset");
                System.out.println("4. Remove Asset");
                System.out.println("5. Calculate Zakat");
                System.out.println("6. Return to Main Menu");
                System.out.println("========================================");
                System.out.print("Choose an option (1-6): ");
                int choice = getValidIntInput();

                switch (choice) {
                    case 1:
                        portfolio.addAsset();
                        break;
                    case 2:
                        portfolio.viewAssets();
                        break;
                    case 3:
                        portfolio.viewAssets();
                        System.out.print("Enter the name of the asset to edit: ");
                        String assetName = scanner.nextLine();
                        portfolio.editAsset(assetName);
                        break;
                    case 4:
                        portfolio.viewAssets();
                        System.out.print("Enter the name of the asset to remove: ");
                        String assetNameToRemove = scanner.nextLine();
                        Asset assetToRemove = portfolio.getAssetByName(assetNameToRemove);
                        if (assetToRemove != null) {
                            System.out.print("Are you sure you want to remove this asset? (yes/no): ");
                            String confirmation = scanner.nextLine().trim().toLowerCase();
                            if (confirmation.equals("yes")) {
                                portfolio.removeAsset(assetToRemove);
                                System.out.println("Asset removed successfully: " + assetToRemove);
                            } else {
                                System.out.println("Asset removal canceled.");
                            }
                        } else {
                            System.out.println("Asset not found.");
                        }
                        break;
                    case 5:
                        zakatEstimator.displayZakatDetails(portfolio);
                        System.out.print("Would you like to generate a Zakat report? (yes/no): ");
                        String generateReport = scanner.nextLine().trim().toLowerCase();
                        if (generateReport.equals("yes")) {
                            zakatReport = new ZakatReport(portfolio);
                            zakatReport.generateReport();
                        }
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
    }

    /**
     * Validates and retrieves integer input from the user.
     * Continues prompting until valid integer input is received.
     *
     * @return The validated integer input
     */
    private int getValidIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline
        return input;
    }
}