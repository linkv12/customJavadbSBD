package javadb.login;

//import java.util.List;

public class loginModel {
    private String username;
    private String password;
//  private List<String>


    public loginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public loginModel() {}


//  GETTER SETTER
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
