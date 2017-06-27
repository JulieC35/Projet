import javafx.fxml.FXML;
import java.io.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class TestController {

    @FXML
    private GridPane table_list;

    @FXML
    private Button settings;

    public void addGrid(GridPane grid){
    	table_list = grid;
    }

    @FXML
    void settingAction(ActionEvent event) {
    	System.out.println("Settings");
    }


}
