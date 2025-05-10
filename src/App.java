/**
 * Main application class that serves as the entry point for the Portfolio Management System.
 * Initializes the system with a default investor and starts the portfolio management interface.
 */
public class App {
    /**
     * Main method that launches the portfolio management application.
     * Creates a default investor and initializes the PortfolioManager.
     * 
     * @param args Command line arguments (not currently used)
     */
    public static void main(String[] args) {
        // Initialize portfolio manager with a default investor
        PortfolioManager portManager = new PortfolioManager(
            new Investor("John Doe", "john.doe@example.com", "johndoe", "password123")
        );
        
        // Start the portfolio management interface
        portManager.start();
    }
}
