import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.stage.*;
import java.io.*;

public class TestGUI extends Application{
	public static void main(String[] args) {
		try{
			launch(args);
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage)throws Exception{

		stage.setWidth(800);
        stage.setHeight(600);
		stage.setTitle("Application Base de donnees");

		Group root = new Group();
		

		Rectangle connection = new Rectangle(200,200);
		connection.setFill(Color.SKYBLUE);
		//connection.setStrokeWidth(5);
        connection.setArcHeight(30);
        connection.setArcWidth(30);

		BorderPane border = new BorderPane();
		border.setCenter(connection);

		Group sign = new Group();
        sign.setTranslateX(150);
        sign.setTranslateY(200);

        Text text = new Text(10, 30, "Hello world!");
        text.setFill(Color.SKYBLUE);
		Rectangle panel = new Rectangle( 0, -50, 500, 110);
        panel.setFill(Color.DARKBLUE);
		sign.getChildren().add(panel);
        sign.getChildren().add(text);
		root.getChildren().add(sign);

		root.getChildren().add(border);
		Scene scene = new Scene(root, Color.PALEVIOLETRED);
		stage.setScene(scene);
		stage.show();

		/*this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //instructions
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                //instructions
            }
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                System.out.println("Vous avez clique !!");
            }
        });*/

        connection.setOnMouseClicked(new EventHandler<MouseEvent>(){
        	public void handle(MouseEvent me){
        		System.out.println("Vous avez clique sur le rectangle !");
        	}
        });
		
	}
}