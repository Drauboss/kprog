package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * Screen to edit the toppings on a pizza of the PizzaDeliveryService.
 */
public class EditPizzaScreen extends VBox {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(EditPizzaScreen.class);

  public static final String SCREEN_NAME = "EditPizzaScreen";

  static SimpleIntegerProperty simplePizzaPrice;
  static Pizza p;
  List<Topping> toppings;
  ObservableList<Topping> myToppingObservableToppingsList;

  //create gui nodes
  Label pizzaSizeLabel = new Label();
  Button addToppingButton = new Button();
  ListView<Topping> toppingsOnPizzaListView = new ListView<>();
  Button finishButton = new Button();
  Label priceLabel = new Label();


  /**
   *
   *
   */
  public EditPizzaScreen(PizzaDeliveryScreenController screenController) {




    //get the pizza object with id=pizzaId
    p = getPizza(service.getOrder(orderId).getPizzaList(), pizzaId);
    toppings = p.getToppings();

    //set pizzaSizeLabel
    pizzaSizeLabel.setText(p.getSize().toString());

    //set priceLabel
    simplePizzaPrice = new SimpleIntegerProperty(p.getPrice());
    priceLabel.textProperty().bind(simplePizzaPrice.asString());

    //available Toppings for choice-box
    List<Topping> availableToppings = new ArrayList<>(EnumSet.allOf(Topping.class));
    ObservableList<Topping> myObservableAvailableToppingsList = FXCollections.observableList(
        availableToppings);
    ChoiceBox<Topping> toppingChoiceBox = new ChoiceBox<>(myObservableAvailableToppingsList);
    toppingChoiceBox.getSelectionModel().selectFirst();

    //Listview für toppings mit cell
    myToppingObservableToppingsList = FXCollections.observableList(
        toppings);
    toppingsOnPizzaListView.setItems(myToppingObservableToppingsList);
    toppingsOnPizzaListView.setCellFactory(
        list -> new ToppingsListCell(myToppingObservableToppingsList));

    //addToppingButton
    addToppingButton.setText("Add selected Topping");
    addToppingButton.setOnAction((event -> {
      try {
        service.addTopping(pizzaId, toppingChoiceBox.getSelectionModel().getSelectedItem());
        myToppingObservableToppingsList = FXCollections.observableList(
            toppings);
        toppingsOnPizzaListView.setItems(myToppingObservableToppingsList);
        simplePizzaPrice.set(p.getPrice());
      } catch (TooManyToppingsException e) {
        //throw new RuntimeException(e);
        System.out.println(e.getMessage());
        raiseExceptionToUi(e, e.getMessage());
      }
    }));

    //finishButton
    finishButton.setText("Finish Order");

    //set IDs
    pizzaSizeLabel.setId("pizzaSizeLabel");
    priceLabel.setId("priceLabel");
    toppingChoiceBox.setId("toppingChoiceBox");
    addToppingButton.setId("addToppingButton");
    toppingsOnPizzaListView.setId("toppingsOnPizzaListView");
    finishButton.setId("finishButton");

    //add nodes to scene graph
    getChildren().addAll(pizzaSizeLabel, priceLabel, toppingChoiceBox, addToppingButton,
        toppingsOnPizzaListView, finishButton);

    //#######DEBUG##########
    //System.out.println(p.getToppings());

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

  /**
   * method to get the pizza object from a pizzaId.
   *
   * @param pizzaList list where the pizza is
   * @param pizzaId   pizza id
   * @return pizza object of requested id
   * @throws IllegalArgumentException throw if id is not valid
   */
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

  private void raiseExceptionToUi(final Exception e, final String header) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(header);
    alert.setContentText("You cant add more than 6 Toppings on one Pizza!");
    alert.showAndWait();

  }


}
