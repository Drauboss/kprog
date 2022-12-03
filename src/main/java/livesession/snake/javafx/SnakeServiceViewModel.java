package livesession.snake.javafx;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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

  /**
   * Informs the user that the game has ended.
   *
   * @param reason reason for termination
   */
  @Override
  public void gameEnded(Reason reason) {

  }

  /**
   * Informs the listener that the score has changed.
   *
   * @param score new score
   */
  @Override
  public void updateScore(int score) {


  }


  public int getScoreIntegerProperty() {
    return score.get();
  }

  public IntegerProperty scoreIntegerPropertyProperty() {
    return score;
  }

  public GameState getGamestate() {
    return gamestate.get();
  }

  public ObjectProperty<GameState> gamestateProperty() {
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
