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
        this.printMenu();
        this.startPrompting();
        this.exit();
    }

    /**
     * Displays the menu of the current screen
     * It is a list of the different possible actions
     */
    public void printMenu(){
        StringBuilder sb = new StringBuilder();
        sb.append("1 : " + L.get("list") + "\n");
        sb.append("2 : " + L.get("add") + "\n");
        sb.append("3 : " + L.get("remove") + "\n");
        sb.append("-----\n");
        sb.append("back\n");
        sb.append("exit\n\n");
        System.out.println(sb.toString());
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret != RequestResult.BACK && ret != RequestResult.END && ret != RequestResult.ERROR ){
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
                    ret = RequestResult.ERROR;
                    break;
            }
        }

        return ret;
    }

    public void exit(){
        app.logout();
        terminal.loadPreviousScreen();
    }
}