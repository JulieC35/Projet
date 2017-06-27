/**
 * Controller of the UserPanelConnections screen.<br>
 * This is where events to modify the connections are proceeded
 */

package application.graphical.controllers;

import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.util.*;
import javafx.beans.binding.*;

import lang.*;
import application.graphical.*;
import library.*;
import library.entities.*;

public class ConnectionsListController extends AppController{

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
    private Button btn_add;

    @FXML
    private TableView<DBConnection> tbl_connections;

    @FXML
    private TableColumn<DBConnection, String> cln_name;

    @FXML
    private TableColumn<DBConnection, String> cln_username;

    @FXML
    private TableColumn<DBConnection, String> cln_host;

    @FXML
    private TableColumn<DBConnection, String> cln_dbName;


    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public ConnectionsListController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.btn_connections.setText("> " + L.get("my-connections"));
        this.btn_profile.setText(L.get("my-profile"));
        this.btn_language.setText(L.get("my-language"));
        this.btn_add.setText(L.get("add"));

        this.lbl_title.setText(L.get("my-connections"));
        this.lbl_subtitle.setText("");

        this.cln_name.setText(L.get("connection-name"));
        this.cln_username.setText(L.get("username"));
        this.cln_host.setText(L.get("host"));
        this.cln_dbName.setText(L.get("database-name"));

        // Filling of the table view
        cln_name.setCellValueFactory(new PropertyValueFactory<DBConnection, String>("name"));
        cln_username.setCellValueFactory(new PropertyValueFactory<DBConnection, String>("username"));
        cln_host.setCellValueFactory(new PropertyValueFactory<DBConnection, String>("host"));
        cln_dbName.setCellValueFactory(new PropertyValueFactory<DBConnection, String>("databaseName"));

        // We get the observable list of connections of the current user from the model
        tbl_connections.getItems().setAll(app.getUser().observeDBConnections());

        this.createContextMenu();
    }

    @FXML
    void createContextMenu(){
        tbl_connections.setRowFactory(new Callback<TableView<DBConnection>, TableRow<DBConnection>>() {  
            @Override  
            public TableRow<DBConnection> call(TableView<DBConnection> tableView) {  
                final TableRow<DBConnection> row = new TableRow<>();  
                final ContextMenu contextMenu = new ContextMenu();  
                // Menu elements creation
                final MenuItem accessMenuItem = new MenuItem(L.get("access"));  
                final MenuItem removeMenuItem = new MenuItem(L.get("remove"));  
                final MenuItem editMenuItem = new MenuItem(L.get("edit"));  

                // Actions handling
                accessMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                        accessDBConnection(row.getItem());
                    }  
                });
                removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                        removeDBConnection(row.getItem());
                    }  
                });  
                editMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                    @Override  
                    public void handle(ActionEvent event) {  
                        editDBConnection(row.getItem());
                    }  
                }); 

                row.setOnMouseClicked(event -> {
                    if ( event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        accessDBConnection(row.getItem());
                    }
                });

                // Then we add the elements to the context menu
                contextMenu.getItems().add(accessMenuItem);  
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
    void onAddButtonClick(ActionEvent event) {
        stage.loadAddConnectionScreen();
    }

    @FXML
    void accessDBConnection(DBConnection dbC){
        if ( dbC != null ) {
            app.setConnectionProfile(dbC);
            app.connect();
            stage.loadDatabaseHomeScreen();
        } else {
            stage.setMessage(L.get("connection-failure"));
            stage.displayMessage();
        }
    }

    @FXML 
    void removeDBConnection(DBConnection dbC){
        if ( stage.askConfirmation(null) ) {
            if ( app.getUser().removeDBConnection(dbC) ){
                app.getAuthSystem().saveUsers();
                tbl_connections.getItems().remove(dbC);
            }
        }
    }

    @FXML 
    void editDBConnection(DBConnection dbC){
        if ( dbC != null ){
            System.out.println(dbC);
            app.postValue("db-connection", dbC);
            stage.loadConnectionEditScreen();
        }
    }

}
