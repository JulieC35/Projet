/**
 * Login page screen
 */
package application.console.screens;

import application.console.*;
import model.RequestResult;
import model.entities.*;
import lang.*;

public class SubscribeScreen extends TerminalScreen{
    /**
     * Constructor of the home screen
     */
    public SubscribeScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        super.initialize();
        terminal.printTitle(L.get("subscribe"));
        this.subscribe();
        this.exit();
    }

    /**
     * Login Subscribing process
     */
    public void subscribe(){
        for(int i = 0 ; i < 1000000000 ; i++){

        }
    }
}