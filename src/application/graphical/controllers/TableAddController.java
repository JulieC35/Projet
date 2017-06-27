/**
 * Controller of the table structure screen.<br>
 */

package application.graphical.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.util.*;
import javafx.collections.*;
import java.util.*;
import java.sql.SQLException;
import javafx.beans.binding.*;

import lang.*;
import application.graphical.*;
import application.graphical.views.elements.MenuListCell;
import library.*;
import library.entities.*;

public class TableAddController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private AnchorPane anc_form;

    @FXML
    private Label lbl_name;

    @FXML
    private TextField txf_name;

    @FXML
    private Label lbl_nbCols;

    @FXML
    private TextField txf_nbCols;

    @FXML
    private Button btn_menu_databaseName;

    @FXML
    private Button btn_continueForm;
    
    @FXML
    private Button btn_cancel;


    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public TableAddController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_title.setText(app.getConnectionProfile().getDatabaseName() + " : " + L.get("add"));
        this.lbl_subtitle.setText(app.getConnectionProfile().getUsername() + "@" + app.getConnectionProfile().getHost());
        this.btn_menu_databaseName.setText(app.getConnectionProfile().getDatabaseName().toUpperCase());
        this.lbl_name.setText(L.get("table-name"));
        this.lbl_nbCols.setText(L.get("table-ask-nb-columns"));

        this.btn_continueForm.setText(L.get("continue"));
        this.btn_cancel.setText(L.get("cancel"));
        
        this.integrateMenu(this.lst_menu);
    }

    @FXML
    public void continueForm(){
        try {
            int nbCols = Integer.parseInt(this.txf_nbCols.getText());
            String tableName = this.txf_name.getText();

            try{
                if ( nbCols < 1 )
                    throw new Exception(L.get("columns-count-not-valid"));
                else if ( tableName.length() < 1 ) {
                    throw new Exception(L.get("table-name-not-valid"));
                }
                else {
                    app.postValue("table-name", tableName);
                    app.postValue("columns-count", new Integer(nbCols));
                    stage.loadTableColumnsScreen();
                }
            } catch(Exception ex){
                stage.setMessage(ex.getMessage());
                stage.displayMessage();
            }
        } catch (Exception ex){
            stage.setMessage(L.get("columns-count-not-valid"));
            stage.displayMessage();
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        stage.loadDatabaseHomeScreen();
    }
}
