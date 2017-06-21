/**
 * Table removing screen
 */
package application.console.screens;

import application.console.*;
import model.entities.*;
import model.*;
import lang.*;

import java.sql.*;

public class TableRemoveScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TableRemoveScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
        this.queryBuilder = app.getQueryBuilder();
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-tables") + " : " + L.get("remove") );
        terminal.printMessage();
        this.removeTable(this.requestInformation());
        startPrompting();
        this.exit();
    }

    /**
     * Requesting information to create a Table
     */
    public String requestInformation(){

        String ret = new String();
        ret = terminal.prompt(L.get("table-name"));

        return ret;            
    }

    /**
     * Adding a table to the database
     */
    public void removeTable(String nameTable){
        try{
            DatabaseMetaData metaData = app.getConnection().getMetaData();
            ResultSet result = metaData.getTables(null, null, "%", null);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        if ( nameTable != null ) {
            queryBuilder.dropTable(nameTable);
            System.out.println(queryBuilder.getQuery());
            try{
                Statement state = app.getConnection().createStatement();
                state.executeUpdate(queryBuilder.getQuery());
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}