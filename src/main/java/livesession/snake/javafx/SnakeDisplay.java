package livesession.snake.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.SnakeService;

public class SnakeDisplay extends VBox {

  @FXML
  public Label scoreLabel;

  @FXML
  public HBox startDisplayId;
  @FXML
  public HBox gameDispayId;
  @FXML
  public ChoiceBox velocityChoiceBox;
  @FXML
  public ChoiceBox boardSizeChoiceBox;
  private SnakeServiceViewModel model;
  private GameConfiguration gameConfiguration;
  private int size;
  private int velocityInMilliSeconds;
  private int numberOfFood;

  public SnakeDisplay(SnakeServiceViewModel model) {
    this.model = model;

    size = SnakeService.DEFAULT_SIZE;
    velocityInMilliSeconds = SnakeService.DEFAULT_VELOCITY;
    numberOfFood = SnakeService.DEFAULT_NUMBER_OF_FOOD;

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

    gameDispayId.setVisible(false);

    List<BoardSizes> boardSizes = new ArrayList<>(EnumSet.allOf(BoardSizes.class));
    ObservableList<BoardSizes> boardSizesObservable = FXCollections.observableList(
        boardSizes);
    boardSizeChoiceBox.setItems(boardSizesObservable);
    boardSizeChoiceBox.getSelectionModel().selectFirst();
    System.out.println(boardSizeChoiceBox.getSelectionModel().getSelectedItem());

    boardSizeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue,
              Number newValue) {

            System.out.println(boardSizes.get(newValue.intValue()));

            switch (newValue.intValue()) {
              case 0:
                System.out.println("0");
                size = 7;
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 1:
                System.out.println("1");
                size = 10;
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 2:
                System.out.println("2");
                size = 15;
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 3:
                System.out.println("3");
                size = 21;
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 4:
                System.out.println("4");
                size = 30;
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;

              default:
                //throw new IllegalStateException("Unexpected value: " + newValue.intValue());
            }
          }
        });

    //if (boardSizeChoiceBox.getSelectionModel().getSelectedItem().toString().equals(BoardSizes.extra_large)) {
    //  size = 20;
    //}
    //if (boardSizeChoiceBox.getSelectionModel().getSelectedItem().toString().equals(BoardSizes.large)) {
    //  size = 15;
    //}
    //if (boardSizeChoiceBox.getSelectionModel().getSelectedItem().toString().equals(BoardSizes.small)) {
    //  size = 7;
    //}
    //if (boardSizeChoiceBox.getSelectionModel().getSelectedItem().toString().equals(BoardSizes.middle)) {
    //  size = 10;
    //}
    //if (boardSizeChoiceBox.getSelectionModel().getSelectedItem().equals(BoardSizes.HUUUUUUUUUGE)) {
    //  size = 30;
    //}






    gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
    try {
      model.getService().configure(gameConfiguration);
    } catch (IllegalConfigurationException e) {
      throw new RuntimeException(e);
    }

  }

  public SnakeBoard returnSnakeBoard() {
    SnakeBoard snakeBoard = new SnakeBoard(model);
    return snakeBoard;
  }

  @FXML
  public void startGameButtonAction() {
    System.out.println(model.getService().getConfiguration().toString());



    gameDispayId.setVisible(true);
    startDisplayId.setVisible(false);
    model.getService().start();
    //TODO: set visibilty of elements with "uielemenr".setVisible.(true)
  }
}
