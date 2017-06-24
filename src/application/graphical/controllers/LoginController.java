package application.graphical.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import lang.*;
import application.graphical.*;
import library.*;
import library.entities.*;

public class LoginController extends AppController{
    @FXML
    private Label lbl_username;

    @FXML
    private TextField txf_username;

    @FXML
    private Label lbl_password;

    @FXML
    private PasswordField psw_password;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_subscribe;

    public LoginController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_username.setText(L.get("username"));
        this.lbl_password.setText(L.get("password"));
        this.btn_login.setText(L.get("login"));
        this.btn_subscribe.setText(L.get("subscribe"));

    }

    @FXML
    void login(ActionEvent event) {
        if ( app.login(this.txf_username.getText(), this.psw_password.getText()) ){
            stage.setMessage(L.get("welcome") + " " + app.getUser().getFirstName() + " !");
        }
        else {
            stage.setMessage(L.get("login-failed"));
        }
        stage.displayMessage();
    }

    @FXML
    void subscribe(ActionEvent event) {
        stage.loadSubscribeScreen();
    }
}

