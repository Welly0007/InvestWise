/**
 * Represents an investor user in the Investment Portfolio System.
 * Extends the base User class with investor-specific functionality
 * and implements Serializable for object persistence.
 */
public class Investor extends User {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new Investor with the specified details.
     * 
     * @param name the full name of the investor
     * @param email the email address of the investor
     * @param userName the username for authentication
     * @param password the password for authentication
     */
    public Investor(String name, String email, String userName, String password) {
        super(name, email, userName, password);
    }

    /**
     * Displays the investor's information in a formatted way.
     * Overrides the base User class implementation to show investor-specific details.
     */
    @Override
    public void showUser() {
        System.out.println("Investor: " + name + " (" + email + ")");
    }
}