package livesession.snake.javafx;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import livesession.snake.Board;
import livesession.snake.Coordinate;
import livesession.snake.GameState;
import livesession.snake.Reason;
import livesession.snake.SnakeListener;
import livesession.snake.SnakeService;
import livesession.snake.provider.SimpleSnakeService;

public class SnakeServiceViewModel implements SnakeListener {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Coordinate.class);


  private IntegerProperty score;
  private ObjectProperty<GameState> gamestate;
  private ObjectProperty<Board> board;
  private SnakeService service;


  public SnakeServiceViewModel(final SnakeService service) {
    this.service = service;
    service.addListener(this);
    score = new SimpleIntegerProperty(0);
    gamestate = new SimpleObjectProperty<>(GameState.PREPARED);
    board = new SimpleObjectProperty<>(service.getBoard());

  }

  /**
   * Informs the listener that the board has changed.
   *
   * @param board board with the actual position of GRASS, FOOD, WALL and SNAKE elements
   */
  @Override
  public void updateBoard(Board board) {

    System.out.println("updateBoard");
    this.board.setValue(board);
    SnakeBoard snakeBoard = new SnakeBoard(this);
    snakeBoard.updateBoardColors(board);
  }

  /**
   * Informs the listener that the game state has changed.
   *
   * @param state new state of the game
   */
  @Override
  public void newGameState(GameState state) {
    System.out.println("new game state");

  }

  private void raiseExceptionToUi(final String header) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game ended");
    alert.setHeaderText(header);
    alert.setContentText("You Died!!!!!");
    alert.showAndWait();

  }

  /**
   * Informs the user that the game has ended.
   *
   * @param reason reason for termination
   */
  @Override
  public void gameEnded(Reason reason) {
    System.out.println("gameEnded");
    raiseExceptionToUi("Game Ended");
  }

  /**
   * Informs the listener that the score has changed.
   *
   * @param score new score
   */
  @Override
  public void updateScore(int score) {

    getScoreIntegerProperty().setValue(score);
    //this.score.setValue(score);

  }


  public int getScore() {
    return score.get();
  }

  public IntegerProperty getScoreIntegerProperty() {
    return score;
  }

  public GameState getGamestate() {
    return gamestate.get();
  }

  public ObjectProperty<GameState> getGamestateProperty() {
    return gamestate;
  }

  public Board getBoard() {
    return board.get();
  }

  public ObjectProperty<Board> boardProperty() {
    return board;
  }

  public SnakeService getService() {
    return service;
  }
}
