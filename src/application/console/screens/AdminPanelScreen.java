/**
 * list of the possibility for the user if he is an administrator
 */
package application.console.screens;

import application.console.*;
import library.*;
import library.entities.*;
import lang.*;
import java.util.*;

public class AdminPanelScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public AdminPanelScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
    }

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getUser().getUsername() + " : " + L.get("admin-panel"));
        terminal.printMessage();
        terminal.printMenu(new String[]{L.get("admin-list"), L.get("admin-modify-access"), L.get("admin-remove")});
        terminal.startPrompting();

        this.exit();
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){            
            switch ( request[0] ){
                case "1":
                    terminal.setCurrentScreen(new AdminListScreen(terminal, app));
                    break;
                case "2": 
                    terminal.setCurrentScreen(new AdminModifyMenuScreen(terminal, app));
                    break;
                case "3":
                    terminal.setCurrentScreen(new AdminRemoveScreen(terminal, app));
                    break;
                default:
                    ret = RequestResult.ERROR;
                    break;
            }
        }

        return ret;
    }
}
