/**
 * A button list cell is a button that can be added to a ListView
 */
package application.graphical.views.elements;

import javafx.scene.control.*;
import javafx.event.*;
import javafx.util.*;

public class MenuListCell extends ListCell<String> {
    private Button button;
    private EventHandler<ActionEvent> handler;

    public MenuListCell(EventHandler<ActionEvent> handler){
        if ( handler != null ){
            this.handler = handler;
        } else {
            this.handler = new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    System.out.println("Table clicked");
                                }                            
                            };
        }
        this.button = new Button();
        this.button.getStyleClass().add("href-like-button");
    }

    @Override
    public void updateItem(String table, boolean empty) {
        super.updateItem(table, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText("");
            this.button.setText(table.toString());
            this.button.setOnAction(this.handler);
            setGraphic(this.button);
        }
    }
}