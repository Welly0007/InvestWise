import java.util.List;
/**
 * The UserDatabase class manages persistence and operations for User objects.
 * It extends the generic Database class and provides user-specific functionality.
 * 
 * <p>This class handles storage and retrieval of user data from a serialized file,
 * and implements CRUD operations for User objects.</p>
 */
public class UserDatabase extends Database<User> {
    private static final long serialVersionUID = 3L;
    private List<User> users;
    /**
     * Constructs a new UserDatabase instance.
     * Initializes the database by loading existing users from the serialized file.
     * The file path is hardcoded to "src/database/userDatabase.ser".
     */
    public UserDatabase() {
        super("src/database/userDatabase.ser");
        this.users = loadFromFile();
    }
    /**
     * Adds a new user to the database if it doesn't already exist.
     * 
     * @param newUser The User object to be added to the database
     * @return true if the user was added successfully, false if the user already exists
     */
    @Override
    public boolean addData(User newUser) {
        if (!users.contains(newUser)) {
            users.add(newUser);
            saveToFile(users);
            return true;
        } else {
            System.out.println("User already exists.");
            return false;
        }
    }
    /**
     * Removes a user from the database.
     * 
     * @param user The User object to be removed
     * @return true if the user was removed successfully, false if the user wasn't found
     */
    @Override
    public boolean deleteData(User user) {
        if (users.contains(user)) {
            users.remove(user);
            saveToFile(users);
            return true;
        } else {
            return false;
        }
    }
    /**
     * Updates an existing user's data by replacing old data with new data.
     * 
     * @param oldData The User object to be replaced
     * @param newData The new User object with updated information
     * @return true if the operation was successful, false otherwise
     */
    @Override
    public boolean editData(User oldData, User newData) {
        deleteData(oldData);
        return addData(newData);
    }
    /**
     * Finds a user by their username.
     * 
     * @param userName The username to search for
     * @return The User object if found, null otherwise
     */
    public User findUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    /**
     * Displays all users in the database to the console.
     * Shows a formatted list of all users or a message if no users exist.
     */
    public void displayUsers() {
        System.out.println("Displaying all users:");
        if (users.isEmpty()) {
            System.out.println("No users found.");
        }
        for (User user : users) {
            user.showUser();
        } 
    }
    /**
     * Clears all users from the database.
     * Note: This operation is immediate and persistent (saves to file).
     */
    public void clear() {
        users.clear(); 
    }
}