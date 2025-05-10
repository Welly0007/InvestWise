public class App {
    public static void main(String[] args) {
        PortfolioManager portManager = new PortfolioManager(new Investor("John Doe", "john.doe@example.com", "johndoe", "password123"));
        portManager.start();
    }
}
