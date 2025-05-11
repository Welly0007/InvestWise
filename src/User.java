import java.io.Serializable;

/**
 * An abstract base class representing a User in the system.
 * Implements Serializable to support object serialization for persistence.
 * 
 * <p>
 * This class defines common properties and behaviors for all user types,
 * including authentication and display functionality that must be implemented
 * by concrete subclasses.
 * </p>
 */
public abstract class User implements Serializable {
    protected String name;
    protected String email;
    protected String userName;
    protected String password;

    /**
     * Constructs a new User with the specified details.
     * 
     * @param name     the full name of the user
     * @param email    the email address of the user
     * @param userName the unique username for authentication
     * @param password the user's password (stored in plain text)
     */
    public User(String name, String email, String userName, String password) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Compares this user with another object for equality based on username.
     * 
     * @param obj the object to compare with
     * @return true if the objects are equal (same username), false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return this.userName.equals(user.userName);
    }

    /**
     * Gets the user's username.
     * 
     * @return the username string
     */
    public String getUserName() {
        return this.userName;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Verifies if the input password matches the user's password.
     * 
     * @param inputPassword the password to verify
     * @return true if passwords match, false otherwise
     */
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    /**
     * Abstract method to display user information.
     * Must be implemented by concrete subclasses to define
     * user-specific display formats.
     */
    public abstract void showUser();
}
