public class App {
    public static void main(String[] args) {
        UserDatabase dbase = new UserDatabase();
        System.out.println("User database initialized!");
        dbase.displayUsers();
        // dbase.addData(new Investor("Ali", "email", "ali", "1234")); // Corrected method name
        dbase.deleteData(new Investor("Ali", "email", "ali", "1234"));
        dbase.displayUsers();
    }
}
