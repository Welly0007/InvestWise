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
            if (userDB.addData(newUser)) {
                return SignupResult.SUCCESS;
            }
            return SignupResult.FAILED;
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
}