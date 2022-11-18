package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Screen to show the list of pizzas of an order of the PizzaDeliveryService. It is also possible
 * to add, change and remove pizzas.
 */
public class ShowOrderScreen extends VBox {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ShowOrderScreen.class);

  public static final String SCREEN_NAME = "ShowOrderScreen";

  public ShowOrderScreen(PizzaDeliveryScreenController screenController) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowOrderScreenFXML.fxml"));
    loader.setRoot(this);
    loader.setController(screenController);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
