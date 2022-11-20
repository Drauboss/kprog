package prog.ex10.solution.javafx4pizzadelivery.gui;

public class PizzaEntry {

  private static int idCounter;
  private int id;
  private String pizzaSize;
  private int toppingsNumber;

  /**
   * Constructor.
   */
  public PizzaEntry(final String pizzaSize, final int toppingsNumber) {
    this.pizzaSize = pizzaSize;
    this.toppingsNumber = toppingsNumber;
    id = ++idCounter;
  }

  public int getId() {
    return id;
  }

  public String getPizzaSize() {
    return pizzaSize;
  }

  public int getToppingsNumber() {
    return toppingsNumber;
  }

  @Override
  public String toString() {
    return pizzaSize + ", " + toppingsNumber + " Toppings";
  }
}

