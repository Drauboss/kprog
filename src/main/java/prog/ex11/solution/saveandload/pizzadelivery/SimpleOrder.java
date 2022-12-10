package prog.ex11.solution.saveandload.pizzadelivery;

import java.util.ArrayList;
import java.util.List;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;


/**
 * Simple and straight-forward implementation of the Order interface.
 */
public class SimpleOrder implements Order {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleOrder.class);



  private int id;
  private static int idCounter = 0;



  private int value = 0;
  List<Pizza> pizzaList = new ArrayList<>();

  public SimpleOrder() {
    id = ++idCounter;
  }

  @Override
  public int getOrderId() {
    return id;
  }


  public void setOrderId(int id) {
    this.id = id;
  }

  @Override
  public List<Pizza> getPizzaList() {
    return pizzaList;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int calculateValue() {
    for (Pizza p : pizzaList) {
      value = value + p.getPrice();
    }
    return value;
  }
  @Override
  public int getValue() {
    return value;
  }

}
