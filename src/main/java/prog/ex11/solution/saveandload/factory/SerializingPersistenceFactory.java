package prog.ex11.solution.saveandload.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import prog.ex11.exercise.saveandload.factory.PersistenceFactory;
import prog.ex11.exercise.saveandload.factory.WrongOrderFormatException;
import prog.ex11.exercise.saveandload.pizzadelivery.Order;
import prog.ex11.solution.saveandload.pizzadelivery.SimpleOrder;

/**
 * Simple and straight-forward implementation of the PersistenceFactory interface. This
 * implementation uses Serialization and Deserialization with Object Streams.
 */
public class SerializingPersistenceFactory implements PersistenceFactory {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SerializingPersistenceFactory.class);

  @Override
  public void save(final File file, final Order order) throws IOException {
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
      outputStream.writeObject(order);
    }
  }

  @Override
  public Order load(final File file) throws IOException, WrongOrderFormatException {
    SimpleOrder order;
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
      try {
        order = (SimpleOrder) inputStream.readObject();
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return order;
  }
}
