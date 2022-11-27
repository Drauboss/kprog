package livesession.snake.javafx;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakeCell extends VBox {

  private Background wallBackground;
  private Background grassBackground;
  private Background foodBackground;
  private Background snakeBackground;


  public SnakeCell(Color colorProperty) {
    wallBackground = new Background(
        new BackgroundFill(Color.GREY, null, null));
    grassBackground = new Background(
        new BackgroundFill(Color.GREEN, null, null));
    foodBackground = new Background(
        new BackgroundFill(Color.RED, null, null));
    snakeBackground = new Background(
        new BackgroundFill(Color.BLUE, null, null));


    VBox vBox = new VBox();
    Rectangle rec = new Rectangle();
    rec.setWidth(25);
    rec.setHeight(25);
    rec.setStroke(Color.BLACK);

    //TODO: change color to boardstate
    rec.setFill(colorProperty);

    vBox.getChildren().add(rec);
    getChildren().add(vBox);




  }
}
