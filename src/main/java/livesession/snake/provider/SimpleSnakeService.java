package livesession.snake.provider;

import static livesession.snake.Board.MINIMAL_BOARD_SIZE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import livesession.snake.Board;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.GameConfiguration;
import livesession.snake.GameState;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.Reason;
import livesession.snake.Snake;
import livesession.snake.SnakeListener;
import livesession.snake.SnakeService;

/**
 * Simple and straight-forward implementation of the ExtendedSnakeService interface.
 */
public class SimpleSnakeService implements ExtendedSnakeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnakeService.class);
  private GameConfiguration gameConfiguration;
  private InternalBoard board;
  private SimpleSnake snake;
  private GameLoop simpleGameLoop;
  private FoodGenerator foodGenerator;

  private GameState gameState;
  private int score;

  private List<SnakeListener> listeners;

  /**
   * Default constructor. The game uses then default values for configuration.
   * The default values are defined in the SnakeService interface.
   */
  public SimpleSnakeService() {
    // TODO: What to initialize?
    listeners = new ArrayList<>();
    foodGenerator = new FoodGenerator(this);
    gameConfiguration = new GameConfiguration(DEFAULT_SIZE, DEFAULT_VELOCITY,
        DEFAULT_NUMBER_OF_FOOD);
    try {
      configure(gameConfiguration);
    } catch (IllegalConfigurationException e) {
      throw new RuntimeException(e);
    }

    reset();

    // TODO: end.
  }

  @Override
  public void reset() {
    logger.debug("reset:");
    //TODO: reset for a new game
    snake = new SimpleSnake(this);
    foodGenerator.placeFood();
    score = 0;
    gameState = GameState.PREPARED;
    //TODO: end.
  }

  @Override
  public void start() {
    logger.debug("start:");
    try {
      configure(gameConfiguration);
    } catch (IllegalConfigurationException e) {
      throw new RuntimeException(e);
    }
    simpleGameLoop = new SimpleGameLoop(this, gameConfiguration.getVelocityInMilliSeconds());
    gameState = GameState.RUNNING;
    notifyListeners((l) -> l.newGameState(gameState));
  }

  @Override
  public void abort() {
    logger.debug("abort:");
    simpleGameLoop.stopGame();
    gameState = GameState.ABORTED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
    notifyListeners((SnakeListener listener) -> listener.gameEnded(new Reason("Game aborted")));
  }

  @Override
  public void pause() {
    logger.debug("pause:");
    simpleGameLoop.pauseGame();
    gameState = GameState.PAUSED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
  }

  @Override
  public void resume() {
    logger.debug("resume:");
    simpleGameLoop.resumeGame();
    gameState = GameState.RUNNING;
    notifyListeners((l) -> l.newGameState(gameState));
  }

  @Override
  public void moveLeft() {
    logger.debug("moveLeft:");
    snake.goLeft();
  }

  @Override
  public void moveRight() {
    logger.debug("moveRight:");
    snake.goRight();
  }

  @Override
  public boolean addListener(final SnakeListener listener) {
    logger.debug("addListener: " + listener);
    if (listener == null) {
      return false;
    }

    if (listeners.contains(listener)) {
      return false;
    }

    listeners.add(listener);
    return true;
  }

  @Override
  public boolean removeListener(final SnakeListener listener) {
    logger.debug("removeListener: " + listener);
    return listeners.remove(listener);
  }

  @Override
  public void configure(final GameConfiguration configuration) throws
      IllegalConfigurationException {
    //TODO: check and save the configuration info
    board = new InternalBoard(gameConfiguration.getSize());

    if (configuration == null) {
      throw new IllegalConfigurationException("Config is null");
    }

    if (configuration.getNumberOfFood() < 1) {
      throw new IllegalConfigurationException("number of food has to be 1 at minimum");
    }

    if (configuration.getSize() < MINIMAL_BOARD_SIZE) {
      throw new IllegalConfigurationException("size has to be 4 at minimum");
    }

    if (configuration.getVelocityInMilliSeconds() < 1) {
      throw new IllegalConfigurationException("velocity has to be 1 at minimum");
    }

    //foodGenerator = new FoodGenerator(this);
    //for (int i = 0; i < configuration.getNumberOfFood(); i++) {
    //foodGenerator.placeFood();
    //}

    this.gameConfiguration = configuration;
    //TODO: end.
  }

  @Override
  public GameConfiguration getConfiguration() {
    return gameConfiguration;
  }

  @Override
  public Snake getSnake() {
    return snake;
  }

  public Board getBoard() {
    return getExternalBoard();
  }

  /**
   * Notifies all listeners by executing the consumer accept method. The accept method
   * implementation is in our case a lambda expression.
   *
   * @param consumer consumer to be executed.
   */
  private void notifyListeners(Consumer<SnakeListener> consumer) {
    for (SnakeListener listener : listeners) {
      consumer.accept(listener);
    }
  }

  @Override
  public Board getExternalBoard() {
    ExternalBoard externalBoard = new ExternalBoard(board, snake);
    return externalBoard;
  }

  @Override
  public void failed(Reason reason) {
    logger.debug("failed: " + reason);
    simpleGameLoop.stopGame();
    gameState = GameState.ABORTED;
    notifyListeners((SnakeListener listener) -> listener.newGameState(gameState));
    notifyListeners((l) -> l.gameEnded(reason));
  }

  @Override
  public void triggeredByGameLoop() {
    try {
      advanceSnake();
    } catch (IllegalPositionException e) {
      failed(new Reason(e.getCoordinate(), e.getState()));
    }
  }

  @Override
  public void advanceSnake() throws IllegalPositionException {
    logger.debug("advanceSnake:");
    Coordinate newPosition = snake.advance();
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
  }

  @Override
  public void addFood(final Coordinate coordinate) {
    logger.debug("addFood: " + coordinate);
    if (board.getStateFromPosition(coordinate).equals(BoardState.FOOD)) {
      throw new IllegalArgumentException("There is already food at this position: " + coordinate);
    }

    board.addFood(coordinate);
    notifyListeners((l) -> l.updateBoard(getExternalBoard()));
  }

  @Override
  public void foodEaten(final Coordinate coordinate) {
    logger.debug("foodEaten: " + coordinate);
    //TODO: what has to be done when one food has been eaten?
    board.removeFood(coordinate);
    updateScore(BoardState.FOOD);
    Board externalBoard = getExternalBoard();
    Coordinate coordinate1 = foodGenerator.placeFood();
    //TODO: check if food is placed on grass field
    //addFood(coordinate1);
    //TODO: end.
  }

  @Override
  public void updateScore(final BoardState state) {
    logger.debug("updateScore: " + state);
    if (state == BoardState.FOOD) {
      score += 10;
    } else {
      throw new IllegalArgumentException("Unknown state in updateScore: " + state);
    }
    notifyListeners((l) -> l.updateScore(score));
  }

  @Override
  public InternalBoard getInternalBoard() {
    return board;
  }

}
