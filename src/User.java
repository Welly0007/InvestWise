import java.io.Serializable;

public abstract class User implements Serializable {
    protected String name;
    protected String email;
    protected String userName;
    protected String password;
    public abstract void showUser();
    public User(String name, String email, String userName, String password) {
        this.name = name;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
    @Override
    public boolean equals(Object obj){
       if (this == obj) return true;
       if(obj == null || getClass() != obj.getClass()) return false; 
       User user = (User) obj;
       return this.userName.equals(user.userName);
    }
    public String getUserName(){
        return this.userName;
    }
}
