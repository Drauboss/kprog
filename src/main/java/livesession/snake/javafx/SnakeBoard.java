package livesession.snake.javafx;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.GameState;
import livesession.snake.SnakeListener;

public class SnakeBoard extends GridPane implements ChangeListener<Board> {

  private ObjectProperty<Color> colorObjectProperty;
  private ObjectProperty<BoardState> boardStateProperty;

  int boardSize;
  private SnakeServiceViewModel model;
  GridPane gridPane;

  public SnakeBoard(SnakeServiceViewModel model) {
    this.model = model;
    gridPane = new GridPane();
    boardSize = model.getService().getConfiguration().getSize();


    model.boardProperty().addListener(this);

    colorObjectProperty = new SimpleObjectProperty<>();
    boardStateProperty = new SimpleObjectProperty<>();

    updateBoardColors(model.boardProperty().getValue());

    this.autosize();


    this.addEventFilter(KeyEvent.KEY_PRESSED,
        event -> System.out.println("Pressed: " + event.getCode()));


  }



  public void updateBoardColors(Board board) {

    getChildren().clear();
    // add snakecells to the grid
    for (int i = 0; i < boardSize; i++) {    // i = row
      for (int j = 0; j < boardSize; j++) {  // j = column
        boardStateProperty.setValue(board.getStateFromPosition(i,j));
        SnakeCell snakeCell = new SnakeCell(boardStateProperty);
        gridPane.add(snakeCell, j, i);
      }
    }
    getChildren().addAll(gridPane);
  }


  /**
   * Called when the value of an {@link ObservableValue} changes.
   * <p>
   * In general, it is considered bad practice to modify the observed value in this method.
   *
   * @param observable The {@code ObservableValue} which value changed
   * @param oldValue   The old value
   * @param newValue   The new value
   */
  @Override
  public void changed(ObservableValue<? extends Board> observable, Board oldValue, Board newValue) {

    System.out.println("changed");
    if (Platform.isFxApplicationThread()) {
      updateBoardColors(newValue);
    } else {
      Platform.runLater(() -> updateBoardColors(newValue));
    }
  }
}
