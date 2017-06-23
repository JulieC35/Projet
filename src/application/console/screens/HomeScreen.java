/**
 * Home page screen
 */
package application.console.screens;

import application.console.*;
import model.*;
import model.entities.*;
import lang.*;

public class HomeScreen extends TerminalScreen{
    /**
     * Constructor of the home screen
     */
    public HomeScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("home-title"));
        terminal.printMessage();
        terminal.printMenu(new String[]{L.get("home-connect"), L.get("home-subscribe")});
        terminal.startPrompting();
        
        this.exit();
    }
    
    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){            
            switch ( request[0] ){
                case "1":
                    terminal.setCurrentScreen(new LoginScreen(terminal, app));
                    break;
                case "2": 
                    terminal.setCurrentScreen(new SubscribeScreen(terminal, app));
                    break;
                default:
                    terminal.setMessage(L.get("not-valid-input"));
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