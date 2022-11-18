package prog.ex10.solution.javafx4pizzadelivery.gui;



import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;

/**
 * Simple and straight-forward implementation of a ScreenController for the PizzaDeliveryService.
 */
public class PizzaDeliveryScreenController implements ScreenController {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PizzaDeliveryScreenController.class);



  public PizzaDeliveryScreenController(final Pane pane) {
  }


  @Override
  public void switchTo(final String fromScreen, final String toScreen)
      throws UnknownTransitionException {

    switch (toScreen) {
      case "CreateOrderScreen":
        break;
      case "ShowOrderScreen":
        break;
      case "EditPizzaScreen":

      default:
        throw new IllegalStateException("Unexpected value: " + toScreen);
    }

  }
}
