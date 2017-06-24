/**
 * Controller of the Subscribe screen.<br>
 * Handles access to the model to try registering the user
 */

package application.graphical.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import lang.*;
import application.graphical.*;
import library.*;
import library.entities.*;
import library.exceptions.UserException;

public class SubscribeController extends AppController{

    @FXML
    private Label lbl_username;

    @FXML
    private TextField txf_username;

    @FXML
    private Label lbl_password;

    @FXML
    private PasswordField psw_password;

    @FXML
    private Button btn_subscribe;

    @FXML
    private Button btn_cancel;

    @FXML
    private Label lbl_message;

    @FXML
    private Label lbl_firstName;

    @FXML
    private TextField txf_firstName;

    @FXML
    private Label lbl_lastName;

    @FXML
    private TextField txf_lastName;

    @FXML
    private Label lbl_email;

    @FXML
    private TextField txf_email;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public SubscribeController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_username.setText(L.get("username"));
        this.lbl_password.setText(L.get("password"));
        this.lbl_firstName.setText(L.get("first-name"));
        this.lbl_lastName.setText(L.get("last-name"));
        this.lbl_email.setText(L.get("email-address"));

        this.btn_subscribe.setText(L.get("subscribe"));
        this.btn_cancel.setText(L.get("cancel"));

    }

    /**
     * Returns to the login page
     */
    @FXML
    void login(ActionEvent event) {
        stage.loadLoginScreen();
    }

    /**
     * Registers the user
     */
    @FXML
    void subscribe(ActionEvent event) {
        try {
            String username = this.txf_username.getText();
            if ( !app.getAuthSystem().checkUsernameAvailability(username) )
                throw new UserException(L.get("username-not-available"));

            String firstName = this.txf_firstName.getText();
            String lastName = this.txf_lastName.getText();
            String email = this.txf_email.getText();
            String password = this.psw_password.getText();

            // If the data are not valid here, an UserException will be thrown by the User class
            app.getAuthSystem().addUser(new User(username, firstName, lastName, email, password, null, null, null));

            stage.setMessage(L.get("subscribal-succes"));
            stage.loadLoginScreen();
        } catch (UserException ex){
            stage.setMessage(ex.getMessage());
            stage.displayMessage();
        }
    }

}
