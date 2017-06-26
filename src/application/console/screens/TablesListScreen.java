/**
 * Tables list screen
 */
package application.console.screens;

import application.console.*;
import library.*;
import library.entities.*;
import lang.*;
import java.util.*;
import java.sql.*;

public class TablesListScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TablesListScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + L.get("my-tables") + " : " + L.get("list"));
        terminal.printMessage();
        try {
            terminal.printList(DBConnection.getTablesList(app.getConnection()));
        } catch ( SQLException ex ){
            terminal.setMessage(L.get("sql-error") + "\n" + ex.getMessage());
            terminal.loadPreviousScreen();
        }
        
        terminal.startPrompting();

        this.exit();
    }

    /**
     * Executes the recieved request
     * @param request An array of strings with the command and its parameters
     * @return The result of the request as a RequestResult element
     */
    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){
            int tableId = 0;
            try {
                tableId = Integer.parseInt(request[0]);
                try {
                    String tableName = DBConnection.getTablesList(app.getConnection()).get(tableId);
                    try {
                        app.setCurrentTable(tableName);
                        terminal.setCurrentScreen(new RowsMenuScreen(terminal, app));
                    } catch (SQLException ex) {
                        terminal.setMessage(L.get("table-not-exists"));
                        ret = RequestResult.ERROR;
                    }
                } catch (IndexOutOfBoundsException ex){
                    terminal.setMessage(L.get("not-valid-input"));
                    ret = RequestResult.ERROR;
                } catch (SQLException ex) {
                    terminal.setMessage(L.get("sql-error") + "\n" + ex.getMessage());
                    terminal.loadPreviousScreen();
                }
            } catch (NumberFormatException ex){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }

        return ret;
    }
}
