package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * Screen to create an order in the PizzaDeliveryService.
 */
public class CreateOrderScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreen.class);

  public static final String SCREEN_NAME = "CreateOrderScreen";

  //attribute store for service
  SingletonAttributeStore attributeStore = SingletonAttributeStore.getInstance();
  SimplePizzaDeliveryService pizzaDeliveryService;
  PizzaDeliveryScreenController screenController;


  public CreateOrderScreen(PizzaDeliveryScreenController screenController) {

    this.screenController = screenController;

    pizzaDeliveryService = (SimplePizzaDeliveryService) attributeStore.getAttribute(
        "PizzaDeliveryService");

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateOrderScreenFXML.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }


  @FXML
  public void createOrderAction() {

    Integer orderId = pizzaDeliveryService.createOrder();
    attributeStore.setAttribute("orderId", orderId);
    try {
      screenController.switchTo(SCREEN_NAME, ShowOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      e.printStackTrace();
    }

    System.out.println("order created");
  }

}
