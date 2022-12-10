package prog.ex11.solution.saveandload.pizzadelivery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;


/**
 * Simple and straight-forward implementation of the Pizza interface.
 */
public class SimplePizza implements Pizza {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimplePizza.class);



  int price = 0;



  private int id;
  private static int idCounter = 0;
  PizzaSize size;
  List<Topping> toppings = new ArrayList<>();


  Map<PizzaSize, Integer> pizzaSizeCostMap; // = new HashMap<>();
  Map<Topping, Integer> pizzaToppingsCostMap; // = new HashMap<>();

  public SimplePizza(PizzaSize size) {
    this.size = size;
    id = ++idCounter;

    //pizzaSizeCostMap.put(PizzaSize.EXTRA_LARGE, 1100);
    //pizzaSizeCostMap.put(PizzaSize.LARGE, 900);
    //pizzaSizeCostMap.put(PizzaSize.MEDIUM, 700);
    //pizzaSizeCostMap.put(PizzaSize.SMALL, 500);
//
    //pizzaToppingsCostMap.put(Topping.CHEESE, 60);
    //pizzaToppingsCostMap.put(Topping.TOMATO, 30);
    //pizzaToppingsCostMap.put(Topping.PINEAPPLE, 90);
    //pizzaToppingsCostMap.put(Topping.SEAFOOD, 150);
    //pizzaToppingsCostMap.put(Topping.HAM, 70);
    //pizzaToppingsCostMap.put(Topping.VEGETABLES, 20);
    //pizzaToppingsCostMap.put(Topping.SALAMI, 50);
  }

  @Override
  public int getPizzaId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  @Override
  public List<Topping> getToppings() {
    return toppings;
  }

  @Override
  public PizzaSize getSize() {
    return size;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int calculatePrice() {

    int pizzaSizeCost = 0;

    //switch trough size variable. If case is found,
    //set pizzaSizeCost to corresponding price of size
    //if no case found, throw IllegalStateException
    switch (size) {
      case SMALL:
        pizzaSizeCost = pizzaSizeCostMap.get(PizzaSize.SMALL);
        break;
      case MEDIUM:
        pizzaSizeCost = pizzaSizeCostMap.get(PizzaSize.MEDIUM);
        break;
      case LARGE:
        pizzaSizeCost = pizzaSizeCostMap.get(PizzaSize.LARGE);
        break;
      case EXTRA_LARGE:
        pizzaSizeCost = pizzaSizeCostMap.get(PizzaSize.EXTRA_LARGE);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + size);
    }


    int pizzaToppingsCost = 0;

    //Iterate trough toppings list, add topping price to pizzaToppingsCost
    for (Topping t : toppings) {

      switch (t) {

        case TOMATO:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.TOMATO);
          break;
        case CHEESE:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.CHEESE);
          break;
        case SALAMI:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.SALAMI);
          break;
        case HAM:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.HAM);
          break;
        case PINEAPPLE:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.PINEAPPLE);
          break;
        case VEGETABLES:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.VEGETABLES);
          break;
        case SEAFOOD:
          pizzaToppingsCost = pizzaToppingsCost + pizzaToppingsCostMap.get(Topping.SEAFOOD);
          break;
        default:
          break;

      }
    }
    price = pizzaSizeCost + pizzaToppingsCost;
    return price;
  }

  @Override
  public int getPrice() {
    return price;
  }


}

