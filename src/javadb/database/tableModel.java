package javadb.database;

import java.util.ArrayList;
import java.util.List;

public class tableModel {
    private String tableName;
    private List<String> dataType;
    private String[] key;
    private List<String[]> tableContent;


    public tableModel() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List getDataType() {
        return dataType;
    }

    public void setDataType(List dataType) {
        this.dataType = dataType;
    }

    public String[] getkey() {
        return key;
    }

    public void setkey(String[] key) {
        this.key = key;
    }

    public List<String[]>  getTableContent() {
        return tableContent;
    }

    public void setTableContent(List<String[]> tableContent) {
        this.tableContent = tableContent;
    }
}
