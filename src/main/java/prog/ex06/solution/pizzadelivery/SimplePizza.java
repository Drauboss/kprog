package prog.ex06.solution.pizzadelivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.Topping;

/**
 * Simple and straight-forward implementation of the Pizza interface.
 */
public class SimplePizza implements Pizza {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SimplePizza.class);

  int price;
  private int id;
  private static int idCounter = 0;
  PizzaSize size;
  List<Topping> toppings = new ArrayList<>();
  Map<PizzaSize, Integer> pizzaSizeCostMap;
  Map<Topping, Integer> pizzaToppingsCostMap;

  public SimplePizza(PizzaSize size) {
    this.size = size;
    id = ++idCounter;

    switch (size) {
      case SMALL:
        price = price + pizzaSizeCostMap.get(PizzaSize.SMALL);
        break;
      case MEDIUM:
        price = price + pizzaSizeCostMap.get(PizzaSize.MEDIUM);
        break;
      case LARGE:
        price = price + pizzaSizeCostMap.get(PizzaSize.LARGE);
        break;
      case EXTRA_LARGE:
        price = price + pizzaSizeCostMap.get(PizzaSize.EXTRA_LARGE);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + size);
    }
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

    for (Topping t : toppings) {

      switch (t) {

        case TOMATO:
          price = price + pizzaToppingsCostMap.get(Topping.TOMATO);
          break;
        case CHEESE:
          price = price + pizzaToppingsCostMap.get(Topping.CHEESE);
          break;
        case SALAMI:
          price = price + pizzaToppingsCostMap.get(Topping.SALAMI);
          break;
        case HAM:
          price = price + pizzaToppingsCostMap.get(Topping.HAM);
          break;
        case PINEAPPLE:
          price = price + pizzaToppingsCostMap.get(Topping.PINEAPPLE);
          break;
        case VEGETABLES:
          price = price + pizzaToppingsCostMap.get(Topping.VEGETABLES);
          break;
        case SEAFOOD:
          price = price + pizzaToppingsCostMap.get(Topping.SEAFOOD);
          break;

      }

    }

    //TODO: go trough toppinglist and add prices of toppings to price, use switch case
    return price;
  }


}

