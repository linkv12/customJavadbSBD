package res.script;

import javadb.database.tableDatabase;
import javadb.login.loginDatabase;
import javadb.login.loginModel;
import javadb.user.userDatabase;
import javadb.user.userModel;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class command {
    private List<String[]> commandList;
    private boolean isInit = false;
    private commandDatabase cmd = null;



    public command() {
        this.cmd = new commandDatabase();
        init();
    }

    public void runCommand(){

    }

    private void init () {
        if (cmd == null) {
            cmd = new commandDatabase();
            commandList = loadCommand();
        } else {
            commandList = loadCommand();
        }
    }

    private List<String[]> loadCommand() {
        BufferedReader br = cmd.getCommandBR();
        if (br.equals(null)) {
            System.out.println("Failed loading command ....");
            System.exit(1);
            return null;
        } else {
            this.commandList = cmd.loadCommandFromCSV();
            /*
            for (String[] s : commandList) {
                System.out.println("List is : ");
                for (String d : s) {
                    System.out.println("s contain : " + d);
                }
            }*/
            return commandList;
        }
    }

    private void execHelp () {
        if (this.commandList.isEmpty()) {
            //System.out.println("not running on base");
            commandList = loadCommand();
            for (String[] s : commandList) {
                //System.out.println("List is : ");
                for (int i = 0; i < s.length; i++) {
                    if (i == 2) {
                        System.out.print("\t"+s[i]);
                    } else if (i == 1) {
                        System.out.println("\t: " + s[i]);
                    }
                }
            }
        } else {
            //System.out.println("On base");
            for (String[] s : commandList) {
                //System.out.println("List is : ");
                for (int i = 0; i < s.length; i++) {
                    if (i == 1) {
                        System.out.print("\t"+s[i]);
                    } else if (i == 2) {
                        System.out.println("\t: " + s[i]);
                    }
                }
            }
        }
    }


    public void execCommand(String command_raw,mainCLInterface active_session) {
        if (command_raw.equalsIgnoreCase("help()")) {
            execHelp();
        } else if (command_raw.equalsIgnoreCase("logout()")) {
            active_session.setLogin(false);
            active_session.setCurentUser(null);
        } else if (command_raw.equalsIgnoreCase("exit()")) {
            active_session.setLogin(false);
            active_session.setCurentUser(null);
            System.out.println("Closing........");
            System.exit(0);
        } else if (command_raw.equalsIgnoreCase("login()")) {
            Scanner strScanner = new Scanner(System.in);
            System.out.print("Username : ");
            String uname = strScanner.nextLine();
            System.out.print("Password : ");
            String pwd = strScanner.nextLine();
            active_session.setCurentUser(new loginDatabase().tryLogin(new loginModel(uname,pwd)));
            if (active_session.getCurentUser() == null) {
                System.out.println("Login failed ....");
                System.out.println("User : \tNULL");
            } else {
                System.out.println("Login success....");
                System.out.println("User : \t" + active_session.getCurentUser().getUsername());
                active_session.setLogin(true);
            }
        } else {
            System.out.println("1st : "+command_raw);
            if (command_raw.charAt(command_raw.length()-1) == ';') {
                command_raw = removeLastChar(command_raw);
            }
            System.out.println("2nd : "+command_raw);
            String[] parsedCommandList = command_raw.split(" ");
            for(String s : parsedCommandList) {
                System.out.println("---" + s);
            }
            if (parsedCommandList[0].equals("create") && parsedCommandList[1].equals("user") && parsedCommandList[3].equals("identified") && parsedCommandList[4].equals("by")) {
                try {
                    new userDatabase().addUsertoDatabase(new userModel(parsedCommandList[2],parsedCommandList[5]));
                } catch (Exception e) {
                    System.out.println("failed...");
                }
            } else if (parsedCommandList[0].equalsIgnoreCase("select")) {
                if (parsedCommandList[1].equals("*")) {
                    // will print all table
                    new tableDatabase().printAllTable();
                } else {
                    // will print a table with t_name
                    new tableDatabase().printTable(parsedCommandList[1]);
                }
            }
        }
    }

    public static void main(String[] args) {
        command x = new command();
        x.loadCommand();
        x.execHelp();
        System.out.println("-----------------------------EOF");
    }

    @NotNull
    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

}
