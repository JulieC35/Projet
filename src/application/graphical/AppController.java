/**
 * Parent class of every controller of the application
 */
package application.graphical;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import library.*;
import library.entities.*;
import library.managers.*;
import lang.*;

public abstract class AppController {
    protected ApplicationModel app;
    protected GraphicalApplication stage;
    protected QueryBuilder queryBuilder;

    @FXML
    protected Label lbl_message;

    /**
     * The constructor of the controller
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public AppController(GraphicalApplication stage, ApplicationModel app){
        this.stage = stage;
        this.app = app;
        this.queryBuilder = new QueryBuilder();
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        this.lbl_message.setText("");
        stage.displayMessage();
    }    
}