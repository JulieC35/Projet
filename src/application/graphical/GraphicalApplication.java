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
import java.util.*;

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
    private AppController loginController, subscribeController, connectionsListController, connectionAddController, languageSelectionController, userProfileEditController, databaseHomeController, sqlQueryController, tableHomeController, tableStructureController, tableAddController, tableColumnsController, sqlQueryResultController, columnAddController;


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
        this.userProfileEditController = new UserProfileEditController(this, app);
        this.databaseHomeController = new DatabaseHomeController(this, app);
        this.sqlQueryController = new SQLQueryController(this, app);
        this.tableHomeController = new TableHomeController(this, app);
        this.tableStructureController = new TableStructureController(this, app);
        this.tableAddController = new TableAddController(this, app);
        this.tableColumnsController = new TableColumnsController(this, app);
        this.sqlQueryResultController = new SQLQueryResultController(this, app);
        this.columnAddController = new ColumnAddController(this, app);
        
        
        // We first load the login screen
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
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
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
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
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
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
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
        } catch (IllegalStateException ex){
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
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads user profile edit screen
     */
    public void loadUserProfileEditScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/UserProfileEditScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.userProfileEditController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the database main screen
     */
    public void loadDatabaseHomeScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/DatabaseHomeScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.databaseHomeController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the database main screen
     */
    public void loadSQLQueryScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/SQLQueryScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.sqlQueryController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the table main screen
     */
    public void loadTableHomeScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/TableHomeScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.tableHomeController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the sql result screen
     */
    public void loadSQLQueryResultScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/SQLQueryResultScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.sqlQueryResultController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the table structure screen
     */
    public void loadTableStructureScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/TableStructureScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.tableStructureController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the table structure screen
     */
    public void loadTableAddScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/TableAddScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.tableAddController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the table columns form
     */
    public void loadTableColumnsScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/TableColumnsScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.tableColumnsController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    /**
     * Loads the add column form
     */
    public void loadColumnAddScreen(){
        try {
            this.fxmlLoader = new FXMLLoader();
            this.fxmlLoader.setLocation(getClass().getResource("/application/graphical/views/ColumnAddScreen.fxml")); // Loader creation
            this.fxmlLoader.setController(this.columnAddController); // setting the controller
            this.primaryStage.setScene(new Scene((AnchorPane) this.fxmlLoader.load()));
            this.currentScene = this.primaryStage.getScene();
        } catch (IOException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        } catch (IllegalStateException ex){
            this.setMessage(L.get("error-loading-screen") + " : " + ex.getMessage());
            this.displayMessage();
        }
    }

    public boolean askConfirmation(String title){
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        boolean ret = false;
        if ( title != null ) 
            alert.setTitle(title);
        else
            alert.setTitle("Confirmation");

        alert.setHeaderText(L.get("confirmation-dialog"));

        Optional<ButtonType> result = alert.showAndWait();
        if ( result.get() == ButtonType.OK )
            ret = true;

        return ret;
    }

    /**
     * Closes the current panel
     */
    public void closePanel(){
        if ( this.currentScene != null ) {
            this.primaryStage.setScene(this.currentScene);
        }
        else {
            this.setMessage(L.get("reload-current-screen-failure"));
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