package javadb.database;

import javadb.login.loginDatabase;
import javadb.login.loginModel;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tableDatabase {
    private String tblListile = "tblList";
    private String databaseExt = "csv";
    private String tblListlocation = Paths.get(".").toAbsolutePath().normalize().toString()+"/databaseData/tables/"+tblListile+"."+databaseExt;
    private BufferedReader tableListBR = null;
    private BufferedReader tableBR = null;
    private PrintWriter tableListPW;
    private PrintWriter tablePW;
    private tableModel tableData;
    private String tblFileName = "tblList";
    private String tableDatalocation = Paths.get(".").toAbsolutePath().normalize().toString()+"/databaseData/tables/";
    private String templateTableDatalocation = Paths.get(".").toAbsolutePath().normalize().toString()+"/databaseData/tables/";


    public void setTableDatalocation(String tbl_name) {
        this.tableDatalocation = this.templateTableDatalocation + tbl_name + "."+databaseExt;
    }

    public void resetTableDatalocatio() {
        this.tableDatalocation = this.templateTableDatalocation;
    }

    public void tryOpenTableListDatabase () {
        try {
            this.tableListBR = new BufferedReader(new FileReader(this.tblListlocation));
        } catch (FileNotFoundException e) {
            try {
                createTableDatabase(tblListlocation,tblListile,databaseExt);
                this.tableListBR = new BufferedReader(new FileReader(this.tblListlocation));
            } catch (IOException e1) {
                //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e1);
                System.out.println("Cannot create file");
                System.exit(1);
            }
            //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            //e.printStackTrace();
        }
    }

    public void tryOpenTableDatabase (String location,String table_name,String databaseExt) {
        try {
            this.tableBR = new BufferedReader(new FileReader(location+"."+databaseExt));
        } catch (FileNotFoundException e) {
            try {
                createTableDatabase(tblListlocation,tblListile,databaseExt);
                this.tableBR = new BufferedReader(new FileReader(location+"."+databaseExt));
            } catch (IOException e1) {
                //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e1);
                System.out.println("Cannot create file");
                System.exit(1);
            }
            //Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            //e.printStackTrace();
        }
    }

    public void createTableDatabase(String location, String databaseFile, String databaseExt) throws IOException {
        File file = new File(location);
        if (file.createNewFile()) {
            System.out.println("Table Database .... OK");
        } else {
            System.out.println("Table Database .... Already Exist");
        }
    }

    public void resettableBR () {
        this.tableBR = null;
    }

    public void settableBR (String table_name) {
        tryOpenTableDatabase(templateTableDatalocation,table_name,databaseExt);
    }

    private tableModel getTableData (String table_name) {
        if (checkTableExist(table_name)) {
            this.settableBR(table_name);
            tableModel temp = new tableModel();
            temp.setTableName(table_name);
            String[] tableDataType = loadTableDataType_n_KeyFromCSV(table_name).get(1);
            settableBR(table_name);
            List<String> listDatatype = new ArrayList<>();
            for (String s : tableDataType) {
                listDatatype.add(s);
            }
            temp.setDataType(listDatatype);
            temp.setTableContent(loadTableContentFromCSV(table_name));
            /*
             * Fill this to read table data
             * */
            this.resettableBR();
            return temp;
        } else {
            return null;
        }
    }

    private boolean checkTableExist (String table_name) {
        tryOpenTableListDatabase();
        List<String> tableName = loadTableNameFromCSV();
        //boolean isInTblList = false;
        for (String s : tableName) {
            if (s.equalsIgnoreCase(table_name)) {
                return true;
            }
        }
        return false;
    }

    public List<String> loadTableNameFromCSV () {
        List <String> temp_list = new ArrayList<>();
        String temp_strorage;
        try {
            while ((temp_strorage = this.tableListBR.readLine()) != null) {
                String[] dataTableName = temp_strorage.split(",");
                for (int i = 0; i < dataTableName.length ; i++) {
                    dataTableName[i] = removePetik(dataTableName[i]);
                }
                temp_list.add(dataTableName[0]);
            }
            return temp_list;
        } catch (IOException e) {
            Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<String[]> loadTableDataType_n_KeyFromCSV (String table_name) {
        if (tableListBR == null) {
            tryOpenTableListDatabase();
        } else {
            System.out.println("failed to open dir");
            return null;
        }
        List<String[]> temp_list = new ArrayList<>();
        String temp_strorage;
        try {
            while ((temp_strorage = this.tableListBR.readLine()) != null) {
                System.out.println(temp_strorage);
                String[] dataTable = temp_strorage.split(",");
                for (int i = 0; i < dataTable.length ; i++) {
                    dataTable[i] = removePetik(dataTable[i]);
                }
                if (dataTable[0].equals(table_name)) {
                    temp_list.add(dataTable);
                }
                for (String[] s : temp_list) {
                    System.out.print("WTHF MAN : ");
                    for (String d : s) {
                        System.out.print(d);
                        System.out.print("-");
                    }
                    System.out.println("\n");
                }
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

    public List loadTableContentFromCSV (String table_name) {
        List  temp_list = new ArrayList<>();
        String temp_strorage;
        try {
            while ((temp_strorage = this.tableBR.readLine()) != null) {
                String[] dataTableName = temp_strorage.split(",");
                for (int i = 0; i < dataTableName.length ; i++) {
                    dataTableName[i] = removePetik(dataTableName[i]);
                }
                temp_list.add(dataTableName);
            }
            return temp_list;
        } catch (IOException e) {
            Logger.getLogger(loginDatabase.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("thiss run 1");
        tableDatabase x = new tableDatabase();
        System.out.println("thiss run 2");
        List<String[]> y = x.loadTableDataType_n_KeyFromCSV("customer");
        System.out.println("thiss run 3");
        for (String[] s : y) {
            System.out.print("WTHF MAN : ");
            for (String d : s) {
                System.out.print(d);
                System.out.print("-");
            }
            System.out.println("\n");
        }
        System.out.println("thiss run 4");
    }
}
