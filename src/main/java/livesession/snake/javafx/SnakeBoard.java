package livesession.snake.javafx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import livesession.snake.BoardState;
import livesession.snake.GameState;

public class SnakeBoard extends GridPane {

  private ObjectProperty<Color> colorObjectProperty;

  int boardSize;
  private SnakeServiceViewModel model;
  GridPane gridPane;

  public SnakeBoard(SnakeServiceViewModel model) {
    this.model = model;
    gridPane = new GridPane();
    boardSize = model.getService().getConfiguration().getSize();

    colorObjectProperty = new SimpleObjectProperty<>();

    updateBoardColors();



    this.addEventFilter(KeyEvent.KEY_PRESSED,
        event -> System.out.println("Pressed: " + event.getCode()));



  }





  public void updateBoardColors() {

    getChildren().clear();
    // add snakecells to the grid
    for (int i = 0; i < boardSize; i++) {    // i = row
      for (int j = 0; j < boardSize; j++) {  // j = column

        SnakeCell snakeCell = new SnakeCell(boardStateToColor(model.getBoard().getStateFromPosition(i,j)));
        gridPane.add(snakeCell, j, i);
      }
    }
    getChildren().addAll(gridPane);
  }

  public ObjectProperty<Color> boardStateToColor(BoardState boardState) {
    ObjectProperty<Color> color = new SimpleObjectProperty<>();

    switch (boardState) {
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
        throw new IllegalStateException("Unexpected value: " + boardState);
    }

    return color;
  }
}
