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

    public SubscribeController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }
    
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

    @FXML
    void login(ActionEvent event) {
        stage.loadLoginScreen();
    }

    @FXML
    void subscribe(ActionEvent event) {
        /*if ( app.getAuthSystem().addUser(new User(username, firstName, lastName, emailAddress, password, null, null, null)) ) {
            stage.setMessage();
            stage.displayMessage();
        } else {            
            stage.setMessage();
            stage.displayMessage();
            stage.loadLoginScreen();
        }*/
    }

}
