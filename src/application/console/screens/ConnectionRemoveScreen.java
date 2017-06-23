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
    public ConnectionRemoveScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-connections") + " : " + L.get("remove") );
        terminal.printMessage();
        terminal.printList(this.generateList());
        terminal.startPrompting();
        
        this.exit();
    }

    /**
     * Generates the menu of the current screen
     * It is a list of the different possible actions
     * @return The list of strings
     */
    public ArrayList<String> generateList(){
        ArrayList<String> list = new ArrayList<String>();

        for(DBConnection dbC : app.getUser().getDBConnections())
            list.add(dbC.getName());

        return list;
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
            ret = true;
        }
        else {
            terminal.setMessage(L.get("connection-removal-failure"));
        }

        return ret;
    }
}
