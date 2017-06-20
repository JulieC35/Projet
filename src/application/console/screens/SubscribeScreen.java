/**
 * Subscribe page screen
 */
package application.console.screens;

import application.console.*;
import model.RequestResult;
import model.entities.*;
import lang.*;

public class SubscribeScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public SubscribeScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("subscribe"));
        terminal.printMessage();
        this.subscribe();
        this.exit();
    }

    /**
     * Login Subscribing process
     */
    public void subscribe(){
        String username = null;
        String password = "";
        String firstName = "";
        String lastName = "";
        String emailAddress = "";
        boolean validUsername = false;
        boolean validEmailAddress = false;
        System.out.println(L.get("mandatory-fields") + "\n");

        // Username part
        username = terminal.prompt(L.get("username") + " (*)");
        while ( !validUsername ) {
            if ( username.length() < 2 ) {
                System.err.println(L.get("username-too-short") + " (*)");
                username = terminal.prompt(L.get("username"));
            } else if ( !app.getAuthSystem().checkUsernameAvailability(username) ) {
                System.err.println(L.get("username-not-available"));
                username = terminal.prompt(L.get("username") + " (*)");
            } else {
                validUsername = true;
            }
        }
    
        password = terminal.promptSecret(L.get("password"));
        firstName = terminal.prompt(L.get("first-name"));
        lastName = terminal.prompt(L.get("last-name"));

        // Email address part
        emailAddress = terminal.prompt(L.get("email-address"));
        while ( !validEmailAddress ) {
            if ( app.getAuthSystem().checkEmailAddress(emailAddress) )
                validEmailAddress = true;
            else {
                System.err.println(L.get("email-address-not-valid"));
                emailAddress = terminal.prompt(L.get("email-address"));
            }
        }

        app.getAuthSystem().addUser(new User(username, firstName, lastName, emailAddress, password, null, null, null));
    }
}