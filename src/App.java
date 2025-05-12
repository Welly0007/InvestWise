/**
 * The main application class that serves as the entry point for the program.
 * This class initializes the necessary services and starts the authentication user interface.
 */
public class App {
    /**
     * The main method that starts the application.
     * It creates instances of AuthService, PortfolioDatabase, and AuthUI,
     * then starts the authentication user interface.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        PortfolioDatabase portfolioDatabase = new PortfolioDatabase();
        AuthUI authUI = new AuthUI(authService, portfolioDatabase);
        authUI.start();
    }
}