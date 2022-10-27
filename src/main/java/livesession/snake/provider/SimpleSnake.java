package livesession.snake.provider;

import java.util.LinkedList;
import java.util.List;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;
import livesession.snake.Direction;
import livesession.snake.Snake;

/**
 * Simple and straight-forward implementation of the Snake interface.
 */
public class SimpleSnake implements Snake {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleSnake.class);
  private final InternalBoard board;
  private final ExtendedSnakeService service;
  // The LinkedList allows direct access to the head (first) and tail (last) of the snake.
  private final LinkedList<Coordinate> position;
  private Direction direction;

  /**
   * Creates a snake. A snake has at the beginning a length of 1.
   *
   * @param service service as single source of truth to get the internal board and to notify on
   *                events during wiggling.
   */
  public SimpleSnake(final ExtendedSnakeService service) {
    this.service = service;
    board = service.getInternalBoard();
    position = new LinkedList<>();
    position.addFirst(board.getStartPosition());
    direction = Direction.EAST;
  }

  /**
   * Advances the snake in the direction the snake is oriented to.
   *
   * @return new position of the head of the snake
   * @throws IllegalPositionException if the position is not allowed
   */
  public Coordinate advance() throws IllegalPositionException {
    // TODO: CHECKED advance the snake

    Coordinate newHead = null;
    switch (direction) {

      case NORTH:
        newHead = new Coordinate(position.get(0).getRow() + 1, position.get(0).getColumn());
        break;
      case EAST:
        newHead = new Coordinate(position.get(0).getRow(), position.get(0).getColumn() + 1);
        break;
      case SOUTH:
        newHead = new Coordinate(position.get(0).getRow() - 1, position.get(0).getColumn());
        break;
      case WEST:
        newHead = new Coordinate(position.get(0).getRow(), position.get(0).getColumn() - 1);
        break;
    }

    assertNewPositionIsPossible(newHead);
    position.add(newHead);
    position.removeLast();


    return newHead;
  }

  private BoardState assertNewPositionIsPossible(final Coordinate newHead)
      throws IllegalPositionException {
    // TODO: CHECKED Check if the position is valid
    //state at the new Head position
    BoardState headState =  board.getStateFromPosition(newHead.getRow(), newHead.getColumn());

    //if new head position is on position with a wall or a part of the snake
    //throw new IllegalPositionException
    if (headState.equals(BoardState.WALL) || headState.equals(BoardState.SNAKE)) {
      throw new IllegalPositionException(newHead, headState);
    }
    return headState;
  }

  @Override
  public List<Coordinate> getPosition() {
    return position;
  }

  @Override
  public Direction getDirection() {
    return direction;
  }

  /**
   * Changes the direction of the snake by turning left.
   */
  public void goLeft() {
    direction = direction.getLeft();
  }

  /**
   * Changes the direction of the snake by turning right.
   */
  public void goRight() {
    direction = direction.getRight();
  }
}
