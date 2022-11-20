package prog.ex10.solution.javafx4pizzadelivery.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.gui.UnknownTransitionException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Pizza;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaSize;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.TooManyToppingsException;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.Topping;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizza;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;

/**
 * Screen to show the list of pizzas of an order of the PizzaDeliveryService. It is also possible to
 * add, change and remove pizzas.
 */
public class ShowOrderScreen extends VBox {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ShowOrderScreen.class);

  public static final String SCREEN_NAME = "ShowOrderScreen";

  //attribute store for service
  SingletonAttributeStore attributeStore = SingletonAttributeStore.getInstance();
  SimplePizzaDeliveryService pizzaDeliveryService;
  PizzaDeliveryScreenController screenController;
  static SimpleIntegerProperty simplePizzaPrice;

  //für listview mit pizzaEntry als elemente
  ObservableList<PizzaEntry> myPizzaEntrysObservableList;
  List<PizzaEntry> pizzaEntrysList = new ArrayList<>();


  //für listview mit simplePizza als elemente
  ObservableList<Pizza> mySimplePizzaObservableList;
  List<Pizza> simplePizzaList = new ArrayList<>();

  @FXML
  public Label orderIdLabel;
  @FXML
  public Label costLabel;
  @FXML
  public ChoiceBox choiceBoxId;
  @FXML
  public ListView listViewId;





  public ShowOrderScreen(PizzaDeliveryScreenController screenController) {

    this.screenController = screenController;

    pizzaDeliveryService = (SimplePizzaDeliveryService) attributeStore.getAttribute(
        "PizzaDeliveryService");



    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowOrderScreenFXML.fxml"));
    loader.setRoot(this);
    loader.setController(this);

    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }

    simplePizzaPrice = new SimpleIntegerProperty(0);
    costLabel.textProperty().bind(simplePizzaPrice.asString());


    //set orderId Label
    orderIdLabel.setText(attributeStore.getAttribute("orderId").toString());
    //set costlabel to initial 0 cost
    //costLabel.setText("0");

    //available Pizzasizes for choice-box
    List<PizzaSize> availablePizzaSizes = new ArrayList<>(EnumSet.allOf(
        PizzaSize.class));
    ObservableList<PizzaSize> myObservableAvailablePizzaSizeList = FXCollections.observableList(
        availablePizzaSizes);
    choiceBoxId.setItems(myObservableAvailablePizzaSizeList);
    choiceBoxId.getSelectionModel().selectFirst();

    //Listview für pizzaList mit cell mit PizzaEntry
    //myPizzaEntrysObservableList = FXCollections.observableList(
    //    pizzaEntrysList);
    //listViewId.setItems(myPizzaEntrysObservableList);
    //listViewId.setCellFactory(
    //    list -> new PizzaListCell(myPizzaEntrysObservableList, screenController));

    //Listview für pizzaList mit cell mit SimplePizza
    mySimplePizzaObservableList = FXCollections.observableList(
        simplePizzaList);
    listViewId.setItems(mySimplePizzaObservableList);
    listViewId.setCellFactory(
        list -> new SimplePizzaListCell(mySimplePizzaObservableList, screenController, attributeStore));


  }

  static class PizzaListCell extends ListCell<PizzaEntry> {

    private final ObservableList<PizzaEntry> pizzaObservableList;
    private final ScreenController screenController;

    public PizzaListCell(final ObservableList<PizzaEntry> pizzaObservableList, final
        ScreenController screenController) {
      this.pizzaObservableList = pizzaObservableList;
      this.screenController = screenController;
    }

    @Override
    protected void updateItem(final PizzaEntry item, final boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        VBox verticalBox = new VBox();
        Label entry = new Label(item.toString() + ",");
        verticalBox.getChildren().addAll(entry);
        Button changeButton = new Button("change");
        Button removeButton = new Button("remove");
        //removeButton.setId("remove-" + item.toString());
        changeButton.setOnAction((event -> {
          try {
            screenController.switchTo(SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
          } catch (UnknownTransitionException e) {
            throw new RuntimeException(e);
          }
        }));
        removeButton.setOnAction((event -> {
          pizzaObservableList.remove(item);
        }));
        Pane spacer = new Pane();
        spacer.setMinSize(10, 1);
        HBox horizontalBox = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        horizontalBox.getChildren().addAll(verticalBox, spacer, changeButton, removeButton);
        setGraphic(horizontalBox);
      }
    }
  }
  static class SimplePizzaListCell extends ListCell<Pizza> {

    private final ObservableList<Pizza> simplePizzaObservableList;
    private final ScreenController screenController;
    private final SingletonAttributeStore attributeStore;

    public SimplePizzaListCell(final ObservableList<Pizza> simplePizzaObservableList, final
    ScreenController screenController, final SingletonAttributeStore attributeStore) {
      this.simplePizzaObservableList = simplePizzaObservableList;
      this.screenController = screenController;
      this.attributeStore = attributeStore;
    }

    @Override
    protected void updateItem(final Pizza item, final boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        textProperty().setValue(null);
        setGraphic(null);
      } else {
        VBox verticalBox = new VBox();
        Label entry = new Label(item.getSize().toString() + ", " + item.getToppings().size() + " toppings");
        verticalBox.getChildren().addAll(entry);
        Button changeButton = new Button("change");
        Button removeButton = new Button("remove");
        //removeButton.setId("remove-" + item.toString());
        changeButton.setOnAction((event -> {

          //TODO: give EditPizzaScreen the pizza id
          attributeStore.setAttribute("pizzaId", item.getPizzaId());
          try {
            screenController.switchTo(SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
          } catch (UnknownTransitionException e) {
            throw new RuntimeException(e);
          }
        }));
        removeButton.setOnAction((event -> {
          simplePizzaObservableList.remove(item);
          //TODO: update price
        }));
        Pane spacer = new Pane();
        spacer.setMinSize(10, 1);
        HBox horizontalBox = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        horizontalBox.getChildren().addAll(verticalBox, spacer, changeButton, removeButton);
        setGraphic(horizontalBox);
      }
    }
  }
  @FXML
  public void addPizzaAction() {

    int orderId = (int) attributeStore.getAttribute("orderId");


    //set priceLabel




    //add a pizza entry mit PizzaEntry als Elemente
    //PizzaEntry entry = new PizzaEntry(pizzaDeliveryService.getPizza(pizzaId).getSize().toString(),
    //    pizzaDeliveryService.getPizza(pizzaId).getToppings().size());
    //pizzaEntrysList.add(entry);

    //myPizzaEntrysObservableList = FXCollections.observableList(
    //    pizzaEntrysList);
    //listViewId.setItems(myPizzaEntrysObservableList);

    //add a pizza entry mit SimplePizza als Elemente
    int pizzaId = pizzaDeliveryService.addPizza(orderId, (PizzaSize) choiceBoxId.getSelectionModel().getSelectedItem());

    attributeStore.setAttribute("pizzaId", pizzaId);

    simplePizzaList = pizzaDeliveryService.getOrder(orderId).getPizzaList();

    mySimplePizzaObservableList = FXCollections.observableList(
        simplePizzaList);
    listViewId.setItems(mySimplePizzaObservableList);


    simplePizzaPrice.set(pizzaDeliveryService.getOrder(orderId).getValue());
    //simplePizzaPrice = new SimpleIntegerProperty(pizzaDeliveryService.getOrder(orderId).getValue());
    //costLabel.textProperty().bind(simplePizzaPrice.asString());
    //TODO: put price in attributeStore

    //try {
    //  screenController.switchTo(SCREEN_NAME, EditPizzaScreen.SCREEN_NAME);
    //} catch (UnknownTransitionException e) {
    //  throw new RuntimeException(e);
    //}


    //System.out.println(pizzaEntrysList);

  }

  @FXML
  public void sendOrderAction() {
    try {
      screenController.switchTo(SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      throw new RuntimeException(e);
    }
  }
  @FXML
  public void cancelOrderAction() {
    try {
      screenController.switchTo(SCREEN_NAME, CreateOrderScreen.SCREEN_NAME);
    } catch (UnknownTransitionException e) {
      throw new RuntimeException(e);
    }
  }
}
