package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import prog.ex06.solution.pizzadelivery.SimplePizzaDeliveryService;
import org.junit.Test;


public class TestPizzaDeliveryGoodCases {

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
  public void testWhether2EmptyOrderObjectsDiffer() {

  }

  @Test //@Ignore
  public void testWhetherEmptyOrderObjectsReturnsEmptyPizzaList() {

  }

  @Test //@Ignore
  public void testWhetherPizzaIsAddedToPizzaListInOrder() {

  }

  @Test //@Ignore
  public void testWhetherRemovedPizzaIsNoLongerInPizzaList() {

  }

  @Test //@Ignore
  public void testCorrectPizzaSizePrices() {

  }

  @Test //@Ignore
  public void testCorrectPizzaToppingsPrices() {

  }

  @Test //@Ignore
  public void testWhetherAddingToppingAddsToToppingsList() {

  }

  @Test //@Ignore
  public void testWhetherRemovingToppingRemovesFromToppingsList() {

  }

  @Test //@Ignore
  public void testCorrectTotalPriceOfOrder() {

  }
}
