/**
 * 
 */
package model;

import model.entities.*;
import java.util.*;

public class QueryBuilder{
    private String query;

    /**
     * The constructor
     */
    public QueryBuilder(){
        this.query = "";
    }

    /**
     * @return The formatted query
     */
    public String getQuery(){
        return this.query;
    }

    /**
     * @param tableName The table's name
     */
    public void selectAllFromTable(String tableName){
        StringBuilder sb = new StringBuilder();
        if ( tableName != null ){
            sb.append("SELECT * FROM `" + tableName + "`");
        }

        this.query = sb.toString();
    }

    /**
     * @param table The table
     */
    public void selectAllFromTable(Table table){
        StringBuilder sb = new StringBuilder();
        if ( table != null ){
            sb.append("SELECT * FROM `" + table.getName() + "`");
        }

        this.query = sb.toString();
    }

    /**
     * @param table The table to be created
     */
    public void createTable(Table table){
        StringBuilder sb = new StringBuilder();
        ArrayList<Column> scheme = table.getScheme();

        if ( table != null ){
            sb.append("CREATE TABLE `" + table.getName() + "` (\n");
                for ( int i = 0 ; i < scheme.size() ; i++ ) {
                    sb.append(scheme.get(i));
                    if ( i == scheme.size() - 1 ) 
                        sb.append("\n");
                    else 
                        sb.append(",\n");
                }
            sb.append(")\n");
            sb.append("ENGINE = InnoDB");
        }

        this.query = sb.toString();
    }
}