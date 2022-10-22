package prog.ex06.solution.pizzadelivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import prog.ex05.exercise.masterworker.Task;
import prog.ex05.solution.masterworker.SimpleWorker;
import prog.ex06.exercise.pizzadelivery.Order;
import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaDeliveryService;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.TooManyToppingsException;
import prog.ex06.exercise.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the PizzaDeliveryService interface.
 */
public class SimplePizzaDeliveryService implements PizzaDeliveryService {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePizzaDeliveryService.class);

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

  //List<SimpleOrder> orders = new ArrayList<>();
  HashMap<Integer, SimpleOrder> orders = new HashMap<>();

  @Override
  public int createOrder() {
    SimpleOrder order = new SimpleOrder();
    orders.put(order.getOrderId(), order);
    return order.getOrderId();
  }

  @Override
  public int addPizza(final int orderId, final PizzaSize size) throws IllegalArgumentException {

    SimplePizza pizza = new SimplePizza(size);
    pizza.pizzaSizeCostMap = pizzaSizeCostMap;
    pizza.pizzaToppingsCostMap = pizzaToppingsCostMap;

    //for (SimpleOrder order : orders.values()) {
    //  if (order.getOrderId() == orderId) {
    //    order.getPizzaList().add(pizza);
    //  }
    //}

    //iterate trough orders map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {
      //if orders Map key equals orderId, add pizza to the pizza List from that order
      if (entry.getKey() == orderId) {
        entry.getValue().getPizzaList().add(pizza);
      } else {
        //if order Id is not found throw new exception
        throw new IllegalArgumentException("orderId: " + orderId + " is not valid");
      }
    }

    return pizza.getPizzaId();
  }

  @Override
  public void removePizza(final int orderId, final int pizzaId) throws IllegalArgumentException {


    //iterate trough order map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {
      //if orders Map key equals orderId, get the pizza List from that order
      if (entry.getKey() == orderId) {
        //get pizza list from current order
        List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();


        //iterate trough pizza list, if pizza with pizzaId is found, remove it
        for (int i = 0; i < pizzaTmpList.size(); i++) {
          Pizza p = pizzaTmpList.get(i);
          if (p.getPizzaId() == pizzaId) {
            pizzaTmpList.remove(p);
          } else {
            //if PizzaId not found throw new exception
            throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not valid");
          }
        }

      } else {
        //if order Id is not found throw new exception
        throw new IllegalArgumentException("orderId: " + orderId + " is not valid");
      }
    }

  }

  @Override
  public void addTopping(final int pizzaId, final Topping topping)
          throws IllegalArgumentException, TooManyToppingsException {

    //iterate trough order map
    for (Entry<Integer, SimpleOrder> entry : orders.entrySet()) {

        //get pizza list from current order
        List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();

        //iterate trough pizza list, if pizza with pizzaId is found, remove it
      for (int i = 0; i < pizzaTmpList.size(); i++) {
        Pizza p = pizzaTmpList.get(i);
        if (p.getPizzaId() == pizzaId) {
          if (p.getToppings().size() >= MAX_TOPPINGS_PER_PIZZA) {
            throw new TooManyToppingsException("too many toppings on Pizza: " + pizzaId);
          } else {
            p.getToppings().add(topping);

          }
        } else {
          //if PizzaId not found throw new exception
          throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not valid");
        }
      }
    }
  }

  @Override
  public void removeTopping(final int pizzaId, final Topping topping)
          throws IllegalArgumentException {

    //iterate trough order map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {

      //get pizza list from current order
      List<Pizza> pizzaTmpList = entry.getValue().getPizzaList();

      //iterate trough pizza list, if pizza with pizzaId is found, remove it
      for (int i = 0; i < pizzaTmpList.size(); i++) {
        Pizza p = pizzaTmpList.get(i);
        if (p.getPizzaId() == pizzaId) {
          p.getToppings().remove(topping);

        } else {
          //if PizzaId not found throw new exception
          throw new IllegalArgumentException("pizzaId: " + pizzaId + " is not valid");
        }
      }
    }

  }

  @Override
  public Order getOrder(final int orderId) throws IllegalArgumentException{

    //iterate trough orders map
    for (Map.Entry<Integer, SimpleOrder> entry : orders.entrySet()) {
      //if orders Map key equals orderId, return order
      if (entry.getKey() == orderId) {
        return entry.getValue();
      }
    }

    //if order Id is not found throw new exception
    throw new IllegalArgumentException("orderId: " + orderId + " is not valid");

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
