/**
 * Connection adding screen
 */
package application.console.screens;

import application.console.*;
import model.RequestResult;
import model.entities.*;
import lang.*;

public class ConnectionAddScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public ConnectionAddScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(L.get("my-connections") + " : " + L.get("add") );
        terminal.printMessage();
        this.addConnection(this.requestInformation());
        this.exit();
    }

    /**
     * Requesting information to create a DBConnection
     */
    public DBConnection requestInformation(){
        DBConnection ret = null;

        String name = "";
        String username = "";
        String password = "";
        String host ="";
        String databaseName = "";

        name = terminal.prompt(L.get("connection-name"));
        while ( name == null || name.equals("") || (name.length() != name.replaceAll("\\s","").length() )){
            System.err.println(L.get("not-valid-input"));
            name = terminal.prompt(L.get("connection-name"));
        }

        username = terminal.prompt(L.get("username"));
        password = terminal.promptSecret(L.get("password"));

        host = terminal.prompt(L.get("host"));
        while ( host == null || host.equals("") ) {
            System.err.println(L.get("input-too-short"));
            host = terminal.prompt(L.get("host"));
        }

        databaseName = terminal.prompt(L.get("database-name"));
        while ( databaseName == null || databaseName.equals("") ) {
            System.err.println(L.get("input-too-short"));
            host = terminal.prompt(L.get("database-name"));
        }

        ret =  new DBConnection(name, username, password, host, databaseName);
        return ret;            
    }

    /**
     * Adding a DB to the user's list
     */
    public void addConnection(DBConnection dbC){
        if ( dbC != null && dbC.testConnectivity() ) {
            app.getUser().addDBConnection(dbC);
            app.getAuthSystem().saveUsers();
            terminal.setMessage(L.get("connection-add-success"));
        }
        else {
            terminal.setMessage(L.get("connection-add-failure"));
        }
    }
}
