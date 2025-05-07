public class Investor extends User{


    
    public Investor(String name, String email, String userName, String password) {
        super(name, email, userName, password);
    }
    
    @Override
    public void showUser() {
        System.out.println(name + " "+ email);
    }
}
