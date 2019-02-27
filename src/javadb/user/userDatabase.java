package javadb.user;

import javadb.login.loginModel;

import java.io.*;
import java.nio.file.Paths;

public class userDatabase {
    private String databaseFile = "userData";
    private String databaseExt = "csv";
    private String location = Paths.get(".").toAbsolutePath().normalize().toString()+"/databaseData/"+databaseFile+"."+databaseExt;
    private BufferedReader userBR = null;
    private PrintWriter userPW;
    private userModel userData;

    public userDatabase() {
    }

    public void tryOpenUserDatabase () {
        try {
            this.userBR = new BufferedReader(new FileReader(this.location));
        } catch (FileNotFoundException e) {
            try {
                createUserDatabase(location,databaseFile,databaseExt);
                this.userBR = new BufferedReader(new FileReader(this.location));
            } catch (IOException e1) {
                //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e1);
                System.out.println("Cannot create file");
                System.exit(1);
            }
            //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            //e.printStackTrace();
        }
    }




    public void createUserDatabase(String location, String databaseFile, String databaseExt) throws IOException {
        File file = new File(location);
        if (file.createNewFile()) {
            System.out.println("User Database .... OK");
        } else {
            System.out.println("User Database .... Already Exist");
        }
    }

    public void addUsertoDatabase(userModel uModel) throws Exception{
        String addtoCsv = "\""+uModel.getUsername()+"\",\""+uModel.getPassword()+"\"";
        System.out.println(addtoCsv);
    }
}
