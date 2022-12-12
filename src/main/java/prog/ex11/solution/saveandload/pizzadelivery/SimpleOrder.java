package prog.ex11.solution.saveandload.pizzadelivery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;


/**
 * Simple and straight-forward implementation of the Order interface.
 */
public class SimpleOrder implements Order, Serializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleOrder.class);


  private int id;
  private static int idCounter = 0;

  int setValueFlag = 0;


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

  /**
   * set the value of the order.
   *
   * @param value value
   */
  public void setValue(int value) {
    this.value = value;
    setValueFlag = 1;

  }

  /**
   * calculate the value.
   *
   * @return value
   */
  public int calculateValue() {
    int value = 0;
    for (Pizza p : pizzaList) {
      value = value + p.getPrice();
    }
    return value;
  }

  @Override
  public int getValue() {
    if (setValueFlag == 0) {
      return calculateValue();
    }

    return value;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", "[", "]")
        .add("OrderId=" + getOrderId())
        .add("numOfPizzas=" + pizzaList.size())
        .add("value=" + getValue())
        .toString();
  }
}
