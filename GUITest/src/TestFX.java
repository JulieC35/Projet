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

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import java.util.Map;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;

public class TestFX extends Application{

	Controller2 control;
	TestModel model;

	public static void main(String[] args){
		try{
			launch(args);
		} catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage){
		try{
			stage.setWidth(1280);
        	stage.setHeight(900);
			stage.setTitle("JAVAMYADMIN");
			stage.getIcons().add(new Image("file:///D:/Projet-bash/Projet/Icon-Application-test.png"));
			Group root = new Group();

			AnchorPane anchor = FXMLLoader.load(new URL("file:///D:/Projet-bash/Projet/Test2.fxml"));

			//model = new TestModel();
			GridPane grid = new GridPane();
			grid.add(new Label("test"), 0, 0);
			grid.add(new Label("Test2"), 0, 1);
			control = new Controller2();
			//control.addGrid(grid);

			/*TitledPane final_panel = FXMLLoader.load(new URL("file:///D:/Projet-bash/Projet/Test2.fxml"));
			AnchorPane total_panel = (AnchorPane)final_panel.getContent();*/
			control.changeGrid();

			//System.out.println(root.getChildren().getClass());

			root.getChildren().add(anchor);
			Scene scene = new Scene(root, Color.TRANSPARENT);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}


}