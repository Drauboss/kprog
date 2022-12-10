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
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizzaDeliveryService;


/**
 * Simple and straight-forward implementation of the PersistenceFactory interface.
 * This implementation uses text in a csv format.
 */
public class PlainTextPersistenceFactory implements PersistenceFactory {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PlainTextPersistenceFactory.class);

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

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

      List<String> orderTokens = new ArrayList<>();
      List<String> pizzaTokens = new ArrayList<>();
      List toppings = new ArrayList<>();

      String line = reader.readLine();
      StringTokenizer tokenizer = new StringTokenizer(line, ";");
      while (tokenizer.hasMoreElements()) {
        orderTokens.add(tokenizer.nextToken());
      }

      System.out.println("order infos");
      System.out.println(orderTokens);

      String orderId = orderTokens.get(0);
      String ordervalue = orderTokens.get(1);
      String numOfPizzas = orderTokens.get(2);

      for (int i = 0; i < Integer.parseInt(numOfPizzas); i++) {

        String linePizza = reader.readLine();
        while (tokenizer.hasMoreElements()) {
          pizzaTokens.add(tokenizer.nextToken());
        }

        int toppingsSize = pizzaTokens.size() - 3;

        String pizzaId = pizzaTokens.get(0);
        String pizzaPrice = pizzaTokens.get(1);
        String pizzaSize = pizzaTokens.get(2);

        for (int j = 3; j < toppingsSize + 3; j++) {
          toppings.add(Topping.valueOf(pizzaTokens.get(i)));
        }
        pizzaTokens.clear();
      }

      SimplePizzaDeliveryService service = new SimplePizzaDeliveryService();
      SimpleOrder order = new SimpleOrder();
      order.setOrderId(Integer.parseInt(orderId));
      order.getPizzaList().add(new SimplePizza());

      for (int i = 0; i < Integer.parseInt(numOfPizzas); i++) {
        order.getPizzaList().add(new SimplePizza())
      }




    }




    return null;
  }
}
