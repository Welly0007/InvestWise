import java.io.Serializable;
/**
 * The AuthService class provides authentication services including user registration and login.
 * It maintains a user database and validates user credentials according to specified rules.
 * 
 * <p>This class implements Serializable to allow for object serialization.</p>
 */
public class AuthService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UserDatabase userDB;
    /**
     * Constructs a new AuthService with an empty user database.
     */
    public AuthService() {
        this.userDB = new UserDatabase();
    }
    
   /**
     * Registers a new user with the system.
     * 
     * @param name the full name of the user
     * @param email the email address of the user
     * @param username the desired username
     * @param password the user's password
     * @param confirmPassword password confirmation (must match password)
     * @return a SignupResult indicating the outcome of the registration attempt
     */ 
    public SignupResult signUp(String name, String email, String username, 
                          String password, String confirmPassword) {
    
    if (!password.equals(confirmPassword)) {
        return SignupResult.PASSWORD_MISMATCH;
    }

    try {
        Investor newUser = new Investor(name, email, username, password);
        
       
        if (!isValidUser(newUser)) {
            return SignupResult.FAILED; 
        }
        
        if (userDB.addData(newUser)) {
            return SignupResult.SUCCESS;
        } else {
            return SignupResult.DUPLICATE_USERNAME;
        }
    } catch (IllegalArgumentException e) {
        return SignupResult.fromException(e);
    }
}
    /**
     * Logs in a user with the provided credentials.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return a LoginResult indicating the outcome of the login attempt
     */
    public LoginResult login(String username, String password) {
        // 1. Input validation
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty");
            return LoginResult.INVALID_CREDENTIALS;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty");
            return LoginResult.INVALID_CREDENTIALS;
        }
        
        // 2. Format validation (consistent with signup rules)
        if (!isValidUsernameFormat(username)) {
            System.out.println("Invalid username format");
            return LoginResult.INVALID_CREDENTIALS;
        }
        
        // 3. Find user
        User user = userDB.findUser(username);
        if (user == null) {
            System.out.println("User not found");
            return LoginResult.INVALID_CREDENTIALS;
        }
        
        // 4. Password check (using your existing checkPassword method)
        if (!user.checkPassword(password)) {
            System.out.println("Incorrect password");
            return LoginResult.INVALID_CREDENTIALS;
        }
        
        // 5. Return success
        return LoginResult.success(user);
    }
    
    // Helper method to validate username format (without duplicate check)
    private boolean isValidUsernameFormat(String username) {
        return username != null && 
               username.length() < 50 && 
               !username.trim().isEmpty();
    }
    /**
     * Enum representing possible outcomes of a user registration attempt.
     */
    public enum SignupResult {
            SUCCESS("Registration successful!"),
            DUPLICATE_USERNAME("Username already exists"),
            INVALID_NAME("Invalid name format"),
            INVALID_EMAIL("Invalid email format"),
            INVALID_PASSWORD("Password must contain uppercase, number/special char"),
            PASSWORD_MISMATCH("Passwords do not match"),
            FAILED("Registration failed");
        
            private final String message;
        
            SignupResult(String message) {
                this.message = message;
            }
        
            public String getMessage() {
                return message;
            }
        /**
         * Converts an IllegalArgumentException to the appropriate SignupResult.
         * 
         * @param e the exception to convert
         * @return the corresponding SignupResult
         */    
        public static SignupResult fromException(IllegalArgumentException e) {
            String message = e.getMessage();
            if (message.contains("name")) return INVALID_NAME;
            if (message.contains("email")) return INVALID_EMAIL;
            if (message.contains("password")) return INVALID_PASSWORD;
            return FAILED;
        }
    }
    /**
     * Represents the result of a login attempt, which may contain the authenticated user.
     */
    public static class LoginResult {
        public static final LoginResult INVALID_CREDENTIALS = new LoginResult(null);
        private final User user;

        private LoginResult(User user) {
            this.user = user;
        }
        /**
         * Creates a successful login result with the authenticated user.
         * 
         * @param user the authenticated user
         * @return a successful LoginResult containing the user
         */
        public static LoginResult success(User user) {
            return new LoginResult(user);
        }
        /**
         * Checks if the login attempt was successful.
         * 
         * @return true if the login was successful, false otherwise
         */
        public boolean isSuccess() {
            return user != null;
        }
        /**
         * Gets the authenticated user from a successful login.
         * 
         * @return the authenticated user, or null if login failed
         */
        public User getUser() {
            return user;
        }
    }
    /**
     * Checks if a username already exists in the database.
     * 
     * @param userName the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean userExists(String userName) {
        return userDB.findUser(userName) != null;
    }
    /**
     * Validates a user's name according to system rules.
     * 
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    private boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() >= 100) {
            System.out.println("Invalid name: must be under 100 characters and not empty.");
            return false;
        }
        return true;
    }
    /**
     * Validates an email address according to system rules.
     * 
     * @param email the email to validate
     * @return true if the email is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.length() >= 100 || 
            !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Invalid email: must be a valid format and under 100 characters.");
            return false;
        }
        return true;
    }
    /**
     * Validates a username according to system rules.
     * 
     * @param username the username to validate
     * @return true if the username is valid, false otherwise
     */
    private boolean isValidUsername(String username) {
        if (username == null || username.length() >= 50) {
            System.out.println("Invalid username: must be under 50 characters.");
            return false;
        }
        if (userDB.findUser(username) != null) {
            System.out.println("Username already exists.");
            return false;
        }
        return true;
    }
        /**
     * Validates a password according to system security requirements.
     * <p>
     * The password must meet the following criteria:
     * <ul>
     *   <li>Must not be null</li>
     *   <li>Must be between 8 and 100 characters in length</li>
     *   <li>Must contain at least one uppercase letter (A-Z)</li>
     *   <li>Must contain at least one digit (0-9) or special character</li>
     * </ul>
     * </p>
     * 
     * @param password the password string to validate
     * @return {@code true} if the password meets all requirements, {@code false} otherwise
     * 
     * @examples
     * // Returns true
     * isValidPassword("ValidPass123!");
     * isValidPassword("Another$Password9");
     * 
     * // Returns false
     * isValidPassword("weak");              // Too short
     * isValidPassword("nouppercase123");    // Missing uppercase
     * isValidPassword("ALLUPPERCASE");      // Missing digit/special char
     * isValidPassword(null);                // Null input
     */
    private boolean isValidPassword(String password) {
        if (password == null || password.length() < 8 || password.length() > 100) {
            return false;
        }
        
        // Regex explanation:
        // ^           - start of string
        // (?=.*[A-Z]) - at least one uppercase letter
        // (?=.*[\d\W]) - at least one digit or special character
        // .+          - one or more of any characters
        // $           - end of string
        String pattern = "^(?=.*[A-Z])(?=.*[\\d\\W]).+$";
        return password.matches(pattern);
    }
    /**
     * Validates all user attributes according to system rules.
     * 
     * @param user the user to validate
     * @return true if all user attributes are valid, false otherwise
     */
    public boolean isValidUser(User user) {
        return isValidName(user.name) &&
               isValidEmail(user.email) &&
               isValidUsername(user.userName) &&
               isValidPassword(user.password ); 
    }
    /**
     * Clears all user data from the authentication service.
     */
    public void clear() {
        userDB.clear(); 
    }

    
}