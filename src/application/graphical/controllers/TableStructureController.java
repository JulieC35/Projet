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

public class TableStructureController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private TableView<Column> tbl_tableContent;

    @FXML
    private TableColumn<Column, String> cln_name;

    @FXML
    private TableColumn<Column, String> cln_type;

    @FXML
    private TableColumn<Column, String> cln_primary;

    @FXML
    private TableColumn<Column, String> cln_null;

    @FXML
    private TableColumn<Column, String> cln_unique;

    @FXML
    private TableColumn<Column, String> cln_autoIncrement;

    @FXML
    private Button btn_menu_databaseName;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public TableStructureController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_title.setText(app.getCurrentTable());
        this.lbl_subtitle.setText(app.getConnectionProfile().getUsername() + "@" + app.getConnectionProfile().getHost());
        this.btn_menu_databaseName.setText(app.getConnectionProfile().getDatabaseName().toUpperCase());
        
        this.integrateMenu(this.lst_menu);

        queryBuilder.selectAllFromTable(app.getCurrentTable());       
        
        cln_name.setText(L.get("name"));
        cln_type.setText(L.get("type"));
        cln_primary.setText(L.get("primary"));
        cln_null.setText(L.get("not-null"));
        cln_unique.setText(L.get("unique"));
        cln_autoIncrement.setText(L.get("auto-increment"));

         // Filling of the table view
        cln_name.setCellValueFactory(new PropertyValueFactory<Column, String>("name"));
        cln_type.setCellValueFactory(new PropertyValueFactory<Column, String>("type"));
        cln_primary.setCellValueFactory(new PropertyValueFactory<Column, String>("primary"));
        cln_null.setCellValueFactory(new PropertyValueFactory<Column, String>("notNull"));
        cln_unique.setCellValueFactory(new PropertyValueFactory<Column, String>("unique"));
        cln_autoIncrement.setCellValueFactory(new PropertyValueFactory<Column, String>("autoIncrement"));

        // We get list of columns of the table with a SQL query and add it to the table
        this.tbl_tableContent.getItems().setAll(app.processSQL(queryBuilder.getQuery()).getScheme());
        this.createContextMenu();
    }

    @FXML
    public void createContextMenu(){
        this.tbl_tableContent.setRowFactory(new Callback<TableView<Column>, TableRow<Column>>() {  
            @Override  
            public TableRow<Column> call(TableView<Column> tableView) {  
                final TableRow<Column> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu();  
                // Menu elements creation
                final MenuItem accessMenuItem = new MenuItem(L.get("access"));  
                final MenuItem removeMenuItem = new MenuItem(L.get("remove"));  
                final MenuItem editMenuItem = new MenuItem(L.get("edit"));  

                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                    }  
                });

                row.setOnMouseClicked(event -> {
                    if ( event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        remove();
                    }
                });

                // Then we add the elements to the context menu
                contextMenu.getItems().add(removeMenuItem);  

            // Hiding the menu for empty rows
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row;  
            }  
        });  
    }

    @FXML
    public void add(){
        System.out.println("adding");
    }

    @FXML
    public void remove(){
        System.out.println("removing");
    }
}
