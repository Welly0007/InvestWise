/**
 * Test class for verifying user authentication functionality.
 * Contains test cases for both signup and login operations,
 * covering various success and failure scenarios.
 */
public class TestUserDatabase {
    /**
     * Main method that executes all authentication test cases.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        auth.clear();

        // ========== SIGNUP TESTS ==========
        // Case 1: Valid user (should succeed)
        System.out.println("Test 1: Valid user signup");
        AuthService.SignupResult result1 = auth.signUp("Ahmed", "ahmed@example.com", "ahmed123", "Password1*!", "Password1*!");
        System.out.println("Result: " + result1 + " - " + result1.getMessage());
        System.out.println();

        // Case 2: Duplicate username (should fail)
        System.out.println("Test 2: Duplicate username");
        AuthService.SignupResult result2 = auth.signUp("Alice B", "aliceb@example.com", "ahmed123", "Another1!", "Another1!");
        System.out.println("Result: " + result2 + " - " + result2.getMessage());
        System.out.println();

        // Case 3: Invalid email format (should fail)
        System.out.println("Test 3: Invalid email");
        AuthService.SignupResult result3 = auth.signUp("Bob", "bob[at]email", "bobuser", "Secure1!", "Secure1!");
        System.out.println("Result: " + result3 + " - " + result3.getMessage());
        System.out.println();

        // Case 4: Weak password (should fail)
        System.out.println("Test 4: Weak password");
        AuthService.SignupResult result4 = auth.signUp("Charlie", "charlie@email.com", "charlieuser", "password", "password");
        System.out.println("Result: " + result4 + " - " + result4.getMessage());
        System.out.println();

        // Case 5: Very long name (should fail)
        System.out.println("Test 5: Very long name");
        String longName = "A".repeat(101);
        AuthService.SignupResult result5 = auth.signUp(longName, "long@example.com", "longuser", "Valid1@", "Valid1@");
        System.out.println("Result: " + result5 + " - " + result5.getMessage());
        System.out.println();

        // Case 6: Password mismatch (should fail)
        System.out.println("Test 6: Password mismatch");
        AuthService.SignupResult result6 = auth.signUp("Dana", "dana@example.com", "danauser", "Password1!", "Password2!");
        System.out.println("Result: " + result6 + " - " + result6.getMessage());
        System.out.println();

        // ========== LOGIN TESTS ==========
        System.out.println("\n=== LOGIN TEST CASES ===");

        // Case 7: Successful login with correct credentials
        System.out.println("Test 7: Successful login");
        AuthService.LoginResult loginResult1 = auth.login("ahmed123", "Password1*!");
        if (loginResult1.isSuccess()) {
            System.out.println("SUCCESS: Logged in as " + loginResult1.getUser().name);
        } else {
            System.out.println("FAILED: Login should have succeeded");
        }
        System.out.println();

        // Case 8: Wrong password
        System.out.println("Test 8: Wrong password");
        AuthService.LoginResult loginResult2 = auth.login("ahmed123", "WrongPassword!");
        if (!loginResult2.isSuccess()) {
            System.out.println("SUCCESS: Correctly rejected wrong password");
        } else {
            System.out.println("FAILED: Should reject wrong password");
        }
        System.out.println();

        // Case 9: Nonexistent username
        System.out.println("Test 9: Nonexistent username");
        AuthService.LoginResult loginResult3 = auth.login("nonexistent", "Password1!");
        if (!loginResult3.isSuccess()) {
            System.out.println("SUCCESS: Correctly rejected unknown user");
        } else {
            System.out.println("FAILED: Should reject unknown user");
        }
        System.out.println();

        // Case 10: Empty username
        System.out.println("Test 10: Empty username");
        AuthService.LoginResult loginResult4 = auth.login("", "Password1!");
        if (!loginResult4.isSuccess()) {
            System.out.println("SUCCESS: Correctly rejected empty username");
        } else {
            System.out.println("FAILED: Should reject empty username");
        }
        System.out.println();

        // Case 11: Empty password
        System.out.println("Test 11: Empty password");
        AuthService.LoginResult loginResult5 = auth.login("ahmed123", "");
        if (!loginResult5.isSuccess()) {
            System.out.println("SUCCESS: Correctly rejected empty password");
        } else {
            System.out.println("FAILED: Should reject empty password");
        }
    }
}