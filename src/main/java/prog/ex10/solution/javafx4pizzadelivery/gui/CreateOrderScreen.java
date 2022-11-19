package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Screen to create an order in the PizzaDeliveryService.
 */
public class CreateOrderScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CreateOrderScreen.class);

  public static final String SCREEN_NAME = "CreateOrderScreen";

  @FXML
  VBox vbox;

  @FXML
  Button createOrder;

  public CreateOrderScreen(PizzaDeliveryScreenController screenController) {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateOrderScreenFXML.fxml"));
    loader.setRoot(this);
    loader.setController("prog.ex10.solution.javafx4pizzadelivery.gui.PizzaDeliveryScreenController");


    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @FXML
  public void createOrder(ActionEvent event) {
    //SimplePizzaDeliveryService pizzaDeliveryService = (SimplePizzaDeliveryService) attributeStore.getAttribute(
    //    "PizzaDeliveryService");
    //pizzaDeliveryService.createOrder();
    System.out.println("order created");

  }

}
