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
    private Application app;
    private TerminalScreen currentScreen;
    private ArrayList<TerminalScreen> stack;

    final String PROMPT = "> ";

    /**
     * Constructor
     */
    public ConsoleApplication(){
        this.app = new Application();

        // We load the first screen of the application, and the stack stays empty
        this.stack = new ArrayList<TerminalScreen>();
        this.currentScreen = new HomeScreen(this, app);
        this.refresh();
    }

    /**
     * Sets the new currentScreen
     * @param screen The TerminalScreen to be loaded
     */
    public void setCurrentScreen(TerminalScreen screen){
        if ( screen != null ) {
            System.out.println("setCurrentScreen");
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
        System.out.println("loadPreviousScreen");
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
        System.out.println("-------------------------");
        System.out.println("\t" + title);
        System.out.println("-------------------------");
    }

    /**
     * Clearing the terminal
     */
    public void clear() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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
}