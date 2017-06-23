/**
 * Command line version of the application
 */
package application.console;

import java.util.*;
import java.io.*;

import library.*;
import library.entities.*;
import library.managers.*;
import lang.*;
import application.console.screens.*;

public class ConsoleApplication{
    private ApplicationModel app;
    private TerminalScreen currentScreen;
    private ArrayList<TerminalScreen> stack;
    private String message;

    final String PROMPT = "> ";

    /**
     * Constructor
     */
    public ConsoleApplication(){
        this.app = new ApplicationModel();
        this.message = "";

        // We load the first screen of the application, and the stack stays empty
        this.stack = new ArrayList<TerminalScreen>();
        this.currentScreen = new HomeScreen(this, app);
        this.refresh();
    }

    /**
     * Allows to set the message to a specific string
     */
    public void setMessage(String message){
        if ( message != null ) 
            this.message = message;
    }

    /**
     * Displays the message of the application and removes it afterwards
     */
    public void printMessage(){
        if ( this.message != null && !this.message.equals("") ){
            System.out.println(L.get("feedback") + " : " + this.message);
            this.message = "";
        } else {
            //System.out.println(L.get("feedback") + " : " + L.get("unknown-error") + "\n");
        }
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
     * Loads the previous screen of the application on the top of the stack
     */
    public void loadPreviousScreen(){
        if ( this.stack.size() > 0 ) {
            this.currentScreen = this.stack.remove(this.stack.size() - 1);
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
     * Returns a separator based on a number of columns
     * @param nb The number of columns
     * @return The separator
     */
    public String getSeparator(int nb){
        StringBuilder sb = new StringBuilder();
        if ( nb > 0 ){
            for ( int i = 0 ; i < nb ; i++ )
                sb.append("--------");
        }
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Displays the header of the application
     */
    public void printHeader(){
        this.clear();
        System.out.println("Console mode");
        System.out.println("\n" + L.get("application-copyright"));
        System.out.println("\n" + L.get("application-description"));
        System.out.println("\n" + L.get("instructions") + " :");
        System.out.println(" - sql : " + L.get("instruction-sql"));
        System.out.println(" - back : " + L.get("instruction-back"));
        System.out.println(" - exit : " + L.get("instruction-exit") + "\n");
        if((this.app.getUser() != null) && (this.app.getUser().getAuthorization() == Authorization.ADMIN)){
            System.out.println(" - admin : " + L.get("instructions-admin"));
        }
    }

    /**
     * Allos to print the title of a screen
     */
    public void printTitle(String title){
        if ( title != null ) {
            String border = "---------";
            for(int i = 0 ; i < title.length() ; i++){
                border += "-";
            }
            border += "---------";
            System.out.println(border);
            System.out.println("\t" + title);
            System.out.println(border);
        }
    }

    /**
     * Allos to print the title of a screen
     */
    public void printTitle(String title, String subtitle){
        if ( title != null ) {
            if (subtitle != null )
                title = title + " : " + subtitle;

        String border = "---------";
        for(int i = 0 ; i < title.length() ; i++){
            border += "-";
        }
        border += "---------";
        System.out.println(border);
        System.out.println("\t" + title);
        System.out.println(border);
        }
    }

    /**
     * Allows to display a menu
     * @param list the list of items of the menu
     */
    public void printMenu(String[] list){
        if ( list != null ){
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            int i = 1;
            for(String element : list){
                sb.append(i + " : " + element + "\n");
                i++;
            }
            System.out.println(sb.toString());
        }
     }

    /**
     * Allows to display a list
     * @param list the list of items
     */
    public void printList(ArrayList<String> list){
        if ( list != null && list.size() > 0 ){
            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            int i = 0;
            for(String element : list){
                sb.append(i + " : " + element + "\n");
                i++;
            }
            System.out.println(sb.toString());
        } else 
            System.out.println("\n" + L.get("empty-list"));
    }

    /**
     * Allows to print the result of a query
     */
    public void printQueryResult(QueryResult result){
        System.out.println(L.get("query-result") + " :\n");
        this.setMessage(result.getMessage());
        this.printMessage();

		StringBuilder sb = new StringBuilder();
		for (Column column : result.getScheme()){
			sb.append("\t" + column.getName());
		}
		sb.append("\n");

        sb.append("\t" + getSeparator(result.getScheme().size()));

		for(Row element : result.getRows()){
			sb.append(element + "\n");
		}

		System.out.println(sb.toString() + "\n");
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
        app.logout();
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
            ret = System.console().readLine("\n" + label + " :\n" + PROMPT);
         else
            ret = System.console().readLine("\n" + PROMPT);

        if ( ret.equals("back") ) {
            this.loadPreviousScreen();
        } else if ( ret.equals("exit") ) {
            this.quit();
        }

        return ret;
     }   
    
    /**
     * Asks the user to type something
     * @return The typed information
     */
     public String prompt(){
        return this.prompt(null);
     }   

    /**
     * Asks the user to type a password
     * @return The typed password
     */
     public String promptSecret(String label){
         char[] ret = null;
         if ( label != null && !label.equals("") ) 
            ret = System.console().readPassword("\n" + label + " :\n" + PROMPT);
         else
            ret = System.console().readPassword(PROMPT);
         return new String(ret);
     }

    /**
     * Ask the user to confirm his current action
     * @return true if The user confirmed his action
     */
    public boolean askConfirmation(String message){
        boolean ret = false;
        String answer = ( message != null ) ? this.prompt(message + " " + L.get("boolean-answer")) : this.prompt(L.get("boolean-answer"));
        // If the answer is anything but y, Y, o or O we consider the user refused
        if ( answer.equals("y") || answer.equals("Y") || answer.equals("o") || answer.equals("O") )
            ret = true;

        return ret;
    }

    /**
     * Starts the loop that asks the user to type information
     */
    public void startPrompting(){
        boolean continuePrompting = true;
        String[] currentRequest = null;

        while ( continuePrompting ) {
            // We first get the user's request and parse it into an array of string
            currentRequest = this.parseRequest( this.prompt() );
            // We proceed to the request
            RequestResult result = this.currentScreen.proceedRequest( currentRequest );
            // If the request was to exit, we set the boolean to false. If there was an error and the request could not be executed,
            // we indicate it.
            switch ( result ){
                case BACK:
                    continuePrompting = false;
                break;
                case END:
                    this.clear();
                    this.quit();
                break;
                case ERROR:
                    this.printMessage(); // We print the message if there were an error
                break;
            }
        }
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