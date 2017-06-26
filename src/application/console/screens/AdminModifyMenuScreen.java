/**
 * Users list screen
 */
package application.console.screens;

import application.console.*;
import library.*;
import library.entities.*;
import lang.*;
import java.util.*;

public class AdminModifyMenuScreen extends TerminalScreen{

	public AdminModifyMenuScreen(ConsoleApplication terminal, ApplicationModel app){
		super(terminal, app);
	}

	public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getUser().getUsername() + " : " + L.get("admin-list"));
        terminal.printMessage();
        terminal.printMenu(new String[]{L.get("user-to-admin"), L.get("user-to-restricted")});
        terminal.startPrompting();

        this.exit();
    }

    /**
     * Executes the recieved request
     * @param request An array of strings with the command and its parameters
     * @return The result of the request as a RequestResult element
     */
    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){
            try {
                if (Integer.parseInt(request[0]) == 1){
                    terminal.setCurrentScreen(new AdminUpScreen(terminal, app));
                }
                else if(Integer.parseInt(request[0]) == 2){
                	terminal.setCurrentScreen(new AdminLowScreen(terminal, app));
                }
            } catch (NumberFormatException ex ){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }
        return ret;
    }

}