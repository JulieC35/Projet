/**
 * Home page screen
 */
package application.console.screens;

import application.console.*;
import model.RequestResult;
import model.entities.*;
import lang.*;

public class HomeScreen extends TerminalScreen{
    /**
     * Constructor of the home screen
     */
    public HomeScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        super.initialize();
        terminal.printTitle(L.get("home-title"));
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
        sb.append("1 : " + L.get("home-connect") + "\n");
        sb.append("2 : " + L.get("home-subscribe") + "\n");
        sb.append("exit\n");
        System.out.println(sb.toString());
    }
    
    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret != RequestResult.END && ret != RequestResult.ERROR ){
            switch ( request[0] ){
                case "1":
                    terminal.setCurrentScreen(new LoginScreen(terminal, app));
                    break;
                case "2": 
                    terminal.setCurrentScreen(new SubscribeScreen(terminal, app));
                    break;
                default:
                    ret = RequestResult.ERROR;
                    break;
            }
        }

        return ret;
    }

    /**
     * Allows to exit the application because it's the first screen
     */
    public void exit(){
        terminal.clear();
        terminal.quit();
    }
}