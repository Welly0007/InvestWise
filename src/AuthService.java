import java.io.Serializable;

public class AuthService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final UserDatabase userDB;

    public AuthService() {
        this.userDB = new UserDatabase();
    }
    
    public SignupResult signUp(String name, String email, String username, String password) {
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
    public boolean signUp(String name, String email, String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match.");
            return false;
        }
        Investor newUser = new Investor(name, email, username, password);
        return userDB.addData(newUser);
    }

    public User login(String username, String password) {
        User user = userDB.findUser(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public enum SignupResult {
        SUCCESS,
        DUPLICATE_USERNAME,
        INVALID_NAME,
        INVALID_EMAIL,
        INVALID_PASSWORD,
        FAILED;

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
        if (!password.matches("^(?=.*[A-Z])(?=.*[0-9!@#$%^&*]).+$")) {
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