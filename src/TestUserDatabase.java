public class TestUserDatabase {
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        auth.clear();

        // Case 1: Valid user (should succeed)
        System.out.println("Test 1: Valid user");
        boolean result1 = auth.signUp("Ahmed", "ahmed@example.com", "ahmed123", "Password1*!", "Password1*!");
        System.out.println("Result: " + result1);
        System.out.println();

        // Case 2: Duplicate username (should fail)
        System.out.println("Test 2: Duplicate username");
        boolean result2 = auth.signUp("Alice B", "aliceb@example.com", "ahmed123", "Another1!", "Another1!"); // same username
        System.out.println("Result: " + result2);
        System.out.println();

        // Case 3: Invalid email format (should fail)
        System.out.println("Test 3: Invalid email");
        boolean result3 = auth.signUp("Bob", "bob[at]email", "bobuser", "Secure1!", "Secure1!");
        System.out.println("Result: " + result3);
        System.out.println();

        // Case 4: Weak password (should fail)
        System.out.println("Test 4: Weak password");
        boolean result4 = auth.signUp("Charlie", "charlie@email.com", "charlieuser", "password", "password");
        System.out.println("Result: " + result4);
        System.out.println();

        // Case 5: Very long name (should fail)
        System.out.println("Test 5: Very long name");
        String longName = "A".repeat(101);
        boolean result5 = auth.signUp(longName, "long@example.com", "longuser", "Valid1@", "Valid1@");
        System.out.println("Result: " + result5);
        System.out.println();
    }
}