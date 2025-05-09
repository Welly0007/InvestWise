import java.util.List;

public class UserDatabase extends Database<User> {
    private static final long serialVersionUID = 3L;
    private List<User> users;

    public UserDatabase() {
        super("src/database/userDatabase.ser");
        this.users = loadFromFile();
    }

    @Override
    public boolean addData(User newUser) {
        if (!users.contains(newUser)) {
            if (isValidUser(newUser)) {
                users.add(newUser);
                saveToFile(users);
                return true;
            } else {
                System.out.println("Invalid user data.");
            }
        } else {
            System.out.println("User already exists.");
        }
        return false;
    }

    @Override
    public boolean deleteData(User user) {
        if (users.contains(user)) {
            users.remove(user);
            saveToFile(users);
            return true;
        } else {
            System.out.println("User not found in the database.");
            return false;
        }
    }

    @Override
    public boolean editData(User oldData, User newData) {
        deleteData(oldData);
        return addData(newData);
    }

    public User findUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        System.out.println("User not found.");
        return null;
    }
    public void displayUsers() {
        System.out.println("Displaying all users:");
        if (users.isEmpty()) {
            System.out.println("No users found.");
        }
        for (User user : users) {
            user.showUser();
        } 
    }
    public boolean userExists(String userName) {
        return findUser(userName) != null;
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
        if (users.stream().anyMatch(u -> u.getUserName().equals(username))) {
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

    private boolean isValidUser(User user) {
        return isValidName(user.name) &&
               isValidEmail(user.email) &&
               isValidUsername(user.userName) &&
               isValidPassword(user.password ); 
    }
}