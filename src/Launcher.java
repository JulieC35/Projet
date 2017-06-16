/**
 * Launcher of the application. If console argument is recieved, the GUI is not built
 */
import application.*;
import lang.*;

public class Launcher{
    /**
     * Entry point
     * @param args The lsit of arguments of the application
     */
    public static void main(String[]args){
        if ( args.length > 0 && args[0].equals("console") )
            new ConsoleApplication();
        else
            runGUI();
    }

    /**
     * GUI placeholder
     */
    public static void runGUI(){
        System.out.println(L.get("launcher-gui-mode"));
    }
}