/**
 * Users list screen
 */
package application.console.screens;

import application.console.*;
import library.*;
import library.entities.*;
import lang.*;
import java.util.*;

public class AdminRemoveScreen extends TerminalScreen{

	private ArrayList<String> users;

	public AdminRemoveScreen(ConsoleApplication terminal, ApplicationModel app){
		super(terminal, app);
		users = this.generateList();
	}

	public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getUser().getUsername() + " : " + L.get("admin-list"));
        terminal.printMessage();
        terminal.printList(this.generateList());
        terminal.startPrompting();
        
        this.exit();
    }

    /**
     * Generates a list of the users
     * @return The list of strings
     */
    public ArrayList<String> generateList(){
        ArrayList<User> users = app.getAuthSystem().getUsers();
        ArrayList<String>ret = new ArrayList<String>();
        for(User user : users){
            ret.add(user.getUsername());
        }
        return ret;
    }

    public RequestResult proceedRequest(String[] request){
        RequestResult ret = super.proceedRequest(request);

        if ( ret == RequestResult.OK ){
            try {
                if ( !this.remove(Integer.parseInt(request[0])) )
                    ret = RequestResult.ERROR;
                else
                    ret = RequestResult.BACK; // When the connection is removed, we go back
            } catch (NumberFormatException ex ){
                terminal.setMessage(L.get("not-valid-input"));
                ret = RequestResult.ERROR;
            }
        }
        return ret;
    }

    /**
     * Adding a DB to the user's list
     * @param userId the id of the connection in the user's list
     * @return true if the connection was removed
     */
    private boolean remove(int userId){
        boolean ret = false;
        User user = app.getAuthSystem().getUsers().get(userId);
        if((user != app.getUser()) && (user.getUsername() != "root")){
            if (app.getAuthSystem().removeUser(user)) {
                app.getAuthSystem().saveUsers();
                terminal.setMessage(L.get("user-removal-success"));
                ret = true;
            }
        }else {
            terminal.setMessage(L.get("user-removal-failure"));
        }
        return ret;
    }
}