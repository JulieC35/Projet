/**
 * A button list cell is a button that can be added to a ListView
 */
package application.graphical.views.elements;

import javafx.scene.control.*;
import javafx.event.*;
import javafx.util.*;

public class MenuListCell extends ListCell<String> {
    @Override
    public void updateItem(String table, boolean empty) {
        super.updateItem(table, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText("");
 
            Button butt = new Button();
            butt.setText(table.toString());
            butt.getStyleClass().add("href-like-button");
            butt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("clicked");
                }                            
            });
            setGraphic(butt);
        }
    }
}