/**
 * Connections menu screen
 */
package application.console.screens;

import application.console.*;
import model.*;
import model.entities.*;
import lang.*;

public class ConnectionsMenuScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public ConnectionsMenuScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-connections"));
        terminal.printMessage();
        terminal.printMenu(new String[]{L.get("list"), L.get("add"), L.get("remove")});
        terminal.startPrompting();
        
        this.exit();
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);
        
        if ( ret == RequestResult.OK ){            
            switch ( request[0] ){
                case "1":
                    terminal.setCurrentScreen(new ConnectionsListScreen(terminal, app));
                    break;
                case "2": 
                    terminal.setCurrentScreen(new ConnectionAddScreen(terminal, app));
                    break;
                case "3":
                    terminal.setCurrentScreen(new ConnectionRemoveScreen(terminal, app));
                    break;
                default:
                    terminal.setMessage(L.get("not-valid-input"));
                    ret = RequestResult.ERROR;
                    break;
            }
        }
        
        return ret;
    }

    public void exit(){
        app.logout();
    }
}