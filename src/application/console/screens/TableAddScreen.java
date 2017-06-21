/**
 * Table adding screen
 */
package application.console.screens;

import application.console.*;
import model.entities.*;
import model.*;
import lang.*;

public class TableAddScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TableAddScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
        this.queryBuilder = app.getQueryBuilder();
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-tables") + " : " + L.get("add") );
        terminal.printMessage();
        this.addTable(this.requestInformation());
        startPrompting();
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
                nbColumns = Integer.parseInt(terminal.prompt(L.get("table-nb-columns") + " ?"));
            } catch(Exception ex){

            }
        }

        for ( int i = 0 ; i < nbColumns ; i++ ){
            tempCol = new Column();
            while ( !tempCol.setName(terminal.prompt(L.get("column-name"))) );
            while ( !tempCol.setType(terminal.prompt(L.get("column-type"))) );

            ret.addColumn(tempCol);
        }

        return ret;            
    }

    /**
     * Adding a table to the database
     */
    public void addTable(Table table){
        if ( table != null ) {
            queryBuilder.createTable(table);
            System.out.println(queryBuilder.getQuery());
        }
    }
}
