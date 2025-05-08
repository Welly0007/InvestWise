public class TestUserDatabase {
    public static void main(String[] args) {
        UserDatabase userDB = new UserDatabase();

        // Case 1: Valid user (should succeed)
        System.out.println("Test 1: Valid user");
        User validUser = new NormalUser("Ahmed", "ahmed@example.com", "ahmed123", "Password1*!");
        boolean result1 = userDB.addData(validUser);
        System.out.println("Result: " + result1);
        System.out.println();

        // Case 2: Duplicate username (should fail)
        System.out.println("Test 2: Duplicate username");
        User duplicateUser = new NormalUser("Alice B", "aliceb@example.com", "alice123", "Another1!");
        boolean result2 = userDB.addData(duplicateUser);
        System.out.println("Result: " + result2);
        System.out.println();

        // Case 3: Invalid email format
        System.out.println("Test 3: Invalid email");
        User invalidEmailUser = new NormalUser("Bob", "bob[at]email", "bobuser", "Secure1!");
        boolean result3 = userDB.addData(invalidEmailUser);
        System.out.println("Result: " + result3);
        System.out.println();

        // Case 4: Weak password (no uppercase or special char)
        System.out.println("Test 4: Weak password");
        User weakPasswordUser = new NormalUser("Charlie", "charlie@email.com", "charlieuser", "password");
        boolean result4 = userDB.addData(weakPasswordUser);
        System.out.println("Result: " + result4);
        System.out.println();

        // Case 5: Very long name (over 100 chars)
        System.out.println("Test 5: Very long name");
        String longName = "A".repeat(101);
        User longNameUser = new NormalUser(longName, "long@example.com", "longuser", "Valid1@");
        boolean result5 = userDB.addData(longNameUser);
        System.out.println("Result: " + result5);
        System.out.println();

        // View all users
        System.out.println("Current users in DB:");
        userDB.displayUsers();
    }
}