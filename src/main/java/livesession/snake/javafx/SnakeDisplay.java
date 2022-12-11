package livesession.snake.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import livesession.snake.GameConfiguration;
import livesession.snake.IllegalConfigurationException;
import livesession.snake.SnakeService;

public class SnakeDisplay extends VBox implements Initializable {

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




    gameDispayId.setVisible(false);

    List<BoardSizes> boardSizes = new ArrayList<>(EnumSet.allOf(BoardSizes.class));
    ObservableList<BoardSizes> boardSizesObservable = FXCollections.observableList(
        boardSizes);
    boardSizeChoiceBox.setItems(boardSizesObservable);
    boardSizeChoiceBox.getSelectionModel().select(1);
    //System.out.println(boardSizeChoiceBox.getSelectionModel().getSelectedItem());

    boardSizeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue,
              Number newValue) {

            //System.out.println(boardSizes.get(newValue.intValue()));

            switch (newValue.intValue()) {
              case 0:
                size = 10;
                System.out.println("0 small" + size);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 1:
                size = 20;
                System.out.println("1 middle" + size);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 2:
                size = 24;
                System.out.println("2 large" + size);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 3:
                size = 28;
                System.out.println("3 extra_large" + size);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 4:
                size = 30;
                System.out.println("4 HUUUGE" + size);
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


    List<Velocities> velocities = new ArrayList<>(EnumSet.allOf(Velocities.class));
    ObservableList<Velocities> velocitiesObservable = FXCollections.observableList(
        velocities);
    velocityChoiceBox.setItems(velocitiesObservable);
    velocityChoiceBox.getSelectionModel().select(1);
    System.out.println(velocityChoiceBox.getSelectionModel().getSelectedItem());

    velocityChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>() {
          @Override
          public void changed(ObservableValue<? extends Number> observable, Number oldValue,
              Number newValue) {

            System.out.println(velocities.get(newValue.intValue()));

            switch (newValue.intValue()) {
              case 0:
                velocityInMilliSeconds = 700;
                System.out.println("0 slow" + velocityInMilliSeconds);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 1:
                velocityInMilliSeconds = 500;
                System.out.println("1 middle" + velocityInMilliSeconds);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 2:
                velocityInMilliSeconds = 400;
                System.out.println("2 fast" + velocityInMilliSeconds);
                gameConfiguration = new GameConfiguration(size, velocityInMilliSeconds, numberOfFood);
                try {
                  model.getService().configure(gameConfiguration);
                } catch (IllegalConfigurationException e) {
                  throw new RuntimeException(e);
                }
                break;
              case 3:
                velocityInMilliSeconds = 300;
                System.out.println("3 ZOOOOOOOM" + velocityInMilliSeconds);
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
    //model.getService().reset();
    model.getService().start();
    //TODO: set visibilty of elements with "uielemenr".setVisible.(true)
  }

  @FXML
  public void abortGameAction() {
    model.getService().abort();
    gameDispayId.setVisible(false);
    startDisplayId.setVisible(true);

    System.out.println("ABORT");
  }
  @FXML
  public void resumeGameAction() {
    model.getService().resume();
    System.out.println("resume");
  }
  @FXML
  public void pauseGameAction() {
    model.getService().pause();
    System.out.println("pause");
  }

  /**
   * Called to initialize a controller after its root element has been completely processed.
   *
   * @param location  The location used to resolve relative paths for the root object, or
   *                  {@code null} if the location is not known.
   * @param resources The resources used to localize the root object, or {@code null} if the root
   *                  object was not localized.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //if (Platform.isFxApplicationThread()) {
    //  scoreLabel.textProperty().bind(model.getScoreIntegerProperty().asString());
    //} else {
    //  Platform.runLater(() -> scoreLabel.textProperty().bind(model.getScoreIntegerProperty().asString()));
    //}
    //scoreLabel.textProperty().bind(model.getScoreIntegerProperty().asString());
    Platform.runLater(() -> scoreLabel.textProperty().bind(model.getScoreIntegerProperty().asString()));
  }
}
