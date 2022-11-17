package prog.ex10.solution.javafx4pizzadelivery.gui;

import javafx.scene.layout.VBox;

/**
 * Screen to edit the toppings on a pizza of the PizzaDeliveryService.
 */
public class EditPizzaScreen extends VBox {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);

  public static final String SCREEN_NAME = "EditPizzaScreen";

  public EditPizzaScreen(PizzaDeliveryScreenController screenController) {
  }
}
