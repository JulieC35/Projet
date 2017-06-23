/**
 * Table adding screen
 */
package application.console.screens;

import application.console.*;
import model.entities.*;
import model.*;
import lang.*;
import java.sql.*;
import java.util.*;

public class AdminLowScreen extends TerminalScreen{

    private ArrayList<String> usersList;

    /**
     * Constructor of the screen
     */
    public AdminLowScreen(ConsoleApplication terminal, Application app){
        super(terminal, app);
        usersList = this.generateList();

    } 

    public void initialize(){
        terminal.printHeader();
        terminal.printTitle(app.getConnectionProfile().getName() + " : " + L.get("admin-list"));
        terminal.printMessage();
        terminal.printList(usersList);
        this.upgrade(this.requestInformation());
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

    /**
     * Requesting information to create a Table
     */
    public int requestInformation(){
        int ret = -1;
        while((ret < 0) || (ret >= usersList.size())){
            try{
                ret = Integer.parseInt(terminal.prompt(L.get("user-down-ask")));    
            } catch(Exception ex){}
        }

        return ret;            
    }

    /**
     * Adding a table to the database
     */
    public boolean upgrade(int index){
        boolean ret = false;
        User user = app.getAuthSystem().getUsers().get(index);
        if(user != null){ 
            switch ( user.getAuthorization() ){
                case RESTRICTED:
                    terminal.setMessage(L.get("already-restricted"));
                break;
                case DEFAULT:
                    user.setAuthorization(Authorization.RESTRICTED);
                    terminal.setMessage(L.get("default-down"));
                    ret = true;
                break;
                case ADMIN:
                    terminal.setMessage(L.get("admin-down"));
                break;
            }
        }
        return ret;
    }
}
