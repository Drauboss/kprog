package prog.ex06.pizzadelivery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;

public class TestPizzaDeliveryBadCases {

  private SimplePizzaDeliveryService PizzaDeliveryService;

  @Before
  public void setup() {
    PizzaDeliveryService = new SimplePizzaDeliveryService();
  }

  @After
  public void teardown() {
    PizzaDeliveryService= null;

  }

  @Test //@Ignore
  public void testGetOrderWithNotExistingOrderId() {

  }

  @Test //@Ignore
  public void testRemovingPizzaWithNotExistingIdFromNotExistingOrderId() {

  }

  @Test //@Ignore
  public void testRemovingPizzaWithNotExistingIdFromExistingOrderId() {

  }

  @Test //@Ignore
  public void testWhether2InstancesOfPizzaServiceInfluenceEachOther() {

  }

  @Test //@Ignore
  public void testWhetherTooManyToppingsAreAdded() {

  }

}
