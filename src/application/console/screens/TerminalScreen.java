/**
 * Parent of every terminal screen
 */
package application.console.screens;

import application.console.*;
import model.*;
import lang.*;

public abstract class TerminalScreen{
    protected Application app;
    protected ConsoleApplication terminal;
    protected QueryBuilder queryBuilder;

    /**
     * The constructor of the TerminalScreen
     */
    public TerminalScreen(ConsoleApplication terminal, Application app){
        this.terminal = terminal;
        this.app = app;
    }

    /**
     * Clears the terminal and prints default information
     */
    public abstract void initialize();

    /**
     * Gets back to the previous screen of the application
     */
    public void exit(){
        terminal.loadPreviousScreen();
    }

    /**
     * Starts the loop that asks the user to type information
     */
    protected void startPrompting(){
        boolean continuePrompting = true;
        String[] currentRequest = null;

        while ( continuePrompting ) {
            // We first get the user's request and parse it into an array of string
            currentRequest = terminal.parseRequest(terminal.prompt());
            // We proceed to the request
            RequestResult result = this.proceedRequest( currentRequest );
            // If the request was to exit, we set the boolean to false. If there was an error and the request could not be executed,
            // we indicate it.
            switch ( result ){
                case BACK:
                    continuePrompting = false;
                break;
                case END:
                    terminal.clear();
                    terminal.quit();
                break;
                case ERROR:
                    this.initialize();
                break;
            }
        }
    }

    /**
     * Takes a parsed request and tries to proceed it
     * @param request An array of strings containing the request (at index 0) and its parameters
     * @return The result of the request
     */
    public RequestResult proceedRequest(String[] request){
        RequestResult ret = RequestResult.OK;

        if ( request == null || request.length == 0 )
            ret = RequestResult.ERROR;
        else if ( request[0].equals("back") )
            ret = RequestResult.BACK;
        else if ( request[0].equals("exit") )
            ret = RequestResult.END;

        return ret;
    }
}
