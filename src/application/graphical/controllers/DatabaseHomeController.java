/**
 * Controller of the UserPanelConnections screen.<br>
 * This is where events to modify the connections are proceeded
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

import lang.*;
import application.graphical.*;
import application.graphical.views.elements.MenuListCell;
import library.*;
import library.entities.*;

public class DatabaseHomeController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;


    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public DatabaseHomeController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_title.setText(app.getConnectionProfile().getDatabaseName());
        this.lbl_subtitle.setText(app.getConnectionProfile().getUsername() + "@" + app.getConnectionProfile().getHost());
    
        try {
            ObservableList<String> tablesList = FXCollections.observableArrayList(DBConnection.getTablesList(app.getConnection()));
            lst_menu.setItems(tablesList);

            lst_menu.setCellFactory(new Callback<ListView<String>, ListCell>() {
                @Override
                public ListCell call(ListView<String> param) {
                    return new MenuListCell();
                }
            });
        } catch (SQLException ex){
            stage.setMessage(L.get("table-list-failure") + " : " + ex.getMessage());
            stage.displayMessage();
        }

}

    @FXML
    void openUserPanel(ActionEvent event) {
        stage.loadUserPanel();
    }
}
