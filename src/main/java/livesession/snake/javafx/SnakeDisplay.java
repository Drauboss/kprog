package livesession.snake.javafx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class SnakeDisplay extends VBox {

  private SnakeServiceViewModel model;

  public SnakeDisplay(SnakeServiceViewModel model) {
    this.model = model;

    //FXML loader
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/SnakeDisplay.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @FXML
  public void startGameButtonAction() {
    System.out.println("fsd");
    model.getService().start();
  }
}
