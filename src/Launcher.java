/**
 * Launcher of the application. If console argument is recieved, the GUI is not built
 */
import application.console.*;
import application.graphical.*;
import lang.*;

public class Launcher{
    /**
     * Entry point
     * @param args The list of arguments of the application
     */
    public static void main(String[]args){
        if ( args.length > 0 && args[0].equals("console") )
            new ConsoleApplication();
        else
            GraphicalApplication.main(args);
    }
}