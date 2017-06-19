/**
 * Command line version of the application
 */
package application.console;

import java.util.*;
import java.io.*;

import model.*;
import model.entities.*;
import model.managers.*;
import lang.*;
import application.console.screens.*;

public class ConsoleApplication{
    private Application appModel;
    private TerminalScreen currentScreen;
    private ArrayList<TerminalScreen> stack;

    final String PROMPT = "javaMyAdmin> ";

    /**
     * Constructor
     */
    public ConsoleApplication(){
        this.appModel = new Application();

        // We load the first screen of the application, and the stack stays empty
        this.stack = new ArrayList<TerminalScreen>();
        this.currentScreen = new HomeScreen(this, appModel);
        this.refresh();
    }

    /**
     * Sets the new currentScreen
     * @param screen The TerminalScreen to be loaded
     */
    public void setCurrentScreen(TerminalScreen screen){
        if ( screen != null ) {
            if ( !( this.currentScreen instanceof LoginScreen || this.currentScreen instanceof SubscribeScreen ) )
                this.stack.add(this.currentScreen);

            this.currentScreen = screen;
            this.refresh();
        }
    }

    /**
     * Refreshes the terminal to display the current screen
     */
    public void refresh(){
        if ( this.currentScreen != null )
            this.currentScreen.initialize();
    }

    /**
     * Loads the previous screen of the application on the top of the stack
     */
    public void loadPreviousScreen(){
        if ( this.stack.size() > 0 )
            this.setCurrentScreen(this.stack.remove(this.stack.size() - 1));
    }

    /**
     * Displays the header of the application
     */
    public void printHeader(){
        System.out.println("Console mode");
        System.out.println("\n" + L.get("application-copyright"));
        System.out.println("\n" + L.get("application-description") + "\n");
    }

    /**
     * Allos to print the title of a screen
     */
    public void printTitle(String title){
        String border = "---------";
        for(int i = 0 ; i < title.length() ; i++){
            border += "-";
        }
        border += "---------";
        System.out.println(border);
        System.out.println("\t" + title);
        System.out.println(border);
    }

    /**
     * Clearing the terminal
     */
    public void clear() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    /**
     * Allows to exit the application
     */
    public void quit(){
        System.exit(0);
    }

    /**
     * Asks the user to type something
     * @param label An information about what needs to be typed
     * @return The typed information
     */
     public String prompt(String label){
         String ret = "";
         if ( label != null && !label.equals("") ) 
            ret = System.console().readLine(label + " :\n" + PROMPT);
         else
            ret = System.console().readLine(PROMPT);
         return ret;
     }   
    
    /**
     * Asks the user to type something
     * @return The typed information
     */
     public String prompt(){
         return System.console().readLine(PROMPT);
     }   

    /**
     * Asks the user to type a password
     * @return The typed password
     */
     public String promptSecret(String label){
         char[] ret = null;
         if ( label != null && !label.equals("") ) 
            ret = System.console().readPassword(label + " :\n" + PROMPT);
         else
            ret = System.console().readPassword(PROMPT);
         return new String(ret);
     }

    /**
     * Converts the request into an array of String objects
     * The first one is the action, and the others are the parameters
     * The elements are separated with spaces
     * @param request The request
     * @return The parsed request
     */
    public String[] parseRequest(String request){
        return request.split(" ");
    }
}