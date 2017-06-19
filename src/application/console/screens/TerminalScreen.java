/**
 * Parent of every terminal screen
 */
package application.console.screens;

import application.console.*;
import model.entities.Application;
import lang.*;

public abstract class TerminalScreen{
    protected Application app;
    protected ConsoleApplication terminal;

    /**
     * The constructor of the TerminalScreen and children
     */
    public TerminalScreen(ConsoleApplication terminal, Application app){
        this.terminal = terminal;
        this.app = app;
    }

    /**
     * Clears the terminal and prints default information
     */
    public void initialize(){
        terminal.clear();
        terminal.printHeader();
    }

    /**
     * Gets back to the previous screen of the application
     */
    public void exit(){
        terminal.loadPreviousScreen();
    }
}