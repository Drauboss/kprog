package livesession.snake.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import livesession.snake.BoardState;
import livesession.snake.GameState;

public class SnakeBoard extends GridPane {

  private ObjectProperty<Color> colorObjectProperty;

  public SnakeBoard(SnakeServiceViewModel model) {

    GridPane gridPane = new GridPane();
    int boardSize = model.getService().getConfiguration().getSize();

    colorObjectProperty = new SimpleObjectProperty<>();



    //model.getService().addListener()




    //gridPane.add(snakeCell, 0, 0);
    //gridPane.add(new Button("fdsf"), 0, 0);
    // add snakecells to the grid
    for (int i = 0; i < boardSize; i++) {    // i = row
      for (int j = 0; j < boardSize; j++) {  // j = column



        SnakeCell snakeCell = new SnakeCell(boardStateToColor(model.getBoard().getStateFromPosition(i,j)));
        gridPane.add(snakeCell, j, i);
      }
    }

    getChildren().addAll(gridPane);
  }

  public Color boardStateToColor(BoardState boardState) {
    Color color;

    switch (boardState) {
      case GRASS:
        color = Color.GREEN;
        break;
      case SNAKE:
        color = Color.BLUE;
        break;
      case WALL:
        color = Color.GREY;
        break;
      case FOOD:
        color = Color.RED;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + boardState);
    }

    return color;
  }
}
