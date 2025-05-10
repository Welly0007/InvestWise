import java.util.Scanner;

/**
 * A console-based user interface for the authentication system.
 * This class provides text-based interaction for user authentication
 * including login and registration functionality.
 */
public class AuthUI {
    private final AuthService authService;
    private final Scanner scanner;
    private boolean isRunning;
    private User currentUser;

    /**
     * Constructs an AuthUI with the specified AuthService.
     * @param authService the authentication service to use
     */
    public AuthUI(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.currentUser = null;
    }

    /**
     * Starts the authentication user interface.
     */
    public void start() {
        System.out.println("=== Welcome to Authentication System ===");
        
        while (isRunning) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                showUserMenu();
            }
        }
        
        scanner.close();
    }

    /**
     * Displays the main menu for unauthenticated users.
     */
    private void showMainMenu() {
        System.out.println("Authentication menu:");
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
     * Displays the menu for authenticated users.
     */
    private void showUserMenu() {
        System.out.println("\nWelcome, " + currentUser.name + "!");
        System.out.println("1. View Profile");
        System.out.println("2. Logout");
        System.out.print("Select an option: ");
        
        int choice = getIntInput(1, 2);
        
        switch (choice) {
            case 1:
                displayUserProfile();
                break;
            case 2:
                handleLogout();
                break;
        }
    }

    /**
     * Handles the user login process.
     */
    private void handleLogin() {
        System.out.println("\n=== Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        AuthService.LoginResult result = authService.login(username, password);
        
        if (result.isSuccess()) {
            currentUser = result.getUser();
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Invalid credentials.");
        }
    }

    /**
     * Handles the user registration process.
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
     * Displays the current user's profile information.
     */
    private void displayUserProfile() {
        System.out.println("\n=== Your Profile ===");
        System.out.println("Name: " + currentUser.name);
        System.out.println("Email: " + currentUser.email);
        System.out.println("Username: " + currentUser.userName);
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Handles user logout.
     */
    private void handleLogout() {
        currentUser = null;
        System.out.println("You have been logged out.");
    }

    /**
     * Gets validated integer input from the user.
     * @param min minimum valid value
     * @param max maximum valid value
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

    /**
     * The main entry point for the authentication UI.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        AuthUI authUI = new AuthUI(authService);
        authUI.start();
    }
}