/**
 * Controller of the language selection screen.<br>
 */

package application.graphical.controllers;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import lang.*;
import application.graphical.*;
import library.*;
import library.entities.*;

public class LanguageSelectionController extends AppController{
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
    private Button btn_validate;

    @FXML
    private AnchorPane anc_form;

    @FXML
    private ToggleGroup grp_language;

    @FXML
    private RadioButton btn_fr;

    @FXML
    private RadioButton btn_en;

    /**
     * The constructor, sends the stage and application model to the parent class
     * @param stage Contains the primary stage of the application
     * @param app Contains an instance of the application model : current user, current connection, etc.
     */
    public LanguageSelectionController(GraphicalApplication stage, ApplicationModel app){
        super(stage, app);
    }

    /**
     * Default behaviour on loading of the associated views
     */
    @FXML
    public void initialize(){
        super.initialize();

        this.btn_connections.setText(L.get("my-connections"));
        this.btn_profile.setText(L.get("my-informations"));
        this.btn_language.setText("> " + L.get("my-language"));

        this.lbl_title.setText(L.get("my-language"));
        this.lbl_subtitle.setText("");
        this.btn_validate.setText(L.get("validate"));

        switch ( app.getConfigurationRule("language") ) {
            case "FRENCH":
                this.btn_fr.setSelected(true);
                break;
            case "ENGLISH":
                this.btn_en.setSelected(true);
                break;
        }
    }

    @FXML
    void closeUserPanel(ActionEvent event) {
        stage.closePanel();
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

    }

    @FXML
    void validate(ActionEvent event) {
        if ( grp_language.getSelectedToggle() != null ) {
            RadioButton selection = (RadioButton)grp_language.getSelectedToggle();
            switch ( selection.getText() ) {
                case "Francais": 
                    // If the language was already french, we don't do anything
                    if ( !app.getConfigurationRule("language").equals("FRENCH") ) {
                        app.editConfigurationRule("language", "FRENCH");
                        app.saveConfiguration();
                        stage.setMessage(L.get("language-change-success"));
                        stage.displayMessage();
                    }
                    break;
                case "English":
                    // If the language was already english, we don't do anything
                    if ( !app.getConfigurationRule("language").equals("ENGLISH") ) {
                        app.editConfigurationRule("language", "ENGLISH");
                        app.saveConfiguration();
                        stage.setMessage(L.get("language-change-success"));
                        stage.displayMessage();
                        break;
                    }
            }
        }
    }

}
