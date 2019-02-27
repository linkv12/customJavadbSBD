package res.script;

import javadb.login.loginDatabase;
import javadb.login.loginModel;
import javadb.user.userModel;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class commandDatabase {
    private String databaseFile = "command";
    private String databaseExt = "csv";
    private String location = Paths.get(".").toAbsolutePath().normalize().toString()+"/databaseData/"+databaseFile+"."+databaseExt;
    private BufferedReader commandBR = null;
    //private PrintWriter loginPW;
    //private loginModel loginData;

    public void tryOpenCommandDatabase () {
        try {
            this.commandBR = new BufferedReader(new FileReader(this.location));
        } catch (FileNotFoundException e) {
            try {
                createCommandDatabase(location,databaseFile,databaseExt);
                this.commandBR = new BufferedReader(new FileReader(this.location));
            } catch (IOException e1) {
                //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e1);
                System.out.println("Cannot create file");
                System.exit(1);
            }
            //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            //e.printStackTrace();
        }
    }

    public void createCommandDatabase(String location, String databaseFile, String databaseExt) throws IOException {
        File file = new File(location);
        if (file.createNewFile()) {
            System.out.println("Command Database .... OK");
        } else {
            System.out.println("Command Database .... Already Exist");
        }
    }

    public BufferedReader getCommandBR () {
        if (commandBR == null) {
            try {
                commandBR = new BufferedReader(new FileReader(this.location));
            } catch (IOException e) {
                System.out.println("Error in getCommand;commandDatabase.java");
                //e.printStackTrace();
                commandBR = null;
            }
            return commandBR;
        } else {
            return commandBR;
        }
    }

    public List<String[]> loadCommandFromCSV () {
        List <String[]> temp_list = new ArrayList<>();
        String temp_strorage;
        try {
            while ((temp_strorage = this.commandBR.readLine()) != null) {
                String[] dataCommand = temp_strorage.split(",");
                for (int i = 0; i < dataCommand.length ; i++) {
                    dataCommand[i] = removePetik(dataCommand[i]);
                }
                temp_list.add(dataCommand);
            }
            return temp_list;
        } catch (IOException e) {
            Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private String removePetik (String s) {
        // will remove " form end and start of a string
        if (s.contains("\"")) {
            String[] result = s.split("\"");
            for (String x : result) {
                System.out.println("Element result : " + x);
            }
            return result[1];
        } else {
            return s;
        }
    }

}
