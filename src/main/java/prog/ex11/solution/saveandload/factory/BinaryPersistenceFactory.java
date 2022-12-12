package prog.ex11.solution.saveandload.factory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.exercise.saveandload.pizzadelivery.Pizza;
import prog.ex11.exercise.saveandload.pizzadelivery.PizzaSize;
import prog.ex11.exercise.saveandload.pizzadelivery.Topping;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;
import prog.ex11.solution.saveandload.pizzadelivery.SimplePizza;

/**
 * Simple and straight-forward implementation of the PersistenceFactory interface. This
 * implementation uses Data Stream to write and read primitive types in binary.
 */
public class BinaryPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(BinaryPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {

    int orderId = order.getOrderId();
    int orderValue = order.getValue();
    List<Pizza> pizzaList = order.getPizzaList();
    int numOfPizzas = order.getPizzaList().size();

    try (DataOutputStream os = new DataOutputStream(
        new BufferedOutputStream(new FileOutputStream(file)))) {

      os.writeInt(orderId);
      os.writeInt(orderValue);
      os.writeInt(numOfPizzas);
      for (int i = 0; i < numOfPizzas; i++) {
        os.writeInt(pizzaList.get(i).getPizzaId());
        os.writeInt(pizzaList.get(i).getPrice());
        os.writeUTF(pizzaList.get(i).getSize().toString());
        os.writeInt(pizzaList.get(i).getToppings().size());
        for (int j = 0; j < pizzaList.get(i).getToppings().size(); j++) {
          os.writeUTF(pizzaList.get(i).getToppings().get(j).toString());
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }


  }



  /**
   * checks if @param topping is listed in Topping enum.
   *
   * @param topping topping to check.
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

  SimpleOrder order;

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {

    order = new SimpleOrder();

    try (DataInputStream is = new DataInputStream(
        new BufferedInputStream(new FileInputStream(file)))) {

      int orderId;
      int orderValue;
      int numOfPizzas;

      List<Pizza> pizzaList = order.getPizzaList();

      while (true) {
        orderId = is.readInt();
        orderValue = is.readInt();
        numOfPizzas = is.readInt();

        if (!String.valueOf(orderId).matches("^[0-9]+$") || !String.valueOf(orderValue)
            .matches("^[0-9]+$") || !String.valueOf(numOfPizzas).matches(
            "^[0-9]+$")) {
          throw new WrongOrderFormatException();
        }

        order.setOrderId(orderId);
        order.setValue(orderValue);

        for (int i = 0; i < numOfPizzas; i++) {
          int pizzaId = is.readInt();
          int pizzaPrice = is.readInt();
          PizzaSize pizzaSize = PizzaSize.valueOf(is.readUTF());
          int toppingSize = is.readInt();

          if (!String.valueOf(pizzaId).matches("^[0-9]+$") || !String.valueOf(pizzaPrice)
              .matches("^[0-9]+$") || !String.valueOf(toppingSize).matches(
              "^[0-9]+$")) {
            throw new WrongOrderFormatException();
          }

          SimplePizza pizza = new SimplePizza(pizzaSize);
          pizza.setId(pizzaId);
          pizza.setPrice(pizzaPrice);

          for (int j = 0; j < toppingSize; j++) {

            String toppingString = is.readUTF();
            Topping topping = Topping.valueOf(toppingString);

            //if topping in file does not exist in Topping Enum, throw exceptc.
            if (!(contains(toppingString))) {
              throw new WrongOrderFormatException();
            }

            pizza.getToppings().add(topping);
          }
          order.getPizzaList().add(pizza);
        }
      }

    } catch (EOFException e) {
      System.out.println("eof");
    }
    return order;
  }
}
