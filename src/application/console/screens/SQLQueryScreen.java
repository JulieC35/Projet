/**
 *
 */

package application.console.screens;

import application.console.*;
import library.*;
import library.entities.*;
import lang.*;

public class SQLQueryScreen extends TerminalScreen {
	public SQLQueryScreen(ConsoleApplication terminal, ApplicationModel app){
		super(terminal, app);
	}

	public void initialize(){
		terminal.printHeader();
		terminal.printTitle(L.get("sql-query"));
		terminal.printMessage();

		while ( true) {
			String query = this.generateQuery();
			terminal.printHeader();
			terminal.printTitle(L.get("sql-query"));
			terminal.printMessage();

			terminal.printQueryResult(app.processSQL(query));
		}
	}

	public String generateQuery(){
		String ret = "";
		String entry = "";
		entry = terminal.prompt();
		if ( !entry.equals(";") )
			ret = entry;

		while ( !entry.equals(";") ) {
			entry = terminal.prompt();
			ret = ret + "\n" + entry;
		}

		return ret;
	}
}