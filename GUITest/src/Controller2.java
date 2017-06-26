import javafx.fxml.FXML;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Controller2 {

	@FXML
	private Group root = new Group();

    @FXML
    private SplitPane double_panel;

    @FXML
    private AnchorPane gauche_panel;

    @FXML
    private ImageView logo;

    @FXML
    private GridPane table_list;

    @FXML
    private AnchorPane droit_panel;

    @FXML
    private Button setting_button;

    public Controller2(){

    }

    public void changeGrid(){
    	table_list = new GridPane();
    }

}