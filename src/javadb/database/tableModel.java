package javadb.database;

import java.util.ArrayList;
import java.util.List;

public class tableModel {
    private String tableName;
    private List<String> dataType;
    private int idxPK, idxFK;
    private List tableContent;


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

    public int getIdxPK() {
        return idxPK;
    }

    public void setIdxPK(int idxPK) {
        this.idxPK = idxPK;
    }

    public int getIdxFK() {
        return idxFK;
    }

    public void setIdxFK(int idxFK) {
        this.idxFK = idxFK;
    }

    public List getTableContent() {
        return tableContent;
    }

    public void setTableContent(List tableContent) {
        this.tableContent = tableContent;
    }
}
