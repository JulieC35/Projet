/**
 * Launcher of the application. If console argument is recieved, the GUI is not built
 */

public class Launcher{
    /**
     * Entry point
     * @param args The lsit of arguments of the application
     */
    public static void main(String[]args){
        if ( args.length > 0 && args[0].equals("console") )
            initConsole();
        else
            initGUI();
    }

    public static void initConsole(){
        System.out.println("Console mode");
    }

    public static void initGUI(){
        System.out.println("GUI mode");
    }
}