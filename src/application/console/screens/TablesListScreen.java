/**
 * Connections list screen
 */
package application.console.screens;

import application.console.*;
import model.*;
import model.entities.*;
import lang.*;
import java.util.*;
import java.sql.*;

public class TablesListScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TablesListScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-tables") + " : " + L.get("list"));
        terminal.printMessage();
        terminal.printList(this.generateList());
        terminal.startPrompting();

        this.exit();
    }

    /**
     * Generates a list of the different possible actions
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
                    String tableName = this.generateList().get(tableId);
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
                }
            } catch (NumberFormatException ex){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }

        return ret;
    }
}
