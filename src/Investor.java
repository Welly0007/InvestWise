/**
 * Represents an Investor user in the system, extending the base User class.
 * 
 * <p>This concrete class implements the investor-specific behavior,
 * particularly the display format for investor information.</p>
 */
public class Investor extends User{


    /**
     * Constructs a new Investor with the specified details.
     * 
     * @param name the full name of the investor
     * @param email the email address of the investor
     * @param userName the unique username for the investor
     * @param password the investor's password
     */
    public Investor(String name, String email, String userName, String password) {
        super(name, email, userName, password);
    }
    /**
     * Displays the investor's information in a formatted way.
     * 
     * <p>The current implementation shows the investor's name and email
     * in a single line separated by a space.</p>
     * 
     * <p>Example output: "John Doe john.doe@example.com"</p>
     */
    @Override
    public void showUser() {
        System.out.println(name + " "+ email);
    }
}
