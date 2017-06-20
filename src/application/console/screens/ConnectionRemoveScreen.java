/**
 * Connection removal screen
 */
package application.console.screens;

import application.console.*;
import model.RequestResult;
import model.entities.*;
import lang.*;
import java.util.*;

public class ConnectionRemoveScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public ConnectionRemoveScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-connections") + " : " + L.get("remove") );
        terminal.printMessage();
        this.printList();
        this.startPrompting();
        this.exit();
    }

    /**
     * Displays the menu of the current screen
     * It is a list of the different possible actions
     */
    public void printList(){
        StringBuilder sb = new StringBuilder();
        ArrayList<DBConnection> dbConnections = app.getUser().getDBConnections();
        for (int i = 0 ; i < dbConnections.size() ; i++){
            System.out.println(dbConnections.get(i).getName());
        }
        sb.append("-----\n");
        sb.append("back\n");
        sb.append("exit\n\n");
        System.out.println(sb.toString());
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret != RequestResult.BACK && ret != RequestResult.END && ret != RequestResult.ERROR ){
            this.removeConnection(request[0]);
            ret = RequestResult.BACK; // When the connection is removed, we go back
        }

        return ret;
    }

    /**
     * Adding a DB to the user's list
     */
    public void removeConnection(String connectionName){
        if ( connectionName != null && app.getUser().removeDBConnection(connectionName) ) {
            app.getAuthSystem().saveUsers();
            app.getAuthSystem().saveUsers();
            terminal.setMessage(L.get("connection-removal-success"));
        }
        else {
            terminal.setMessage(L.get("connection-removal-failure"));
        }
    }
}
