/**
 * 
 */

package application.console.screens;

import application.console.*;
import lang.*;
import library.*;
import library.entities.*;

public class RowsMenuScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public RowsMenuScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
    }

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + app.getCurrentTable());
        terminal.printMessage();
        terminal.printMenu(new String[]{L.get("entries"), L.get("add"), L.get("remove")});
        terminal.startPrompting();

        this.exit();
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){            
            switch ( request[0] ){
                case "1":
                    terminal.setCurrentScreen(new RowsListScreen(terminal, app));
                    break;
                case "2": 
                    
                    break;
                case "3":
                    
                    break;
                default:
                    ret = RequestResult.ERROR;
                    break;
            }
        }

        return ret;
    }
}