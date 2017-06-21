/**
 * Connection removal screen
 */
package application.console.screens;

import application.console.*;
import model.*;
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
        if ( dbConnections.size() == 0 )
            System.out.println(L.get("empty-list"));
        else {
            for (int i = 0 ; i < dbConnections.size() ; i++){
                System.out.println(i + " : " + dbConnections.get(i).getName());
            }
        }
        sb.append("-----\n");
        sb.append("back\n");
        sb.append("exit\n\n");
        System.out.println(sb.toString());
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){
            try {
                if ( !this.removeConnection(Integer.parseInt(request[0])) )
                    ret = RequestResult.ERROR;
                else
                    ret = RequestResult.BACK; // When the connection is removed, we go back
            } catch (NumberFormatException ex ){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }
        return ret;
    }

    /**
     * Adding a DB to the user's list
     * @param connectionId the id of the connection in the user's list
     * @return true if the connection was removed
     */
    private boolean removeConnection(int connectionId){
        boolean ret = false;

        if ( app.getUser().removeDBConnection(connectionId) ) {
            app.getAuthSystem().saveUsers();
            terminal.setMessage(L.get("connection-removal-success"));
            ret= true;
        }
        else {
            terminal.setMessage(L.get("connection-removal-failure"));
        }

        return ret;
    }
}
