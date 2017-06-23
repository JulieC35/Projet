/**
 * Table removing screen
 */
package application.console.screens;

import application.console.*;
import library.entities.*;
import library.*;
import lang.*;

import java.sql.*;
import java.util.*;

public class TableRemoveScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TableRemoveScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
        this.queryBuilder = app.getQueryBuilder();
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + L.get("my-tables") + " : " + L.get("remove") );
        terminal.printMessage();
        terminal.printList(this.generateList());
        terminal.startPrompting();
        this.exit();
    }

    /**
     * Generates a list of the different tables
     * @return The list of strings
     */
    public ArrayList<String> generateList(){
        ArrayList<String> ret = new ArrayList<String>();
        try{
            DatabaseMetaData metaData = app.getConnection().getMetaData();
            ResultSet result = metaData.getTables(null, null, "%", null);

            while(result.next()){
                ret.add(result.getString("TABLE_NAME"));
            }
        } catch(SQLException ex){
            terminal.setMessage(L.get("sql-error") + "\n" + ex.getMessage());
            terminal.loadPreviousScreen();
        }

        return ret;
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){
            try {
                if ( !this.removeTable(Integer.parseInt(request[0])) )
                    ret = RequestResult.ERROR;
                else
                    ret = RequestResult.BACK; // When the table is removed, we go back
            } catch (NumberFormatException ex){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }
        return ret;
    }

    /**
     * Removing a table to the database
     */
    public boolean removeTable(int tableId){
        boolean ret = false;
        String nameTable = "";

        try {
            nameTable = this.generateList().get(tableId);

            if ( nameTable != null ) {
                queryBuilder.dropTable(nameTable);
                //System.out.println(queryBuilder.getQuery());
                try{
                    Statement state = app.getConnection().createStatement();
                    state.executeUpdate(queryBuilder.getQuery());
                    terminal.setMessage(L.get("table-removal-success"));
                    ret = true;
                } catch(SQLException ex){
                    terminal.setMessage(L.get("sql-error") + "\n" + ex.getMessage());
                }
            }
        } catch(IndexOutOfBoundsException ex){
            terminal.setMessage(L.get("not-valid-input"));
        }

        return ret;
    }
}