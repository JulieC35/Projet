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
import java.net.URL;
import javafx.fxml.FXMLLoader;

import lang.*;
import application.graphical.*;
import application.graphical.views.elements.MenuListCell;
import library.*;
import library.entities.*;

public class ColumnAddController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private Button btn_menu_databaseName;

    @FXML
    private Button btn_add;
    
    @FXML
    private Button btn_cancel;

    @FXML
    private Label lbl_name;

    @FXML
    private TextField txf_name;

    @FXML
    private Label lbl_type;

    @FXML
    private TextField txf_type;

    @FXML
    private CheckBox chk_primary;

    @FXML
    private CheckBox chk_notNull;

    @FXML
    private CheckBox chk_unique;

    @FXML
    private CheckBox chk_autoInc;


    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public ColumnAddController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_title.setText(app.getCurrentTable() + " : " + L.get("column-add"));
        this.lbl_subtitle.setText(app.getConnectionProfile().getUsername() + "@" + app.getConnectionProfile().getHost());
        this.btn_menu_databaseName.setText(app.getConnectionProfile().getDatabaseName().toUpperCase());

        this.btn_add.setText(L.get("add"));
        this.btn_cancel.setText(L.get("cancel"));

        this.lbl_name.setText(L.get("column-name"));
        this.lbl_type.setText(L.get("column-type"));
        this.chk_primary.setText(L.get("column-primary"));
        this.chk_notNull.setText(L.get("column-notnull"));
        this.chk_unique.setText(L.get("column-unique"));
        this.chk_autoInc.setText(L.get("column-autoincrement"));
        
        this.integrateMenu(this.lst_menu);
    }

    @FXML
    public void add(){
        Column col = null;

        try {
            col = new Column();
            col.setName(this.txf_name.getText());
            col.setType(this.txf_type.getText());
            col.setPrimary(false);
            col.setNotNull(this.chk_notNull.isSelected());
            col.setUnique(this.chk_unique.isSelected());
            col.setAutoIncrement(false);


            queryBuilder.alterTableAddColumn(app.getCurrentTable(), col);
            QueryResult result = app.processSQL(queryBuilder.getQuery());
            if ( !result.getMessage().equals("") )
                throw new Exception(result.getMessage());
            
            stage.loadTableStructureScreen();
            stage.setMessage(L.get("column-add-success"));
            stage.displayMessage();
        } catch(Exception ex){
            stage.setMessage(ex.getMessage());
            stage.displayMessage();
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        stage.loadTableHomeScreen();
    }
}
