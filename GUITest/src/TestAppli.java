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

public class TestAppli extends Application{

	private static final double WIDTH = 1250;
	private static final double HEIGHT = 735;
	public static GridPane grid;

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
		stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
		stage.setTitle("JAVAMYADMIN");
		try{
			stage.getIcons().add(new Image("file:///D:/Projet-bash/Projet/Icon-Application-test.png"));
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		Group root = new Group();

		//bandeau avec le logo
		Group logo_group = new Group();
        logo_group.setTranslateX(0);
        logo_group.setTranslateY(0);
        //rectangle contenant le logo
		Rectangle logo_rect = new Rectangle(250, 123);
		logo_rect.setFill(Color.SILVER);
		//boutton contenant le logo
		Button logo = new Button();
		try{
			final Image image = new Image("file:///D:/Projet-bash/Projet/Icon-Application-test.png");
			final ImageView icon = new ImageView(image);
			logo.setGraphic(icon);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		logo.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

		//Ajout de l'image et du fond
		logo_group.getChildren().add(logo_rect);
		logo_group.getChildren().add(logo);

		//Ajout du menu d'accès
		grid = new GridPane();
		Label label1 = new Label("IUT DATABASE 1");
		Label label2 = new Label(">Louis Toudoire");
		Label label3 = new Label("J'AI LA FLEMME");
		grid.add(label1, 0, 0);
		grid.add(label2, 0, 1);
		grid.add(label3, 0, 2);
		grid.setTranslateY(logo_rect.getHeight()+10);
		grid.setTranslateX(10);

		//Ajout du boutton +
		Button plus_button = new Button();
		try{
			final Image image = new Image("file:///D:/FTP/plus-sign-icon-31.png");
			final ImageView icon = new ImageView(image);
			plus_button.setGraphic(icon);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		plus_button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		plus_button.setLayoutX(logo_rect.getWidth()-80);
		plus_button.setLayoutY(this.HEIGHT-120);


		//initialisation du panel gauche
		Group panel_gauche = new Group();
		Rectangle rect_gauche = new Rectangle(250, HEIGHT);
		rect_gauche.setFill(Color.LIGHTCYAN);
		panel_gauche.getChildren().add(rect_gauche);
		panel_gauche.getChildren().add(logo_group);
		panel_gauche.getChildren().add(grid);
		panel_gauche.getChildren().add(plus_button);

		// bouton paramètre
		Button button = new Button();
		try{
			final Image image = new Image("file:///D:/Projet-bash/Projet/settings-icon-128.png", true); 
			final ImageView icon = new ImageView(image);
			button.setGraphic(icon);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		button.setLayoutX(this.WIDTH - 77);
		button.setLayoutY(0);
		button.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

		//Label et titre de la fenêtre
		Group titre = new Group();
		GridPane grid2 = new GridPane();
		Label label_titre = new Label(label2.getText());
		label_titre.setFont(new Font(30));
		label_titre.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		Label label_mail = new Label("user@iut-database1");
		label_mail.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		grid2.add(label_titre, 0, 0);
		grid2.add(label_mail, 0, 1);
		titre.getChildren().add(grid2);
		//Placement du group dans la fenêtre
		titre.setLayoutX(rect_gauche.getWidth() + 30);
		titre.setLayoutY(30);



		//ajout des composants à la fenêtre
		root.getChildren().add(panel_gauche);
		root.getChildren().add(button);
		root.getChildren().add(titre);
		Scene scene = new Scene(root, Color.GAINSBORO);
		stage.setScene(scene);
		stage.show();

	}
}