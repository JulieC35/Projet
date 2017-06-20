/**
 * Login page screen
 */
package application.console.screens;

import application.console.*;
import model.RequestResult;
import model.entities.*;
import lang.*;

public class LoginScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public LoginScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("login"));
        terminal.printMessage();
        this.login();
        this.exit();
    }

    /**
     * Login process
     */
    public void login(){
        String username = null;
        String password = null;

        username = terminal.prompt(L.get("username"));
        password = terminal.promptSecret(L.get("password"));

        // app.login(String, String) returns false if the login failed
        /*while (!app.login(username, password)) {
            System.out.println(L.get("login-failed"));
            username = terminal.prompt(L.get("username"));
            password = terminal.promptSecret(L.get("password"));
        }*/
        if ( app.login(username, password) ){
            terminal.setCurrentScreen(new ConnectionsMenuScreen(terminal, app));
            terminal.setMessage(L.get("welcome") + " " + app.getUser().getFirstName() + " !");
        }
        else {
            terminal.setMessage(L.get("login-failed"));
        }
            
    }
}