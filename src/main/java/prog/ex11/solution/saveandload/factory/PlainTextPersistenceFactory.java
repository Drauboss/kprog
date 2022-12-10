package prog.ex11.solution.saveandload.factory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;


/**
 * Simple and straight-forward implementation of the PersistenceFactory interface. This
 * implementation uses text in a csv format.
 */
public class PlainTextPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(
      PlainTextPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {

    List<String> orderTokens = new ArrayList<>();
    List<String> pizzaTokens = new ArrayList<>();

    String orderId = String.valueOf(order.getOrderId());
    String ordervalue = String.valueOf(order.getValue());
    String numOfPizzas = String.valueOf(order.getPizzaList().size());

    orderTokens.add(orderId);
    orderTokens.add(";");
    orderTokens.add(ordervalue);
    orderTokens.add(";");
    orderTokens.add(numOfPizzas);

    for (int i = 0; i < order.getPizzaList().size(); i++) {
      String pizzaId = String.valueOf(order.getPizzaList().get(i).getPizzaId());
      String pizzaValue = String.valueOf(order.getPizzaList().get(i).getPrice());
      String pizzaSize = String.valueOf(order.getPizzaList().get(i).getSize());

      pizzaTokens.add(pizzaId);
      pizzaTokens.add(";");
      pizzaTokens.add(pizzaValue);
      pizzaTokens.add(";");
      pizzaTokens.add(pizzaSize);

      for (int j = 0; j < order.getPizzaList().get(i).getToppings().size(); j++) {
        String topping = String.valueOf(order.getPizzaList().get(i).getToppings().get(j));
        pizzaTokens.add(";");
        pizzaTokens.add(topping);
      }

      //new line only after last topping
      if (i != order.getPizzaList().size() - 1) {
        pizzaTokens.add("\n");
      }
    }

    try (PrintWriter wr = new PrintWriter(new FileWriter(file))) {
      //print order infos
      for (int i = 0; i < orderTokens.size(); i++) {
        wr.print(orderTokens.get(i));
      }
      wr.println();

      //print Pizza infos
      for (int i = 0; i < pizzaTokens.size(); i++) {
        wr.print(pizzaTokens.get(i));
      }

    }

  }


  /**
   * checks if @param topping is listed in Topping enum.
   *
   * @param topping
   * @return true if it contains, false if not.
   */
  public boolean contains(String topping) {
    for (Topping c : Topping.values()) {
      if (c.name().equals(topping)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

      SimpleOrder order = new SimpleOrder();
      StringTokenizer tokenizer;
      List<String> orderTokens = new ArrayList<>();
      List<String> pizzaTokens = new ArrayList<>();
      //List toppings = new ArrayList<>();

      orderTokens.clear();
      pizzaTokens.clear();
      //toppings.clear();

      //read first line with order infos and save em
      String line = reader.readLine();
      tokenizer = new StringTokenizer(line, ";");
      while (tokenizer.hasMoreElements()) {
        orderTokens.add(tokenizer.nextToken());
      }

      System.out.println(orderTokens);
      if (orderTokens.size() != 3) {
        throw new WrongOrderFormatException();
      }

      String orderId = orderTokens.get(0);
      String ordervalue = orderTokens.get(1);
      String numOfPizzas = orderTokens.get(2);


      if (!orderId.matches("^[0-9]+$") || !ordervalue.matches("^[0-9]+$") || !numOfPizzas.matches("^[0-9]+$")) {
        throw new WrongOrderFormatException();
      }

      order.setOrderId(Integer.parseInt(orderId));
      order.setValue(Integer.parseInt(ordervalue));

      //iterate trough rest of the lines (number of pizzas equals rest of lines)
      for (int i = 0; i < Integer.parseInt(numOfPizzas); i++) {

        pizzaTokens.clear();
        //toppings.clear();

        //add all entrys of line to pizzaTokens array
        String linePizza = reader.readLine();
        tokenizer = new StringTokenizer(linePizza, ";");
        while (tokenizer.hasMoreElements()) {
          pizzaTokens.add(tokenizer.nextToken());
        }

        //System.out.println(pizzaTokens);

        //number of toppings
        int toppingsSize = pizzaTokens.size() - 3;
        System.out.println(toppingsSize);

        //first 3 entrys of pizza line
        String pizzaId = pizzaTokens.get(0);
        String pizzaPrice = pizzaTokens.get(1);
        String pizzaSize = pizzaTokens.get(2);

        if (!pizzaId.matches("^[0-9]+$") || !pizzaPrice.matches("^[0-9]+$")) {
          throw new WrongOrderFormatException();
        }

        //create new pizza with correct size and set id
        SimplePizza pizza = new SimplePizza(PizzaSize.valueOf(pizzaSize));
        pizza.setId(Integer.parseInt(pizzaId));

        //add all toppings of file to the pizza
        //first topping starts at 4th position of line( 3rd element in array)
        for (int j = 3; j < pizzaTokens.size(); j++) {

          //if topping in file does not exist in Topping Enum, throw exceptc.
          if (!(contains(pizzaTokens.get(j)))) {
            throw new WrongOrderFormatException();
          }
          pizza.getToppings().add(Topping.valueOf(pizzaTokens.get(j)));
        }

        pizza.setPrice(Integer.parseInt(pizzaPrice));
        order.getPizzaList().add(pizza);
      }
      return order;
    }
  }
}
