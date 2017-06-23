/**
 * Connection adding screen
 */
package application.console.screens;

import application.console.*;
import model.entities.*;
import model.*;
import lang.*;

public class ConnectionAddScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public ConnectionAddScreen(ConsoleApplication terminal, ApplicationModel app){
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
        DBConnection ret = new DBConnection();

        while ( !ret.setName(terminal.prompt(L.get("connection-name")) )){
            terminal.setMessage(L.get("not-valid-input"));
            terminal.printMessage();
        }

        while ( !ret.setUsername(terminal.prompt(L.get("username"))) ){
            terminal.setMessage(L.get("not-valid-input"));
            terminal.printMessage();
        }

        while ( !ret.setPassword(terminal.promptSecret(L.get("password"))) ){
            terminal.setMessage(L.get("not-valid-input"));
            terminal.printMessage();
        }

        while ( !ret.setHost(terminal.prompt(L.get("host"))) ){
            terminal.setMessage(L.get("not-valid-input"));
            terminal.printMessage();
        }

        while ( !ret.setDatabaseName(terminal.prompt(L.get("database-name"))) ) {
            terminal.setMessage(L.get("input-too-short"));
            terminal.printMessage();
        }

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
