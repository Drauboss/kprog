package prog.ex10.solution.javafx4pizzadelivery.gui;



import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class PizzaDeliveryScreenController implements ScreenController {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaDeliveryScreenController.class);


  Pane pane;

  FXMLLoader ShowOrderScreenLoader = new FXMLLoader(getClass().getResource("/ShowOrderScreenFXML.fxml"));
  FXMLLoader CreateOrderScreenFXML = new FXMLLoader(getClass().getResource("/CreateOrderScreenFXML.fxml"));




  //attribute store for service
  SingletonAttributeStore attributeStore = SingletonAttributeStore.getInstance();

  @FXML
  Button createOrder;


  public PizzaDeliveryScreenController(final Pane pane) {

    this.pane = pane;

  }



  @Override
  public void switchTo(final String fromScreen, final String toScreen)
      throws UnknownTransitionException {

    switch (toScreen) {
      case CreateOrderScreen.SCREEN_NAME:
        CreateOrderScreen createOrderScreen = new CreateOrderScreen(this);
        pane.getChildren().add(createOrderScreen);
        break;
      case ShowOrderScreen.SCREEN_NAME:
        ShowOrderScreen showOrderScreen = new ShowOrderScreen(this);
        pane.getChildren().add(showOrderScreen);
        break;
      case EditPizzaScreen.SCREEN_NAME:
        EditPizzaScreen editPizzaScreen = new EditPizzaScreen(this);
        pane.getChildren().add(editPizzaScreen);
        break;

      default:
        throw new IllegalStateException("Unexpected value: " + toScreen);
    }

  }


  @FXML
  void createOrder(ActionEvent event) {
    //SimplePizzaDeliveryService pizzaDeliveryService = (SimplePizzaDeliveryService) attributeStore.getAttribute(
    //    "PizzaDeliveryService");
    //pizzaDeliveryService.createOrder();
    System.out.println("order created");
  }
}
