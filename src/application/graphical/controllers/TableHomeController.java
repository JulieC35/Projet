/**
 * Controller of the SQLQuery screen.<br>
 */

package application.graphical.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

public class TableHomeController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private TableView tbl_tableContent;

    @FXML
    private Button btn_menu_databaseName;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public TableHomeController(GraphicalApplication stage, ApplicationModel app){
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
        this.integrateQueryResult(this.tbl_tableContent, app.processSQL(queryBuilder.getQuery()));
        this.createContextMenu();
    }

    @FXML
    public void createContextMenu(){
        this.tbl_tableContent.setRowFactory(new Callback<TableView<DBConnection>, TableRow<DBConnection>>() {  
            @Override  
            public TableRow<DBConnection> call(TableView<DBConnection> tableView) {  
                final TableRow<DBConnection> row = new TableRow<>();  
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
                editMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                    }  
                }); 

                row.setOnMouseClicked(event -> {
                    if ( event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    }
                });

                // Then we add the elements to the context menu
                contextMenu.getItems().add(removeMenuItem);  
                contextMenu.getItems().add(editMenuItem);  

            // Hiding the menu for empty rows
                row.contextMenuProperty().bind(  
                        Bindings.when(row.emptyProperty())  
                        .then((ContextMenu)null)  
                        .otherwise(contextMenu)  
                );  
                return row ;  
            }  
        });  
    }

    @FXML
    public void insert(){

    }

    @FXML
    public void structure(){

    }

    @FXML 
    public void dropTable(){
        if ( stage.askConfirmation(L.get("drop-table") + " " + app.getCurrentTable()) ) {
            queryBuilder.dropTable(app.getCurrentTable());
            app.processSQL(queryBuilder.getQuery());
            stage.loadDatabaseHomeScreen();
            stage.setMessage(L.get("table-removal-success"));
            stage.displayMessage();
        }
    }
}
