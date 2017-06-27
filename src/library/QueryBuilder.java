/**
 * Builds SQL queries based on several information types, such as table names, tables, etc.<br><br>
 * Example : <br>
 * dropTable("MyTable");<br><br>
 * Result : <br>
 * DROP TABLE MyTable;
 */
package library;

import library.entities.*;
import java.util.*;
import java.sql.*;

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
     * Builds a a query to select all elements from a table
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
     * Builds a a query to select all elements from a table
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
     * Builds a a query to remove all elements from a table
     * @param table The table
     */
    public void deleteFromTable(String table){
        StringBuilder sb = new StringBuilder();
        if ( table != null ){
            sb.append("DELETE FROM `" + table + "`");
        }

        this.query = sb.toString();
    }

    /**
     * Builds a a query to create a table
     * @param table The table to be created
     */
    public void createTable(Table table){
        StringBuilder sb = new StringBuilder();
        ArrayList<Column> scheme = table.getScheme();

        if ( table != null ){
            sb.append("CREATE TABLE `" + table.getName() + "` (\n");
                for ( int i = 0 ; i < scheme.size() ; i++ ) {
                    sb.append(scheme.get(i).toSQL());
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

    /**
     * Builds a a query to drop a table
     * @param nameTable The table to drop
     */
    public void dropTable(String nameTable){
        if(nameTable != null){
            String requeteText = "DROP TABLE " + nameTable + ";";
            this.query = requeteText;
        }
    }
}