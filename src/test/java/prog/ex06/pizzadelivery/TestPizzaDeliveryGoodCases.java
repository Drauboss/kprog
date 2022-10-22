package prog.ex06.pizzadelivery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import prog.ex06.exercise.pizzadelivery.Pizza;
import prog.ex06.exercise.pizzadelivery.PizzaSize;
import prog.ex06.exercise.pizzadelivery.TooManyToppingsException;
import prog.ex06.exercise.pizzadelivery.Topping;
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

    int id1 = PizzaDeliveryService.createOrder();
    int id2 = PizzaDeliveryService.createOrder();

    assertNotEquals("Two empty Order Objects should be different",
        PizzaDeliveryService.getOrder(id1), PizzaDeliveryService.getOrder(id2));

  }

  @Test //@Ignore
  public void testWhetherEmptyOrderObjectsReturnsEmptyPizzaList() {

    List<Pizza> emptyPizzaList = new ArrayList<>();
    int id1 = PizzaDeliveryService.createOrder();
    assertEquals("Empty Order should return empty Pizza List",
        emptyPizzaList, PizzaDeliveryService.getOrder(id1).getPizzaList());
  }

  @Test //@Ignore
  public void testWhetherPizzaIsAddedToPizzaListInOrder() {


    int id1 = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id1, PizzaSize.LARGE);


    assertEquals("pizzaId should be equals to pizzaId in pizza list",
        pizzaId, PizzaDeliveryService.getOrder(id1).getPizzaList().get(0).getPizzaId());

  }

  @Test //@Ignore
  public void testWhetherRemovedPizzaIsNoLongerInPizzaList() {

    List<Pizza> emptyPizzaList = new ArrayList<>();
    int id1 = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id1, PizzaSize.LARGE);


    //System.out.println(id1);
    //System.out.println(pizzaId);
    //System.out.println(PizzaDeliveryService.getOrder(id1).getPizzaList());

    PizzaDeliveryService.removePizza(id1, pizzaId);

    System.out.println(PizzaDeliveryService.getOrder(id1).getPizzaList());
    assertEquals("Pizza List should be empty",
        emptyPizzaList, PizzaDeliveryService.getOrder(id1).getPizzaList());



  }

  @Test //@Ignore
  public void testCorrectPizzaSizePrices() {

    Map<PizzaSize, Integer> tmpPizzaSizeCostMap = PizzaDeliveryService.getPizzaSizePriceList();


    assertEquals("Price should be 1100", (Integer)1100, tmpPizzaSizeCostMap.get(PizzaSize.EXTRA_LARGE));
    assertEquals("Price should be 900", (Integer)900, tmpPizzaSizeCostMap.get(PizzaSize.LARGE));
    assertEquals("Price should be 700", (Integer)700, tmpPizzaSizeCostMap.get(PizzaSize.MEDIUM));
    assertEquals("Price should be 500", (Integer)500, tmpPizzaSizeCostMap.get(PizzaSize.SMALL));

  }

  @Test //@Ignore
  public void testCorrectPizzaToppingsPrices() {

    Map<Topping, Integer> pizzaToppingsCostMap = PizzaDeliveryService.getToppingsPriceList();

    assertEquals("Price should be 30", (Integer)30, pizzaToppingsCostMap.get(Topping.TOMATO));
    assertEquals("Price should be 60", (Integer)60, pizzaToppingsCostMap.get(Topping.CHEESE));
    assertEquals("Price should be 50", (Integer)50, pizzaToppingsCostMap.get(Topping.SALAMI));
    assertEquals("Price should be 70", (Integer)70, pizzaToppingsCostMap.get(Topping.HAM));
    assertEquals("Price should be 90", (Integer)90, pizzaToppingsCostMap.get(Topping.PINEAPPLE));
    assertEquals("Price should be 20", (Integer)20, pizzaToppingsCostMap.get(Topping.VEGETABLES));
    assertEquals("Price should be 150", (Integer)150, pizzaToppingsCostMap.get(Topping.SEAFOOD));


  }

  @Test //@Ignore
  public void testWhetherAddingToppingAddsToToppingsList() {
    int id = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);

    //Expected toppings
    Topping topping1 = Topping.CHEESE;
    Topping topping2 = Topping.HAM;

    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.CHEESE);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }
    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.HAM);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }

    assertEquals("Topping should be Cheese", topping1,
        PizzaDeliveryService.getOrder(id).getPizzaList().get(0).getToppings().get(0)
    );
    assertEquals("Topping should be Ham", topping2,
        PizzaDeliveryService.getOrder(id).getPizzaList().get(0).getToppings().get(1)
    );
  }

  @Test //@Ignore
  public void testWhetherRemovingToppingRemovesFromToppingsList() {
    int id = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);

    //Expected toppings
    Topping topping1 = Topping.CHEESE;
    Topping topping2 = Topping.HAM;

    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.CHEESE);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }
    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.HAM);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }

    try {
      PizzaDeliveryService.removeTopping(pizzaId, Topping.HAM);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    assertEquals("Topping should be Cheese", topping1,
        PizzaDeliveryService.getOrder(id).getPizzaList().get(0).getToppings().get(0));

    try {
      assertEquals("Topping should be Ham", topping2,
          PizzaDeliveryService.getOrder(id).getPizzaList().get(0).getToppings().get(1));
      fail("When ham is removed accessing index 1 should throw a indexOutOfBound Exception");

    } catch (IndexOutOfBoundsException e) {
      assertTrue(true);
      //success
    }


  }

  @Test //@Ignore
  public void testCorrectTotalPriceOfOrder() {
    int id = PizzaDeliveryService.createOrder();
    int pizzaId = PizzaDeliveryService.addPizza(id, PizzaSize.LARGE);
    int pizzaId2 = PizzaDeliveryService.addPizza(id, PizzaSize.SMALL);


    int expectedPrice = 900 + 500 + 60 + 30 + 60;
    //add cheese to pizza 1
    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.CHEESE);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }
    //add tomato to pizza 1
    try {
      PizzaDeliveryService.addTopping(pizzaId, Topping.TOMATO);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }
    //add cheese to pizza 2
    try {
      PizzaDeliveryService.addTopping(pizzaId2, Topping.CHEESE);
    } catch (TooManyToppingsException e) {
      System.out.println(e.getMessage());
    }

    assertEquals("Total price of order should be: " + expectedPrice, expectedPrice,
        PizzaDeliveryService.getOrder(id).getValue());
  }
}
