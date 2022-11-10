package prog.ex09.solution.editpizzascreen.pizzadelivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Order;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Pizza;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.TooManyToppingsException;
import prog.ex09.exercise.editpizzascreen.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the PizzaDeliveryService interface.
 */
public class SimplePizzaDeliveryService implements PizzaDeliveryService {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizzaDeliveryService.class);

  /**
   * Constructor for {@link SimplePizzaDeliveryService}. Setting the prices for the size and
   * toppings pricelists
   */
  public SimplePizzaDeliveryService() {
    pizzaSizeCostMap.put(PizzaSize.EXTRA_LARGE, 1100);
    pizzaSizeCostMap.put(PizzaSize.LARGE, 900);
    pizzaSizeCostMap.put(PizzaSize.MEDIUM, 700);
    pizzaSizeCostMap.put(PizzaSize.SMALL, 500);

    pizzaToppingsCostMap.put(Topping.CHEESE, 60);
    pizzaToppingsCostMap.put(Topping.TOMATO, 30);
    pizzaToppingsCostMap.put(Topping.PINEAPPLE, 90);
    pizzaToppingsCostMap.put(Topping.SEAFOOD, 150);
    pizzaToppingsCostMap.put(Topping.HAM, 70);
    pizzaToppingsCostMap.put(Topping.VEGETABLES, 20);
    pizzaToppingsCostMap.put(Topping.SALAMI, 50);
  }

  Map<PizzaSize, Integer> pizzaSizeCostMap = new HashMap<>();
  Map<Topping, Integer> pizzaToppingsCostMap = new HashMap<>();
  HashMap<Integer, SimpleOrder> orders = new HashMap<>();

  @Override
  public int createOrder() {
    SimpleOrder order = new SimpleOrder();
    orders.put(order.getOrderId(), order);
    return order.getOrderId();
  }

  @Override
  public int addPizza(final int orderId, final PizzaSize size) throws IllegalArgumentException {

    if (orderId < 1) {
      throw new IllegalArgumentException("id has to be  bigger than 0");
    }

    SimplePizza pizza = new SimplePizza(size);
    pizza.pizzaSizeCostMap = pizzaSizeCostMap;
    pizza.pizzaToppingsCostMap = pizzaToppingsCostMap;

    //iterate trough orders map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {
      //if order is found, add pizza to the pizza List from that order
      //and return pizzaId so the exception is not thrown
      if (entry.getKey() == orderId) {
        entry.getValue().getPizzaList().add(pizza);
        return pizza.getPizzaId();
      }
    }
    //if order Id is not found throw new exception
    throw new IllegalArgumentException("orderId: " + orderId + " is not valid");
  }

  @Override
  public void removePizza(final int orderId, final int pizzaId) throws IllegalArgumentException {

    if (orderId < 1 || pizzaId < 1) {
      //if PizzaId or orderId is not valid throw new exception
      throw new IllegalArgumentException("id has to be bigger than 0");
    }

    boolean foundOrderId = false;

    //iterate trough order map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {
      //if order is found, get the pizza List from that order
      //set foundOrderId to true
      //and search trough pizzalist
      if (entry.getKey() == orderId) {
        //get pizza list from current order
        List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();

        foundOrderId = true;

        //iterate trough pizza list, if pizza with pizzaId is found, remove it
        //and return to escape out of method so the exception is not thrown
        for (int i = 0; i < pizzaTmpList.size(); i++) {
          Pizza p = pizzaTmpList.get(i);
          if (p.getPizzaId() == pizzaId) {
            pizzaTmpList.remove(p);
            return;
          }
        }
      }
    }
    //if Order was found but pizzaId not, throw exception for wrong pizzaId
    //if not, then throw exception for wrong orderId
    if (foundOrderId) {
      //if pizza Id is not found throw new exception
      throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not found");
    } else {
      //if order Id is not found throw new exception
      throw new IllegalArgumentException("orderId: " + orderId + " is not found");
    }

  }

  @Override
  public void addTopping(final int pizzaId, final Topping topping)
      throws IllegalArgumentException, TooManyToppingsException {

    if (pizzaId < 1) {
      throw new IllegalArgumentException("id has to be  bigger than 0");
    }

    //iterate trough order map
    for (Entry<Integer, SimpleOrder> entry : orders.entrySet()) {

      //get pizza list from current order
      List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();

      //iterate trough pizza list, if pizza with pizzaId is found, remove it
      //and return to escape out of method so the exception is not thrown
      for (int i = 0; i < pizzaTmpList.size(); i++) {
        Pizza p = pizzaTmpList.get(i);
        if (p.getPizzaId() == pizzaId) {
          if (p.getToppings().size() >= MAX_TOPPINGS_PER_PIZZA) {
            throw new TooManyToppingsException("too many toppings on Pizza: " + pizzaId);
          } else {
            p.getToppings().add(topping);
            return;
          }
        }
      }
    }
    //if PizzaId not found in any order throw new exception
    throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not found");
  }

  @Override
  public void removeTopping(final int pizzaId, final Topping topping)
      throws IllegalArgumentException {

    if (pizzaId < 1) {
      throw new IllegalArgumentException("id has to be  bigger than 0");
    }

    //iterate trough order map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {

      //get pizza list from current order
      List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();

      //iterate trough pizza list, if pizza with pizzaId is found, remove it
      //and return to escape out of method so the exception is not thrown
      for (int i = 0; i < pizzaTmpList.size(); i++) {
        Pizza p = pizzaTmpList.get(i);
        if (p.getPizzaId() == pizzaId) {
          p.getToppings().remove(topping);
          return;
        }
      }
    }
    //if PizzaId not found throw new exception
    throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not valid");
  }

  @Override
  public Order getOrder(final int orderId) throws IllegalArgumentException {

    if (orderId < 1) {
      throw new IllegalArgumentException("id has to be  bigger than 0");
    }

    //iterate trough orders map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {
      //if orderId is found, return order so the exception is not thrown
      if (entry.getKey() == orderId) {
        return entry.getValue();
      }
    }

    //if order Id is not found throw new exception
    throw new IllegalArgumentException("orderId: " + orderId + " is not found");

  }

  public Pizza getPizza(final int pizzaId) throws IllegalArgumentException {
    if (pizzaId < 1) {
      throw new IllegalArgumentException("id has to be  bigger than 0");
    }

    //iterate trough order map
    for (Entry<Integer, SimpleOrder> entry : orders.entrySet()) {

      //get pizza list from current order
      List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();

      //iterate trough pizza list, if pizza with pizzaId is found, remove it
      //and return to escape out of method so the exception is not thrown
      for (int i = 0; i < pizzaTmpList.size(); i++) {
        Pizza p = pizzaTmpList.get(i);
        if (p.getPizzaId() == pizzaId) {
          return p;
        }
      }
    }
    //if PizzaId not found in any order throw new exception
    throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not found");

  }

  @Override
  public Map<PizzaSize, Integer> getPizzaSizePriceList() {
    return pizzaSizeCostMap;
  }

  @Override
  public Map<Topping, Integer> getToppingsPriceList() {
    return pizzaToppingsCostMap;
  }
}