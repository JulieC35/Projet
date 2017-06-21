/**
 * Connections list screen
 */
package application.console.screens;

import application.console.*;
import model.*;
import model.entities.*;
import lang.*;
import java.util.*;

public class ConnectionsListScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public ConnectionsListScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-connections") + " : " + L.get("list"));
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

    /**
     * Executes the recieved request
     * @param request An array of strings with the command and its parameters
     * @return The result of the request as a RequestResult element
     */
    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){
            try {
                if ( !this.connect(Integer.parseInt(request[0])) )
                    ret = RequestResult.ERROR;
            } catch (NumberFormatException ex ){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }
        return ret;
    }

    /**
     * Asks the application to connect with toe recieved connection
     * @param connectionId the connection id in the user's list
     */
    private boolean connect(int connectionId){
        boolean ret = false;
        DBConnection dbC = app.getUser().getDBConnection(connectionId);
        if ( dbC != null ) {
            app.setConnectionProfile(dbC);
            app.connect();
            terminal.setMessage(L.get("connection-success"));
            terminal.setCurrentScreen(new TablesMenuScreen(terminal, app));
            ret = true;
        }
        else {
            terminal.setMessage(L.get("connection-failure"));
        }
        return ret;
    }
}
