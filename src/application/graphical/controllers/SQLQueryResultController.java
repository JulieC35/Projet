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

import lang.*;
import application.graphical.*;
import application.graphical.views.elements.MenuListCell;
import library.*;
import library.entities.*;

public class SQLQueryResultController extends AppController{

    @FXML
    private Label lbl_title;

    @FXML
    private Label lbl_subtitle;

    @FXML
    private ListView lst_menu;

    @FXML
    private Button btn_menu_databaseName;

    @FXML
    private ScrollPane scr_queryResults;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public SQLQueryResultController(GraphicalApplication stage, ApplicationModel app){
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
        this.integrateResults();
    }

    /**
     * 
     */
    @FXML
    public void integrateResults(){
        QueryResult[] results = null;
        try {
            results = (QueryResult[]) app.getPostedValue("sqlResults");

            AnchorPane container = new AnchorPane();
            TableView table = null;

            for ( int i = 0 ; i < results.length ; i++ ){
                table = new TableView();
                table.setPrefHeight(200.0);
                container.setTopAnchor(table, (i * 220.0));
                
                this.integrateQueryResult(table, results[i]);
                container.getChildren().add(table);
            }
    
            this.scr_queryResults.setContent(container);
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
