package livesession.snake.javafx;

import java.io.IOException;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SnakeDisplay extends VBox {

  @FXML
  public Label scoreLabel;
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

    //scoreLabel.textProperty().bind(model.getScoreIntegerProperty().asString());


  }

  @FXML
  public void startGameButtonAction() {
    System.out.println("fsd");
    model.getService().start();
    //TODO: set visibilty of elements with "uielemenr".setVisible.(true)
  }
}
