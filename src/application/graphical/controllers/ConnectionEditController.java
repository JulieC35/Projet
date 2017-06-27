/**
 * Controller of the connection editing screen.<br>
 */

package application.graphical.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.*;
import javafx.util.*;
import javafx.beans.binding.*;

import lang.*;
import application.graphical.*;
import library.*;
import library.entities.*;

public class ConnectionEditController extends AppController{


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
    private Button btn_cancel;

    @FXML
    private Button btn_edit;

    @FXML
    private AnchorPane anc_form;

    @FXML
    private Label lbl_name;

    @FXML
    private TextField txf_name;

    @FXML
    private Label lbl_username;

    @FXML
    private TextField txf_username;

    @FXML
    private Label lbl_password;

    @FXML
    private PasswordField psw_password;

    @FXML
    private Label lbl_host;

    @FXML
    private TextField txf_host;

    @FXML
    private Label lbl_dbName;

    @FXML
    private TextField txf_dbName;

    private DBConnection dbC;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public ConnectionEditController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();
        this.dbC = null;

        try {
            dbC = (DBConnection) app.getPostedValue("db-connection");
        } catch(Exception ex){
            stage.loadUserPanel();
            stage.setMessage(L.get("unknown-error"));
            stage.displayMessage();
        }

        this.btn_connections.setText("> " + L.get("my-connections"));
        this.btn_profile.setText(L.get("my-profile"));
        this.btn_language.setText(L.get("my-language"));

        this.lbl_name.setText(L.get("connection-name"));
        this.lbl_username.setText(L.get("username"));
        this.lbl_password.setText(L.get("password"));
        this.lbl_host.setText(L.get("host"));
        this.lbl_dbName.setText(L.get("database-name"));

        this.lbl_title.setText(L.get("my-connections"));
        this.lbl_subtitle.setText(L.get("edit"));

        this.btn_edit.setText(L.get("edit"));
        this.btn_cancel.setText(L.get("cancel"));

        this.txf_name.setText(dbC.getName());
        this.txf_username.setText(dbC.getUsername());
        this.psw_password.setText(dbC.getPassword());
        this.txf_host.setText(dbC.getHost());
        this.txf_dbName.setText(dbC.getDatabaseName());
    }  
    
    @FXML
    void edit(ActionEvent event) {
        DBConnection temp = new DBConnection();

        if ( !temp.setName(this.txf_name.getText())){
            stage.setMessage(L.get("name-not-valid"));
            stage.displayMessage();
        } else if ( !temp.setUsername(this.txf_username.getText()) ){
            stage.setMessage(L.get("username-not-valid"));
            stage.displayMessage();
        } else if ( !temp.setPassword(this.psw_password.getText()) ){
            stage.setMessage(L.get("not-valid-input") + ": password");
            stage.displayMessage();
        } else if ( !temp.setHost(this.txf_host.getText()) ){
            stage.setMessage(L.get("host-not-valid"));
            stage.displayMessage();
        } else if ( !temp.setDatabaseName(this.txf_dbName.getText()) ) {
            stage.setMessage(L.get("database-name-not-valid"));
            stage.displayMessage();
        } else {
            if ( temp != null && temp.testConnectivity() ) {
                app.getUser().removeDBConnection(dbC);

                this.dbC.setName(temp.getName());
                this.dbC.setUsername(temp.getUsername());
                this.dbC.setPassword(temp.getPassword());
                this.dbC.setHost(temp.getHost());
                this.dbC.setDatabaseName(temp.getDatabaseName());

                app.getUser().addDBConnection(dbC);
                app.getAuthSystem().saveUsers();
                stage.loadUserPanel();
                stage.setMessage(L.get("connection-edit-success"));
                stage.displayMessage();
            }
            else {
                stage.loadUserPanel();
                stage.setMessage(L.get("connection-edit-failure"));
                stage.displayMessage();

            }
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        stage.loadUserPanel();
    }

}
