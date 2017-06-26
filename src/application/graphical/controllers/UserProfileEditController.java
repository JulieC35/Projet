/**
 * Controller of the user profile edit screen.<br>
 */

package application.graphical.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import lang.*;
import application.graphical.*;
import library.*;
import library.entities.*;
import library.exceptions.*;

public class UserProfileEditController extends AppController{

    @FXML
    private Button btn_connections;

    @FXML
    private Button btn_profile;

    @FXML
    private Button btn_language;

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ButtonBar btnBar_form;

    @FXML
    private Button btn_edit;

    @FXML
    private AnchorPane anc_form;

    @FXML
    private Label lbl_username;

    @FXML
    private TextField txf_username;

    @FXML
    private Label lbl_password;

    @FXML
    private PasswordField psw_password;

    @FXML
    private Label lbl_firstName;

    @FXML
    private TextField txf_firstName;

    @FXML
    private Label lbl_lastName;

    @FXML
    private TextField txf_lastName;

    @FXML
    private Label lbl_emailAddress;

    @FXML
    private TextField txf_emailAddress;

    @FXML boolean passwordEdited;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public UserProfileEditController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.btn_connections.setText(L.get("my-connections"));
        this.btn_profile.setText("> " + L.get("my-profile"));
        this.btn_language.setText(L.get("my-language"));
        this.btn_edit.setText(L.get("edit"));

        this.lbl_title.setText(L.get("my-profile"));
        this.lbl_subtitle.setText("edit");

        this.lbl_username.setText(L.get("username"));
        this.lbl_password.setText(L.get("password") + " (" + L.get("empty-password-instruction").toLowerCase() + ")");
        this.lbl_firstName.setText(L.get("first-name"));
        this.lbl_lastName.setText(L.get("last-name"));
        this.lbl_emailAddress.setText(L.get("email"));

        this.txf_username.setText(app.getUser().getUsername());
        this.txf_username.setEditable(false);
        this.txf_firstName.setText(app.getUser().getFirstName());
        this.txf_lastName.setText(app.getUser().getLastName());
        this.txf_emailAddress.setText(app.getUser().getEmailAddress());
    }

    @FXML
    public void setPasswordEdited(){
        if ( !this.passwordEdited )
            this.passwordEdited = true;
    }

    @FXML
    void edit(ActionEvent event) {
        try{
            if ( this.passwordEdited ) {
                System.out.println("New password saved too");
                app.getUser().setPassword(this.psw_password.getText());
            }

            app.getUser().setFirstName(this.txf_firstName.getText());
            app.getUser().setLastName(this.txf_lastName.getText());
            app.getUser().setEmailAddress(this.txf_emailAddress.getText());

            app.getAuthSystem().saveUsers();
            stage.setMessage(L.get("user-profile-edit-success"));
            stage.displayMessage();
        } catch (UserException ex){
            stage.setMessage(ex.getMessage());
            stage.displayMessage();
        }
    }

}
