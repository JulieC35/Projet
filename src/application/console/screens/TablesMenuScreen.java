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

        System.out.println(app.getConnectionProfile());
        System.out.println("-----");
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
        DBConnection dbC = null;

        if ( ret != RequestResult.END && ret != RequestResult.ERROR ){            switch ( request[0] ){
                case "1":
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
