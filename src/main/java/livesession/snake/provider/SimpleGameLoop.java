package livesession.snake.provider;

import javafx.application.Platform;

/**
 * Simple implementation of the GameLoop interface for the game snake.
 */
public class SimpleGameLoop extends Thread implements GameLoop {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleGameLoop.class);
  private boolean running;
  private boolean paused;
  private ExtendedSnakeService service;
  private int sleepTime;

  /**
   * Constructor.
   *
   * @param service   ExtendedSnakeService to be notified every loop
   * @param sleepTime time between two notifications in milliseconds
   */
  public SimpleGameLoop(final ExtendedSnakeService service,
                        final int sleepTime) {
    this.service = service;
    this.sleepTime = sleepTime;
    running = true;
    this.start();
  }

  @Override
  public void run() {
    while (running) {
      //service.triggeredByGameLoop();
      logger.info("triggered");

      if (paused) {
        synchronized (this) {
          try {
            this.wait();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }

      if (Platform.isFxApplicationThread()) {
        service.triggeredByGameLoop();
      } else {
        Platform.runLater(() -> service.triggeredByGameLoop());
      }


      try {
        sleep(sleepTime);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  @Override
  public void pauseGame() {

    //running = false;
    paused = true;
    //synchronized (this) {
    //  try {
    //    this.wait();
    //  } catch (InterruptedException e) {
    //    throw new RuntimeException(e);
    //  }
    //}
  }
  @Override
  public void resumeGame() {
    paused = false;
    //running = true;
    System.out.println("resume");
    synchronized (this) {
      this.notifyAll();
    }
  }

  @Override
  public void stopGame() {
    running = false;

  }
}
