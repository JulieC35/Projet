/**
 * Rows list screen
 */
package application.console.screens;

import application.console.*;
import library.*;
import library.entities.*;
import lang.*;
import java.util.*;
import java.sql.*;

public class RowsListScreen extends TerminalScreen{
    /**
     * Constructor of the screen
     */
    public RowsListScreen(ConsoleApplication terminal, ApplicationModel app){
        super(terminal, app);
    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + app.getCurrentTable() + " : " + L.get("entries"));
        terminal.printMessage();
        queryBuilder.selectAllFromTable(app.getCurrentTable());
        terminal.printQueryResult(app.processSQL(this.queryBuilder.getQuery()));
        terminal.prompt("Press enter to go back");

        this.exit();
    }
}
