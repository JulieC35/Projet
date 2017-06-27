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
import javafx.stage.*;

import lang.*;
import application.graphical.*;
import application.graphical.views.elements.MenuListCell;
import library.*;
import library.entities.*;

public class SQLQueryController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private Button btn_menu_databaseName;

    @FXML
    private TextArea txa_query;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public SQLQueryController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.lbl_title.setText(app.getConnectionProfile().getDatabaseName() + " : " + L.get("sql-query"));
        this.lbl_subtitle.setText(app.getConnectionProfile().getUsername() + "@" + app.getConnectionProfile().getHost());
        this.btn_menu_databaseName.setText(app.getConnectionProfile().getDatabaseName().toUpperCase());

        this.integrateMenu(this.lst_menu);
    }

    @FXML
    void execute(ActionEvent event) {
        String[] statements = this.txa_query.getText().split(";");
        QueryResult[] results = new QueryResult[statements.length];
        for (int i = 0 ; i < statements.length ; i++){
            results[i] = app.processSQL(statements[i]);
        }

        app.postValue("sqlResults", results);
        stage.loadSQLQueryResultScreen();
    }

    @FXML
    void load(ActionEvent event) {
        String query = stage.askSQLFile();
        this.txa_query.setText(query);
    }
}
