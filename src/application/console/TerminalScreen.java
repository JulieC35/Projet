/**
 * Parent of every terminal screen
 */
package application.console;

import library.*;
import application.console.screens.*;
import lang.*;
import java.io.*;

public abstract class TerminalScreen{
    protected ApplicationModel app;
    protected ConsoleApplication terminal;
    protected QueryBuilder queryBuilder;

    /**
     * The constructor of the TerminalScreen
     */
    public TerminalScreen(ConsoleApplication terminal, ApplicationModel app){
        this.terminal = terminal;
        this.app = app;
        this.queryBuilder = app.getQueryBuilder();
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
                    terminal.clear();
                    terminal.printHeader();
                    //terminal.printLastTitle();
                    terminal.printMessage();
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
        else if ( request[0].equals("sql") ){
            if ( app.getConnection() != null ) {
                ret = RequestResult.SQL;
                terminal.setCurrentScreen(new SQLQueryScreen(terminal, app));
            } else {
                ret = RequestResult.ERROR;
                terminal.setMessage(L.get("error-no-database-connection"));
            }
        }
        else if (request[0].equals("admin")){
            if((this.app.getUser().getAuthorization() == Authorization.ADMIN) || (this.app.getUser().getAuthorization() == Authorization.SUPERADMIN)){
                ret = RequestResult.ADMIN;
                terminal.setCurrentScreen(new AdminPanelScreen(terminal, app));
            } else{
                ret = RequestResult.ERROR;
            }
        }

        return ret;
    }
}
