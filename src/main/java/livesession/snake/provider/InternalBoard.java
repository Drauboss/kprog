package livesession.snake.provider;

import java.util.Arrays;
import livesession.snake.BoardState;
import livesession.snake.Coordinate;

/**
 * Realizes the internal view on the snake board. It contains GRASS, FOOD and WALL elements <b>but
 * not the position of the snake itself</b>.
 */
public class InternalBoard extends BaseBoard {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(InternalBoard.class);

  /**
   * Creates an internal board with the given size.
   *
   * @param size size of the board including walls at the border of the board.
   */
  public InternalBoard(final int size) {
    super(size);

    // TODO: Init board with GRASS and WALLs
/*
       grid layout:
       x = wall
       g = grass
       size = 4
         0 1 2 3  j = column
       0 x x x x
       1 x g g x
       2 x g g x
       3 x x x x
       i
       =
       row
     */

    // place grass on the complete field
    for (int i = 0; i < size; i++) {    // i = row
      for (int j = 0; j < size; j++) {  // j = column
        board[i][j] = BoardState.GRASS;
      }
    }

    // place walls on the top row of the field
    for (int j = 0; j < size; j++) {  // j = column
      board[0][j] = BoardState.WALL;
    }

    // place walls on the bottom row of the field
    for (int j = 0; j < size; j++) {  // j = column
      board[size - 1][j] = BoardState.WALL;
    }

    // place walls on the most left column of the field
    for (int i = 0; i < size; i++) {  // i = row
      board[i][0] = BoardState.WALL;
    }

    // place walls on the most right column of the field
    for (int i = 0; i < size; i++) {  // i = row
      board[i][size - 1] = BoardState.WALL;
    }

    // TODO: end
  }

  /**
   * Calculates the start position.
   *
   * @return coordinate with the start position
   */
  public Coordinate getStartPosition() {
    return new Coordinate(size / 2, size / 2);
  }

  /**
   * Adds food at the given coordinate.
   *
   * @param coordinate coordinate where the food is placed.
   */
  protected void addFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.FOOD;
  }

  /**
   * Removes food at the given coordinate.
   *
   * @param coordinate coordinate where the food has to be removed.
   */
  protected void removeFood(Coordinate coordinate) {
    assertPositionIsOnBoard(coordinate);
    board[coordinate.getRow()][coordinate.getColumn()] = BoardState.GRASS;
  }

}
