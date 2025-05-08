public class NormalUser extends User {

    public NormalUser(String name, String email, String userName, String password) {
        super(name, email, userName, password);
    }

    @Override
    public void showUser() {
        System.out.println("User: " + this.name + " | Email: " + this.email + " | Username: " + this.userName);
    }
}