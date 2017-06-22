/**
 *
 */

package application.console.screens;

import application.console.*;
import model.*;
import model.entities.*;
import lang.*;

public class SQLQueryScreen extends TerminalScreen {
	public SQLQueryScreen(ConsoleApplication terminal, Application app){
		super(terminal, app);
	}

	public void initialize(){
		terminal.printHeader();
		terminal.printTitle(L.get("sql-query"));

		System.out.println(this.generateQuery());

		terminal.startPrompting();
		this.exit();
	}

	public String generateQuery(){
		String ret = "";
		/*String entry = "";
		entry=terminal.prompt();
		while(entry != ";"){
			entry = terminal.prompt();
			ret = ret + entry;
		}*/
		return ret;
	}


}