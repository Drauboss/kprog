package prog.ex10.solution.javafx4pizzadelivery.gui;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * Simple and straight-forward implementation of a ScreenController for the PizzaDeliveryService.
 */
public class PizzaDeliveryScreenController implements ScreenController, Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaDeliveryScreenController.class);


  Pane pane;

  //FXMLLoader ShowOrderScreenLoader = new FXMLLoader(getClass().getResource("/ShowOrderScreenFXML.fxml"));
  //FXMLLoader CreateOrderScreenFXML = new FXMLLoader(getClass().getResource("/CreateOrderScreenFXML.fxml"));




  //attribute store for service
  SingletonAttributeStore attributeStore = SingletonAttributeStore.getInstance();

  //@FXML
  //private Button createOrder;

  CreateOrderScreen createOrderScreen;
  ShowOrderScreen showOrderScreen;
  EditPizzaScreen editPizzaScreen;


  public PizzaDeliveryScreenController(final Pane pane) {

    this.pane = pane;




    //createOrder.setOnAction(event -> System.out.println("das"));

  }



  @Override
  public void switchTo(final String fromScreen, final String toScreen)
      throws UnknownTransitionException {

    switch (toScreen) {
      case CreateOrderScreen.SCREEN_NAME:
        createOrderScreen = new CreateOrderScreen(this);
        pane.getChildren().clear();
        pane.getChildren().add(createOrderScreen);
        break;
      case ShowOrderScreen.SCREEN_NAME:
        showOrderScreen = new ShowOrderScreen(this);
        pane.getChildren().clear();
        pane.getChildren().add(showOrderScreen);
        break;
      case EditPizzaScreen.SCREEN_NAME:
        editPizzaScreen = new EditPizzaScreen(this);
        pane.getChildren().clear();
        pane.getChildren().add(editPizzaScreen);
        break;

      default:
        throw new UnknownTransitionException("invalid screen", fromScreen, toScreen);
    }

  }



  @Override
  public void initialize(URL location, ResourceBundle resources) {


  }
}
