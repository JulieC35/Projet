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

public class TableColumnsController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private ScrollPane scr_form;

    @FXML
    private Button btn_menu_databaseName;

    @FXML
    private Button btn_validate;
    
    @FXML
    private Button btn_cancel;

    private Integer columnsCount;
    private String tableName;
    private ArrayList<AnchorPane> inputsGroup;


    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public TableColumnsController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.inputsGroup = new ArrayList<AnchorPane>();

        try{
            this.columnsCount = (Integer) app.getPostedValue("columns-count");
            this.tableName = (String) app.getPostedValue("table-name");
        } catch(Exception ex){
            stage.loadDatabaseHomeScreen();
            stage.setMessage(L.get("unknown-error"));
            stage.displayMessage();
        }

        this.lbl_title.setText(app.getConnectionProfile().getDatabaseName() + " : " + L.get("add") + " " + this.tableName);
        this.lbl_subtitle.setText(app.getConnectionProfile().getUsername() + "@" + app.getConnectionProfile().getHost());
        this.btn_menu_databaseName.setText(app.getConnectionProfile().getDatabaseName().toUpperCase());

        this.btn_validate.setText(L.get("validate"));
        this.btn_cancel.setText(L.get("cancel"));
        
        this.integrateMenu(this.lst_menu);

        this.generateForm();
    }

    @FXML
    public void validate(){
        Table table = new Table();
        table.setName(this.tableName);
        Column col = null;

        try {
            for ( AnchorPane gp : this.inputsGroup ){
                col = new Column();
                col.setName(((TextField) gp.lookup("#txf_name")).getText());
                col.setType(((TextField) gp.lookup("#txf_type")).getText());
                col.setPrimary(((CheckBox) gp.lookup("#chk_primary")).isSelected());
                col.setNotNull(((CheckBox) gp.lookup("#chk_notNull")).isSelected());
                col.setUnique(((CheckBox) gp.lookup("#chk_unique")).isSelected());
                col.setAutoIncrement(((CheckBox) gp.lookup("#chk_autoInc")).isSelected());

                table.addColumn(col);
            }

            queryBuilder.createTable(table);
            
            stage.setMessage(app.processSQL(queryBuilder.getQuery()).getMessage());
            stage.loadDatabaseHomeScreen();
            stage.displayMessage();
        } catch(Exception ex){
            stage.setMessage(L.get("not-valid-input") + " " + ex.getMessage());
            stage.displayMessage();
        }
    }

    @FXML
    public void cancel(ActionEvent event){
        stage.loadDatabaseHomeScreen();
    }

    @FXML
    private void generateForm(){
        AnchorPane container = new AnchorPane();
        Double offset = 200.0;
        try {
            for ( int i = 0 ; i < this.columnsCount ; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                InputsGroupController subController = new InputsGroupController(stage, app);
                fxmlLoader.setController(subController);
                fxmlLoader.setLocation(stage.getClass().getResource("/application/graphical/views/elements/TableColumnFormGroup.fxml"));

                AnchorPane group = (AnchorPane) fxmlLoader.load();
                container.setTopAnchor(group, (i * offset));

                container.getChildren().add(group);
                this.inputsGroup.add(group);
            }
            this.scr_form.setContent(container);
        } catch(Exception ex){
            stage.loadDatabaseHomeScreen();
            stage.setMessage(L.get("unknown-error") + " " + ex.getMessage());
            stage.displayMessage();
        }
    }

    /**
     * This class is used to handle the different groups for the columns we are adding to the table
     */
    public class InputsGroupController extends AppController{

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

        public InputsGroupController(GraphicalApplication stage, ApplicationModel app){
            super(stage, app);
        }

        @FXML
        public void initialize(){
            this.lbl_name.setText(L.get("column-name"));
            this.lbl_type.setText(L.get("column-type"));
            this.chk_primary.setText(L.get("column-primary"));
            this.chk_notNull.setText(L.get("column-notnull"));
            this.chk_unique.setText(L.get("column-unique"));
            this.chk_autoInc.setText(L.get("column-autoincrement"));
        }

        @FXML
        public void primary(ActionEvent event){
            if ( chk_primary.isSelected() ){
                chk_notNull.setSelected(false);
                chk_notNull.setDisable(true);
                chk_unique.setSelected(false);
                chk_unique.setDisable(true);
                chk_autoInc.setSelected(false);
                chk_autoInc.setDisable(false);
            } else {
                chk_notNull.setSelected(false);
                chk_notNull.setDisable(false);
                chk_unique.setSelected(false);
                chk_unique.setDisable(false);
                chk_autoInc.setSelected(false);
                chk_autoInc.setDisable(true);
            }
        }

        @FXML
        public void notPrimary(ActionEvent event){
        }
    }
}
