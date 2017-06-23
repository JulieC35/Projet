/**
 * Table adding screen
 */
package application.console.screens;

import application.console.*;
import library.entities.*;
import library.*;
import lang.*;
import java.sql.*;

public class TableAddScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TableAddScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
        this.queryBuilder = app.getQueryBuilder();
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + L.get("my-tables") + " : " + L.get("add") );
        terminal.printMessage();
        
        this.addTable(this.requestInformation());
        this.exit();
    }

    /**
     * Requesting information to create a Table
     */
    public Table requestInformation(){
        Table ret = new Table();
        Column tempCol = null;
        int nbColumns = -1;
        
        while( !ret.setName(terminal.prompt(L.get("table-name"))) );

        while ( nbColumns < 0 ) {
            try {
                nbColumns = Integer.parseInt(terminal.prompt(L.get("table-ask-nb-columns")));
            } catch(Exception ex){

            }
        }
        for ( int i = 0 ; i < nbColumns ; i++ ){
            terminal.printHeader();
            terminal.printTitle(L.get("my-tables") + " : " + L.get("add") );
            
            tempCol = new Column();
            while ( !tempCol.setName(terminal.prompt(L.get("column-ask-name"))) );
            while ( !tempCol.setType(terminal.prompt(tempCol.getName() + " : " + L.get("column-ask-type"))) );

            // If the table has already a primary key, we just ask if the col is unique and/or not null
            if ( !ret.hasPrimary() )
                tempCol.setPrimary(terminal.askConfirmation(tempCol.getName() + " : " + L.get("column-ask-primary")));
                if ( tempCol.isPrimary() )
                    tempCol.setAutoIncrement(terminal.askConfirmation(tempCol.getName() + " : " + L.get("column-ask-autoincrement")));
            else {
                tempCol.setUnique(terminal.askConfirmation(tempCol.getName() + " : " + L.get("column-ask-unique")));
                tempCol.setNotNull(terminal.askConfirmation(tempCol.getName() + " : " + L.get("column-ask-notnull")));
            }

            ret.addColumn(tempCol);
        }

        return ret;            
    }

    /**
     * Adding a table to the database
     */
    public void addTable(Table table){
        if ( table != null ) {
            Statement stm = null;
            try {
                stm = app.getConnection().createStatement();

                // We build the query with the recieved table
                queryBuilder.createTable(table);

                // And execute it in the new statement
                stm.executeUpdate(queryBuilder.getQuery());
                terminal.setMessage(L.get("table-add-success"));
            } catch (SQLException ex){
                terminal.setMessage(L.get("sql-error") + "\n" + ex.getMessage());
            } finally{
                terminal.loadPreviousScreen();
            }
        }
    }
}
