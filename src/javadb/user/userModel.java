package javadb.user;

public class userModel {
    private String username;
    private String password;
    private String privilege_raw;
    private String[] listPrivilege;


    public userModel(String username, String password, String privilege_raw) {
        this.username = username;
        this.password = password;
        this.privilege_raw = privilege_raw;
        this.listPrivilege = privilege_raw.split("/+");
    }

    public userModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public userModel() {
    }

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

    public String getPrivilege_raw() {
        return privilege_raw;
    }

    public void setPrivilege_raw(String privilege_raw) {
        this.privilege_raw = privilege_raw;
    }

    public String[] getListPrivilege() {
        return listPrivilege;
    }

    public void setListPrivilege(String[] listPrivilege) {
        this.listPrivilege = listPrivilege;
    }
}
