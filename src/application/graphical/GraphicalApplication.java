/**
 * Central point of the gui application.
 * Stores the primary stage, the controllers and all the methods to switch between the different views.
 */
package application.graphical;

import javafx.application.*;
import javafx.stage.*;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import java.io.IOException;

import lang.*;
import library.*;
import library.entities.*;
import library.managers.*;
import application.graphical.controllers.*;


public class GraphicalApplication extends Application{

    private Stage primaryStage;
    private Scene currentScene;
    private FXMLLoader fxmlLoader;

    private ApplicationModel app;
    private String message;

    // Controllers
    private AppController loginController, subscribeController, connectionsListController, connectionAddController, languageSelectionController;


    /**
     * Entry points of the graphical application
     * @param args The list of parameters of the application
     */
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.app = new ApplicationModel();
        this.message = "";
        this.primaryStage = primaryStage;
        this.fxmlLoader = new FXMLLoader();

        // Controllers
        this.loginController = new LoginController(this, app);
        this.subscribeController = new SubscribeController(this, app);
        this.connectionsListController = new ConnectionsListController(this, app);
        this.connectionAddController = new ConnectionAddController(this, app);
        this.languageSelectionController = new LanguageSelectionController(this, app);

        // First screen
        this.loadLoginScreen();

        //primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.setTitle(L.get("app-name"));
        this.primaryStage.show();
    }

    /**
     * Loads the login screen into the stage
     */
    public void loadLoginScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/LoginScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.loginController); // setting the controller
            this.primaryStage.setScene(new Scene((HBox) fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen"));
            this.displayMessage();
        }
    }

    /**
     * Loads the subscribe screen into the stage
     */
    public void loadSubscribeScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/SubscribeScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.subscribeController); // setting the controller
            this.primaryStage.setScene(new Scene((HBox) this.fxmlLoader.load()));
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen"));
            this.displayMessage();
        }
    }

    /**
     * Loads the subscribe screen into the stage
     */
    public void loadUserPanel(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/ConnectionsListScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.connectionsListController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen"));
            this.displayMessage();
        }
    }

    /**
     * Loads the connection adding screen into the stage
     */
    public void loadAddConnectionScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/ConnectionAddScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.connectionAddController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the language selection screen
     */
    public void loadLanguageSelectionScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/LanguageSelectionScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.languageSelectionController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Closes the current panel
     */
    public void closePanel(){
        if ( this.currentScene != null ) {
            this.primaryStage.setScene(this.currentScene);
        }
        else {
            this.setMessage("Impossible to load the current screen");
            this.displayMessage();
        }
    }

    /**
     * Allows the transmission of a message to the current scene
     * @param message The message to be sent
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Sends the current message of the application to the current scene's message label
     */
    public void displayMessage(){
        try {
            Label lbl_message = (Label) this.primaryStage.getScene().lookup("#lbl_message");
            lbl_message.setText(message);
        } catch (Exception ex){
            if ( this.message != null )
                System.out.println(this.message);
        }
        this.message = "";
    }
}