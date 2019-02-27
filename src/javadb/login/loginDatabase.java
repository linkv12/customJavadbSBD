package javadb.login;
import javadb.user.userModel;

import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginDatabase {
    private String databaseFile = "userData";
    private String databaseExt = "csv";
    private String location = Paths.get(".").toAbsolutePath().normalize().toString()+"/databaseData/"+databaseFile+"."+databaseExt;
    private BufferedReader loginBR = null;
    private PrintWriter loginPW;
    private loginModel loginData;

    public loginDatabase() {
    }


    public void tryOpenUserDatabase () {
        try {
            this.loginBR = new BufferedReader(new FileReader(this.location));
        } catch (FileNotFoundException e) {
            try {
                createUserDatabase(location,databaseFile,databaseExt);
                this.loginBR = new BufferedReader(new FileReader(this.location));
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

    public userModel tryLogin (loginModel loginData) {
        tryOpenUserDatabase();
        //boolean isLoginValid = false;
        if (loginBR != null) {
            String temp_strorage;
            try {
                while ((temp_strorage = this.loginBR.readLine()) != null) {
                    String[] dataUser = temp_strorage.split(",");
                    for (int i = 0; i < dataUser.length ; i++) {
                        dataUser[i] = removePetik(dataUser[i]);
                    }
                    //System.out.println("comparing username from csv : " + dataUser[0] + " with " + loginData.getUsername());
                    //System.out.println("comparing password from csv : " + dataUser[1] + " with " + loginData.getPassword());
                    //System.out.println("privilege from csv : " + dataUser[2]);
                    if (dataUser[0].equals(loginData.getUsername()) && dataUser[1].equals(loginData.getPassword())) {
                        return (new userModel(dataUser[0],dataUser[1],dataUser[2]));
                    }
                }
                return null;
            } catch (IOException e) {
                Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
                return null;
            }

        } else {
            System.out.println("Failed to login ..... ");
            return null;
        }
    }

    public static void main(String[] args) {
        loginDatabase x = new loginDatabase();
        System.out.println(x.location);
        x.tryOpenUserDatabase();
        System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());
        if ((x.tryLogin(new loginModel("SYSTEM","1"))) != null) {
            System.out.println("Login success .... ");
        } else{
            System.out.println("Login failed  .... ");
        }
    }

    private String removePetik (String s) {
        // will remove " form end and start of a string
        String[] result = s.split("\"");
        //for (String x : result) {
        //    System.out.println("Element result : " + x);
        //}
        return result[1];
    }

/*
    public static void main(String[] args) {
        String csvFile = Paths.get(".").toAbsolutePath().normalize().toString() + "/databaseData/" + "/country.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */
}
