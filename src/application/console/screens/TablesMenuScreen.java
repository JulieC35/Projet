/**
 * Connections list screen
 */
package application.console.screens;

import application.console.*;
import model.*;
import model.entities.*;
import lang.*;
import java.util.*;

public class TablesMenuScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public TablesMenuScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    }

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + L.get("my-tables"));
        terminal.printMessage();
        terminal.printMenu(new String[]{L.get("list"), L.get("add"), L.get("remove")});
        terminal.startPrompting();

        this.exit();
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);
        DBConnection dbC = null;

        if ( ret == RequestResult.OK ){            
            switch ( request[0] ){
                case "1":
                    terminal.setCurrentScreen(new TablesListScreen(terminal, app));
                    break;
                case "2": 
                    terminal.setCurrentScreen(new TableAddScreen(terminal, app));
                    break;
                case "3":
                    terminal.setCurrentScreen(new TableRemoveScreen(terminal, app));
                    break;
                default:
                    ret = RequestResult.ERROR;
                    break;
            }
        }

        return ret;
    }
}
