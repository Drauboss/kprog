package prog.ex09.solution.editpizzascreen.gui;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * JavaFX component to edit a pizza configuration.
 */
public class EditPizzaScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);

  static SimpleIntegerProperty simplePizzaPrice;
  static Pizza p;

  public EditPizzaScreen(PizzaDeliveryService service, final int orderId, int pizzaId) {
    p = getPizza(service.getOrder(orderId).getPizzaList(), pizzaId);

    Label pizzaSizeLabel = new Label();
    Label priceLabel = new Label();
    Button addToppingButton = new Button();
    ListView<Topping> toppingOnPizzaListView = new ListView<>();
    Button finishButton = new Button();

    //set pizzaSizeLabel
    pizzaSizeLabel.setText(p.getSize().toString());

    //set priceLabel
    simplePizzaPrice = new SimpleIntegerProperty(p.getPrice());
    //to change it: simplePizzaPrice.set(value)
    priceLabel.textProperty().bind(simplePizzaPrice.asString());

    //available Toppings for choice-box
    List<Topping> availableToppings = new ArrayList<>(EnumSet.allOf(Topping.class));
    ObservableList<Topping> myObservableAvailableToppingsList = FXCollections.observableList(
        availableToppings);
    ChoiceBox<Topping> toppingChoiceBox = new ChoiceBox<>(myObservableAvailableToppingsList);

    //addToppingButton
    addToppingButton.setText("Add selected Topping");
    addToppingButton.setOnAction((event -> {
      try {
        toppingOnPizzaListView.refresh();
        service.addTopping(pizzaId, toppingChoiceBox.getSelectionModel().getSelectedItem());
        simplePizzaPrice.set(p.getPrice());
      } catch (TooManyToppingsException e) {
        //throw new RuntimeException(e);
        System.out.println(e.getMessage());
      }
    }));

    //Listview für toppings mit cell
    ObservableList<Topping> myToppingObservableToppingsList = FXCollections.observableList(
        p.getToppings());
    toppingOnPizzaListView.setItems(myToppingObservableToppingsList);
    toppingOnPizzaListView.setCellFactory(
        list -> new ToppingsListCell(myToppingObservableToppingsList));

    //finishButton
    finishButton.setText("Finish Order");

    pizzaSizeLabel.setId("pizzaSizeLabel");
    priceLabel.setId("priceLabel");
    toppingChoiceBox.setId("toppingChoiceBox");
    addToppingButton.setId("addToppingButton");
    toppingOnPizzaListView.setId("toppingOnPizzaListView");
    finishButton.setId("finishButton");

    getChildren().addAll(pizzaSizeLabel, priceLabel, toppingChoiceBox, addToppingButton,
        toppingOnPizzaListView, finishButton);

    //#######DEBUG##########
    System.out.println(p.getToppings());

  }

  static class ToppingsListCell extends ListCell<Topping> {

    private final ObservableList<Topping> toppingObservableList;

    public ToppingsListCell(final ObservableList<Topping> toppingObservableList) {
      this.toppingObservableList = toppingObservableList;
    }

    @Override
    protected void updateItem(final Topping item, final boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        VBox verticalBox = new VBox();
        Label toppingLabel = new Label(item.toString());
        verticalBox.getChildren().addAll(toppingLabel);
        Button removeButton = new Button("remove");
        removeButton.setId("remove-" + item.toString());
        removeButton.setOnAction((event -> {
          toppingObservableList.remove(item);
          simplePizzaPrice.set(p.getPrice());
        }));
        Pane spacer = new Pane();
        spacer.setMinSize(10, 1);
        HBox horizontalBox = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        horizontalBox.getChildren().addAll(verticalBox, spacer, removeButton);
        setGraphic(horizontalBox);
      }
    }
  }

  public Pizza getPizza(final List<Pizza> pizzaList, final int pizzaId)
      throws IllegalArgumentException {
    if (pizzaId < 1) {
      throw new IllegalArgumentException("id has to be  bigger than 0");
    }

    //iterate trough pizza list, if pizza with pizzaId is found, remove it
    //and return to escape out of method so the exception is not thrown
    for (int i = 0; i < pizzaList.size(); i++) {
      Pizza p = pizzaList.get(i);
      if (p.getPizzaId() == pizzaId) {
        return p;
      }
    }
    //if PizzaId not found in any order throw new exception
    throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not found");
  }
}
