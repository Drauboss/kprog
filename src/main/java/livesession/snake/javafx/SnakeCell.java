package livesession.snake.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeCell extends VBox {



  public SnakeCell(ObjectProperty<Color> colorProperty) {

    VBox vBox = new VBox();
    Rectangle rec = new Rectangle();
    rec.setWidth(25);
    rec.setHeight(25);
    rec.setStroke(Color.BLACK);

    //TODO: change color to boardstate
    rec.setFill(colorProperty.getValue());

    vBox.getChildren().add(rec);
    getChildren().add(vBox);




  }
}
