package res.script;

import javadb.login.loginDatabase;
import javadb.login.loginModel;
import javadb.user.userModel;

import java.util.Scanner;

public class mainCLInterface {
    private userModel curentUser = null;
    private boolean isLogin = false;
    private boolean shouldRunning = true;

    public userModel getCurentUser() {
        return curentUser;
    }

    public void setCurentUser(userModel curentUser) {
        this.curentUser = curentUser;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void runCLI () {
        Scanner strScanner = new Scanner(System.in);
        String command = null;
        while (shouldRunning) {
            if (curentUser != null) {
                System.out.println("User : \t" + curentUser.getUsername());
            } else {
                System.out.println("User : \tNULL");
                System.out.print("javadb>");
                command = strScanner.nextLine();
                if ((command.equalsIgnoreCase("login()")) || command.equalsIgnoreCase("exit()") || command.equalsIgnoreCase("help()")) {
                    try {
                        new command().execCommand(command,this);
                    } catch (Exception e) {
                        System.out.println("Failed to execute command : " + command);
                    }
                }
            }
            System.out.print("javadb>");
            command = strScanner.nextLine();
            new command().execCommand(command,this);
        }
    }
}

/*
    public void runCLI () {
        Scanner strScanner = new Scanner(System.in);
        while (shouldRunning) {
            if (curentUser != null) {
                System.out.println("User : \t" + curentUser.getUsername());
            } else {
                System.out.println("User : " + "\tNULL");
                System.out.println("javadb>");
                System.out.print("Username : ");
                String uname = strScanner.nextLine();
                System.out.print("Password : ");
                String pwd = strScanner.nextLine();

                //if ((new loginDatabase().tryLogin(new loginModel(uname,pwd)) != null)
                curentUser = new loginDatabase().tryLogin(new loginModel(uname,pwd));
                if (curentUser != null) {
                    System.out.println("Login success\t....");
                    setLogin(true);
                    System.out.println("User : \t" + curentUser.getUsername());
                } else {
                    //System.out.println("User : " + "\tNULL");
                    System.out.println("Login failed\t....");
                }
            }
            //System.out.println(curentUser.getUsername());
            if (isLogin) {
                System.out.print("javadb>");
                String command = strScanner.nextLine();
                try {
                    new command().execCommand(command,this);
                } catch (Exception e) {
                    System.out.println("Failed to execute command : " + command);
                }
            }
        }
    }*/
