import java.io.Serializable;

public class AuthService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UserDatabase userDB;

    public AuthService() {
        this.userDB = new UserDatabase();
    }
    
 
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

        public static SignupResult fromException(IllegalArgumentException e) {
            String message = e.getMessage();
            if (message.contains("name")) return INVALID_NAME;
            if (message.contains("email")) return INVALID_EMAIL;
            if (message.contains("password")) return INVALID_PASSWORD;
            return FAILED;
        }
    }

    public static class LoginResult {
        public static final LoginResult INVALID_CREDENTIALS = new LoginResult(null);
        private final User user;

        private LoginResult(User user) {
            this.user = user;
        }

        public static LoginResult success(User user) {
            return new LoginResult(user);
        }

        public boolean isSuccess() {
            return user != null;
        }

        public User getUser() {
            return user;
        }
    }
    public boolean userExists(String userName) {
        return userDB.findUser(userName) != null;
    }
    private boolean isValidName(String name) {
        if (name == null || name.isEmpty() || name.length() >= 100) {
            System.out.println("Invalid name: must be under 100 characters and not empty.");
            return false;
        }
        return true;
    }
    
    private boolean isValidEmail(String email) {
        if (email == null || email.length() >= 100 || 
            !email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Invalid email: must be a valid format and under 100 characters.");
            return false;
        }
        return true;
    }
    
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
    
    private boolean isValidPassword(String password) {
        if (password == null || password.length() >= 100) {
            System.out.println("Password must be under 100 characters.");
            return false;
        }
        if (!password.matches("^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,100}$")) {
            System.out.println("Password must contain at least one uppercase letter and one number or special character.");
            return false;
        }
        return true;
    }

    public boolean isValidUser(User user) {
        return isValidName(user.name) &&
               isValidEmail(user.email) &&
               isValidUsername(user.userName) &&
               isValidPassword(user.password ); 
    }
    public void clear() {
        userDB.clear(); 
    }

    
}