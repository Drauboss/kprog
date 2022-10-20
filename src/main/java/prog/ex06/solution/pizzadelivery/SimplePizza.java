package prog.ex06.solution.pizzadelivery;

import java.util.ArrayList;
import java.util.List;
import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the Pizza interface.
 */
public class SimplePizza implements Pizza {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePizza.class);

  private int price;
  private int id;
  private static int idCounter = 0;
  PizzaSize size;
  List<Topping> toppings = new ArrayList<>();

  public SimplePizza(PizzaSize size) {
    this.size = size;
    id = ++idCounter;
  }

  @Override
  public int getPizzaId() {
    return id;
  }

  @Override
  public List<Topping> getToppings() {
    return toppings;
  }

  @Override
  public PizzaSize getSize() {
    return size;
  }

  @Override
  public int getPrice() {
    return price;
  }
}
