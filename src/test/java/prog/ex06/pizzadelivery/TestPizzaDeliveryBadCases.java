package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.TooManyToppingsException;
import prog.ex06.exercise.pizzadelivery.Topping;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;

public class TestPizzaDeliveryBadCases {

  private SimplePizzaDeliveryService PizzaDeliveryService;
  private SimplePizzaDeliveryService PizzaDeliveryService2;

  @Before
  public void setup() {
    PizzaDeliveryService = new SimplePizzaDeliveryService();

  }

  @After
  public void teardown() {
    PizzaDeliveryService= null;
    PizzaDeliveryService2= null;

  }

  @Test //@Ignore
  public void testGetOrderWithNotExistingOrderId() {
    int id = PizzaDeliveryService.createOrder(); //id = 1

    try {
      PizzaDeliveryService.getOrder(50);
      fail("getting order with id 50 should fail because there is only a order with id 1");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
      System.out.println(e.getMessage());
    }
  }

  @Test //@Ignore
  public void testRemovingPizzaWithNotExistingIdFromNotExistingOrderId() {

    int id = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);

    try {
      PizzaDeliveryService.removePizza(50, 50);
      fail("Should fail, because orderId 50 and pizzaId 50 dont exist");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
      System.out.println(e.getMessage());
    }
  }

  @Test //@Ignore
  public void testRemovingPizzaWithNotExistingIdFromExistingOrderId() {

    int id = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);

    try {
      PizzaDeliveryService.removePizza(1, 50);
      fail("Should fail, because orderId 50 and pizzaId 50 dont exist");
    } catch (IllegalArgumentException e) {
      assertTrue(true);
      System.out.println(e.getMessage());
    }

  }

  @Test //@Ignore
  public void testWhether2InstancesOfPizzaServiceInfluenceEachOther() {

    PizzaDeliveryService2 = new SimplePizzaDeliveryService();

    int id = PizzaDeliveryService.createOrder();
    int idPizza = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);
    try {
      PizzaDeliveryService.addTopping(idPizza, Topping.PINEAPPLE);
    } catch (TooManyToppingsException e) {
      e.getMessage();
    }

    int id2 = PizzaDeliveryService2.createOrder();
    int idPizza2 = PizzaDeliveryService2.addPizza(id2, PizzaSize.LARGE);
    try {
      PizzaDeliveryService2.addTopping(idPizza2, Topping.SEAFOOD);
    } catch (TooManyToppingsException e) {
      e.getMessage();
    }
  }

  @Test //@Ignore
  public void testWhetherTooManyToppingsAreAdded() {

    int id = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);
    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.CHEESE);
      PizzaDeliveryService.addTopping(pizzaId, Topping.TOMATO);
      PizzaDeliveryService.addTopping(pizzaId, Topping.PINEAPPLE);
      PizzaDeliveryService.addTopping(pizzaId, Topping.HAM);
      PizzaDeliveryService.addTopping(pizzaId, Topping.VEGETABLES);
      PizzaDeliveryService.addTopping(pizzaId, Topping.SEAFOOD);
      PizzaDeliveryService.addTopping(pizzaId, Topping.SALAMI);
      fail("after adding Seafood topping list size is 6, should fail after adding another one");
      PizzaDeliveryService.addTopping(pizzaId, Topping.CHEESE);
    } catch (TooManyToppingsException e) {
      assertTrue(true);
      System.out.println(e.getMessage());
    }
  }
}
