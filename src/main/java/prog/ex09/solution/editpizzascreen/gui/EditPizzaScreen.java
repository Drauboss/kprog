package prog.ex09.solution.editpizzascreen.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * JavaFX component to edit a pizza configuration.
 */
public class EditPizzaScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);

  public EditPizzaScreen(PizzaDeliveryService service, final int orderId, int pizzaId) {

    Label pizzaSizeLabel;
    Label priceLabel;
    Button addToppingButton;
    ListView<Topping> toppingOnPizzaListView;
    //Cell
    Button finishButton;



    List<Topping> availableToppings = new ArrayList<>(EnumSet.allOf(Topping.class));
    ObservableList<Topping> myObservableToppingList = FXCollections.observableList(availableToppings);
    ChoiceBox<Topping> toppingChoiceBox = new ChoiceBox<>(myObservableToppingList);
    toppingChoiceBox.setItems(myObservableToppingList);

    //pizzaSizeLabel.setId(pizzaSizeLabel);
    //priceLabel.setId(priceLabel);
    //toppingChoiceBox.setId(toppingChoiceBox);
    //addToppingButton.setId(addToppingButton);
    //toppingOnPizzaListView.setId(toppingOnPizzaListView);
    //finishButton.setId(finishButton);



  }
}
