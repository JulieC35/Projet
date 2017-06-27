/**
 * Parent class of every controller of the application
 */
package application.graphical;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.util.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.sql.SQLException;
import javafx.beans.property.*;

import library.*;
import library.entities.*;
import library.managers.*;
import lang.*;
import application.graphical.views.elements.*;

public abstract class AppController {
    protected ApplicationModel app;
    protected GraphicalApplication stage;
    protected QueryBuilder queryBuilder;

    @FXML
    protected Label lbl_message;

    @FXML
    protected AnchorPane anc_container;

    /**
     * The constructor of the controller
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public AppController(GraphicalApplication stage, ApplicationModel app){
        this.stage = stage;
        this.app = app;
        this.queryBuilder = new QueryBuilder();
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        this.lbl_message.setText("");
    }   

    @FXML 
    public void logout(){
        app.logout();
        stage.loadLoginScreen();
    }

    @FXML
    void closeUserPanel(ActionEvent event) {
        stage.closePanel();
    }

    @FXML
    void openUserPanel(ActionEvent event) {
        stage.loadUserPanel();
    }

    @FXML
    void connections(ActionEvent event) {
        stage.loadUserPanel();
    }

    @FXML
    void language(ActionEvent event) {
        stage.loadLanguageSelectionScreen();
    }

    @FXML
    void profile(ActionEvent event) {
        stage.loadUserProfileEditScreen();
    }  

    @FXML
    void sql(ActionEvent event){
        stage.loadSQLQueryScreen();
    }

    @FXML
    void databaseHome(ActionEvent event){
        stage.loadDatabaseHomeScreen();
    }

    @FXML
    public void integrateMenu(ListView lst_menu){
        try {
            ObservableList<String> tablesList = FXCollections.observableArrayList(DBConnection.getTablesList(app.getConnection()));
            lst_menu.setItems(tablesList);

            lst_menu.setCellFactory(new Callback<ListView<String>, ListCell>() {
                @Override
                public ListCell call(ListView<String> param) {
                    return new MenuListCell(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    app.setCurrentTable(((Button)event.getSource()).getText());
                                    stage.loadTableHomeScreen();
                                } catch (SQLException ex){
                                    stage.setMessage(L.get("table-access-failure"));
                                    stage.displayMessage();
                                }
                            }                            
                        }
                    );
                }
            });
        } catch (SQLException ex){
            stage.setMessage(L.get("table-list-failure") + " : " + ex.getMessage());
            stage.displayMessage();
        }
    }

    @FXML
    public void integrateQueryResult(TableView<Row> table, QueryResult result){
        if ( result != null ){
            if ( result.getRows().size() >= 1 ) {
                try {
                    // We define the table columns
                    for (int i = 0; i < result.getScheme().size(); i++) {
                        TableColumn<Row, String> column = new TableColumn<>(result.getScheme().get(i).getName());
                        column.setCellValueFactory(param -> {
                            int index = param.getTableView().getColumns().indexOf(param.getTableColumn());
                            List<String> cells = param.getValue().getValues();
                            return new SimpleStringProperty( ( cells.size() > index ) ? cells.get(index) : null);
                        });
                        table.getColumns().add(column);
                    }

                    table.getItems().addAll(result.getRows());
                } catch ( Exception ex) {
                    stage.setMessage(L.get("entries-access-failure"));
                    stage.displayMessage();  
                }
            }

        } else {
            stage.setMessage(L.get("tuple-access-error"));
            stage.displayMessage();
        }
    }
}
