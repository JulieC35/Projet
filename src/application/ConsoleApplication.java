/**
 * 
 */
package application;

import java.util.*;
import java.io.*;

import model.*;
import model.managers.*;
import model.entities.*;
import lang.*;

public class ConsoleApplication{
    private UserManager authSystem;
    private Application app;

    /**
     * Constructor
     */
    public ConsoleApplication(){
		authSystem = new UserManager();
        app = new Application();
        User tempUser = null;

        System.out.println(L.get("launcher-console-mode"));
        Console console = System.console();
        System.out.println(L.get("launcher-login"));
        do {
            String username = console.readLine("--> " + L.get("username") + " ? ");
            char[] password = console.readPassword("--> " + L.get("password") + " ? ");
            tempUser = authSystem.authenticate(username, new String(password) );
        } while (tempUser == null);
        System.out.print(tempUser);
    }
}