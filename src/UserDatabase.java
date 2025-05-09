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
            users.add(newUser);
            saveToFile(users);
            return true;
        } else {
            System.out.println("User already exists.");
            return false;
        }
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
    public void clear() {
        users.clear(); 
    }
}