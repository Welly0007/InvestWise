import java.util.Scanner;
/**
 * The user interface class for handling authentication and user sessions.
 * Manages the login, registration, and session flow for investors in the Investment Portfolio System.
 * Once authenticated, launches the PortfolioManager for the logged-in investor.
 */
public class AuthUI {
    private final AuthService authService;
    private final Scanner scanner;
    private boolean isRunning;
    private Investor currentUser;  // Changed to Investor 

    /**
     * Constructs an AuthUI instance with required services.
     * 
     * @param authService the authentication service to handle login/registration
     * @param portfolioDatabase the database service for portfolio operations
     */
    public AuthUI(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.currentUser = null;
    }

    /**
     * Starts the authentication user interface loop.
     * Displays the main menu and manages the application lifecycle.
     */
    public void start() {
        System.out.println("=== Welcome to Investment Portfolio System ===");
        
        while (isRunning) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                // Directly launch portfolio management after login
                launchPortfolioManager();
                handleLogout();  // Logout after returning from portfolio manager
            }
        }
        
        scanner.close();
    }

    /**
     * Displays the main menu options for unauthenticated users.
     */
    private void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");
        
        int choice = getIntInput(1, 3);
        
        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleRegistration();
                break;
            case 3:
                isRunning = false;
                System.out.println("Goodbye!");
                break;
        }
    }

    /**
     * Handles the user login process by collecting credentials and authenticating.
     */
    private void handleLogin() {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        AuthService.LoginResult result = authService.login(username, password);
        
        if (result.isSuccess()) {
            currentUser = (Investor) result.getUser();  // Cast to Investor
            System.out.println("\nLogin successful! Loading your portfolios...");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    /**
     * Handles new user registration by collecting user details and validating them.
     */
    private void handleRegistration() {
        System.out.println("\n=== Registration ===");
        System.out.print("Full Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.nextLine();
        
        AuthService.SignupResult result = authService.signUp(
            name, email, username, password, confirmPassword
        );
        
        if (result == AuthService.SignupResult.SUCCESS) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed: " + result.getMessage());
        }
    }

    /**
     * Launches the portfolio management interface for the authenticated investor.
     */
    private void launchPortfolioManager() {
        PortfolioManager portfolioManager = new PortfolioManager(currentUser);
        portfolioManager.start();
    }

    /**
     * Handles user logout by clearing the current session.
     */
    private void handleLogout() {
        currentUser = null;
        System.out.println("You have been logged out.");
    }

    /**
     * Helper method to get validated integer input within a specified range.
     * 
     * @param min the minimum acceptable value
     * @param max the maximum acceptable value
     * @return the validated user input
     */
    private int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.printf("Please enter a number between %d and %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}