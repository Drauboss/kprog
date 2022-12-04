package livesession.snake.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import livesession.snake.BoardState;

public class SnakeCell extends VBox {



  public SnakeCell(ObjectProperty<BoardState> boardProperty) {

    VBox vBox = new VBox();
    Rectangle rec = new Rectangle();
    rec.setWidth(25);
    rec.setHeight(25);
    rec.setStroke(Color.BLACK);

    //TODO: change color to boardstate
    //rec.setFill(colorProperty.getValue());
    rec.setFill(boardStateToColor(boardProperty));

    vBox.getChildren().add(rec);
    getChildren().add(vBox);

  }
  public Color boardStateToColor(ObjectProperty<BoardState> boardProperty) {
    ObjectProperty<Color> color = new SimpleObjectProperty<>();

    switch (boardProperty.getValue()) {
      case GRASS:
        color.setValue(Color.GREEN);
        break;
      case SNAKE:
        color.setValue(Color.BLUE);
        break;
      case WALL:
        color.setValue(Color.GREY);
        break;
      case FOOD:
        color.setValue(Color.RED);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + boardProperty);
    }

    return color.getValue();
  }
}
