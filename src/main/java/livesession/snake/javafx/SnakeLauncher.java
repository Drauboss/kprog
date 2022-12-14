package livesession.snake.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import livesession.snake.Coordinate;
import livesession.snake.SnakeService;
import livesession.snake.provider.SimpleSnakeService;

public class SnakeLauncher extends Application {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(Coordinate.class);

  @Override
  public void start(Stage primaryStage) throws Exception {
    SnakeService service = new SimpleSnakeService();
    SnakeServiceViewModel model = new SnakeServiceViewModel(service);

    SnakeDisplay display = new SnakeDisplay(model);
    SnakeBoard board = new SnakeBoard(model);

    //System.out.println(Thread.currentThread());

    HBox outerBox = new HBox();
    outerBox.getChildren().addAll(board, display);

    Scene scene = new Scene(outerBox);



    //primaryStage.setFullScreen(true);
    primaryStage.setMaximized(true);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Snake");
    primaryStage.show();
    primaryStage.onCloseRequestProperty().setValue(event -> logger.info("Closing Time"));
  }
}
