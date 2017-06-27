import java.util.*;
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


public class TestModel{
	public static ArrayList<String> mille;

	public TestModel(){
		mille = new ArrayList<String>();
		mille.add("LOL");
		mille.add("Coucou");
		mille.add("CA MARCHE");
	}

	public static GridPane arrayToGrid(ArrayList<String> list){
		GridPane grid = new GridPane();
		int i = 0;
		for (String s : list){
			Label label = new Label(s);
			grid.add(label, 0, i);
			i++;
		}
		return grid;
	}
}