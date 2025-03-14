import org.junit.Test;
import org.junit.Before;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Pizza;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.*;

/**
 * Comprehensive test class for pizzas with improved mutation coverage.
 */
public class PizzaTest {
  private Pizza alacarte;
  private Pizza cheese;
  private Pizza halfCheese;

  @Before
  public void setUp() {
    alacarte = new pizza.AlaCartePizza(Size.Medium, Crust.Classic);
    alacarte.addTopping(ToppingName.Cheese, ToppingPortion.Full);
    alacarte.addTopping(ToppingName.Sauce, ToppingPortion.Full);

    cheese = new pizza.CheesePizza(Size.Large, Crust.Thin);

    halfCheese = new pizza.CheesePizza(Size.Medium, Crust.Stuffed);
    halfCheese.removeTopping(ToppingName.Cheese);
    halfCheese.addTopping(ToppingName.Cheese, ToppingPortion.LeftHalf);
  }

  @Test
  public void testOriginalPizzaInterface() {
    assertEquals(7.0, alacarte.cost(), 0.01);
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Cheese));

    alacarte.setCrust(Crust.Thin);
    alacarte.setSize(Size.Large);
    alacarte.removeTopping(ToppingName.Cheese);

    assertNull(alacarte.hasTopping(ToppingName.Cheese));
    assertEquals(8.0, alacarte.cost(), 0.01);

    assertEquals(ToppingPortion.Full, cheese.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, cheese.hasTopping(ToppingName.Sauce));
    assertEquals(9.0, cheese.cost(), 0.01);

    assertEquals(ToppingPortion.LeftHalf, halfCheese.hasTopping(ToppingName.Cheese));
    assertEquals(6.5, halfCheese.cost(), 0.01);
  }

  @Test
  public void testAlaCartePizzaBuilder() {
    ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(ToppingPortion.Full, alacarte.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.LeftHalf, alacarte.hasTopping(ToppingName.Jalapeno));
    assertNull(alacarte.hasTopping(ToppingName.BlackOlive));
  }

  @Test
  public void testPizzaBuilders() {
    ObservablePizza alacarte = new AlaCartePizza.AlaCartePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Cheese, ToppingPortion.Full)
            .addTopping(ToppingName.Sauce, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.Full)
            .addTopping(ToppingName.Onion, ToppingPortion.Full)
            .addTopping(ToppingName.Jalapeno, ToppingPortion.LeftHalf)
            .build();

    ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    ObservablePizza stuffedCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();

    ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(9.0, cheese.cost(), 0.01);
    assertEquals(7.0, stuffedCheese.cost(), 0.01);
    assertEquals(11.5, veggie.cost(), 0.01);
  }

  @Test
  public void testLeftHalfCheese() {
    ObservablePizza leftHalfCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .leftHalfCheese()
            .build();

    assertEquals(ToppingPortion.LeftHalf, leftHalfCheese.hasTopping(ToppingName.Cheese));
    assertEquals(8.5, leftHalfCheese.cost(), 0.01);
  }

  @Test
  public void testCustomizedVeggiePizza() {
    ObservablePizza customVeggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .noTomato()
            .noJalapeno()
            .build();

    assertNull(customVeggie.hasTopping(ToppingName.Tomato));
    assertNull(customVeggie.hasTopping(ToppingName.Jalapeno));
    assertEquals(ToppingPortion.Full, customVeggie.hasTopping(ToppingName.BlackOlive));
    assertEquals(10.5, customVeggie.cost(), 0.01);
  }

  /**
   * Test for right half cheese pizza.
   */
  @Test
  public void testRightHalfCheese() {
    ObservablePizza rightHalfCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Large)
            .rightHalfCheese()
            .build();

    assertEquals(ToppingPortion.RightHalf, rightHalfCheese.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, rightHalfCheese.hasTopping(ToppingName.Sauce));
    assertEquals(8.5, rightHalfCheese.cost(), 0.01);
  }

  /**
   * Test for cheese pizza with no cheese (just sauce).
   */
  @Test
  public void testNoCheese() {
    ObservablePizza noCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Medium)
            .noCheese()
            .build();

    assertNull(noCheese.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, noCheese.hasTopping(ToppingName.Sauce));
    assertEquals(6.0, noCheese.cost(), 0.01);
  }

  /**
   * Test for adding the addTopping method to VeggiePizzaBuilder.
   */
  @Test
  public void testVeggiePizzaWithExtraToppings() {
    ObservablePizza veggiePizza = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Alfredo, ToppingPortion.Full) // Replace red sauce with white
            .noSauce() // Remove red sauce
            .build();

    assertNull(veggiePizza.hasTopping(ToppingName.Sauce));
    assertEquals(ToppingPortion.Full, veggiePizza.hasTopping(ToppingName.Alfredo));
    assertEquals(ToppingPortion.Full, veggiePizza.hasTopping(ToppingName.Cheese));
    assertEquals(9.75, veggiePizza.cost(), 0.01);
  }

  /**
   * Test for adding the addTopping method to CheesePizzaBuilder.
   */
  @Test
  public void testCheesePizzaWithExtraToppings() {
    ObservablePizza cheesePizza = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Classic)
            .size(Size.Medium)
            .addTopping(ToppingName.Pepperoni, ToppingPortion.Full)
            .addTopping(ToppingName.GreenPepper, ToppingPortion.RightHalf)
            .build();

    assertEquals(ToppingPortion.Full, cheesePizza.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, cheesePizza.hasTopping(ToppingName.Sauce));
    assertEquals(ToppingPortion.Full, cheesePizza.hasTopping(ToppingName.Pepperoni));
    assertEquals(ToppingPortion.RightHalf, cheesePizza.hasTopping(ToppingName.GreenPepper));
    assertEquals(8.0, cheesePizza.cost(), 0.01);
  }

  /**
   * Test protected constructor edge cases.
   */
  @Test
  public void testProtectedConstructorEdgeCases() {
    try {
      ObservablePizza invalidPizza = new AlaCartePizza.AlaCartePizzaBuilder()
              .crust(Crust.Classic)
              .build();
      fail("Should throw IllegalStateException when size is null");
    } catch (IllegalStateException e) {
      // Expected exception
    }

    ObservablePizza defaultCrustPizza = new AlaCartePizza.AlaCartePizzaBuilder()
            .size(Size.Medium)
            .build();
    assertEquals(5.0, defaultCrustPizza.cost(), 0.01);
  }

  /**
   * Test veggie pizza with all toppings removed.
   */
  @Test
  public void testVeggiePizzaNoToppings() {
    ObservablePizza strippedVeggie = new VeggiePizza.VeggiePizzaBuilder()
            .size(Size.Medium)
            .crust(Crust.Stuffed)
            .noCheese()
            .noSauce()
            .noBlackOlive()
            .noGreenPepper()
            .noOnion()
            .noJalapeno()
            .noTomato()
            .build();

    assertNull(strippedVeggie.hasTopping(ToppingName.Cheese));
    assertNull(strippedVeggie.hasTopping(ToppingName.Sauce));
    assertNull(strippedVeggie.hasTopping(ToppingName.BlackOlive));
    assertNull(strippedVeggie.hasTopping(ToppingName.GreenPepper));
    assertNull(strippedVeggie.hasTopping(ToppingName.Onion));
    assertNull(strippedVeggie.hasTopping(ToppingName.Jalapeno));
    assertNull(strippedVeggie.hasTopping(ToppingName.Tomato));
    assertEquals(5.0, strippedVeggie.cost(), 0.01);
  }

  /**
   * Test for multiple mutations of a cheese pizza using the builder.
   */
  @Test
  public void testMultipleMutationsOfCheesePizza() {
    ObservablePizza multiMutatedCheese = new CheesePizza.CheesePizzaBuilder()
            .size(Size.Small)
            .crust(Crust.Thin)
            .noCheese()
            .addTopping(ToppingName.Cheese, ToppingPortion.RightHalf)
            .build();

    assertEquals(ToppingPortion.RightHalf, multiMutatedCheese.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, multiMutatedCheese.hasTopping(ToppingName.Sauce));
    assertEquals(4.5, multiMutatedCheese.cost(), 0.01);

    ObservablePizza anotherMutatedCheese = new CheesePizza.CheesePizzaBuilder()
            .size(Size.Large)
            .crust(Crust.Stuffed)
            .leftHalfCheese()
            .rightHalfCheese()
            .build();

    assertEquals(ToppingPortion.RightHalf, anotherMutatedCheese.hasTopping(ToppingName.Cheese));
    assertEquals(8.5, anotherMutatedCheese.cost(), 0.01);
  }

  /**
   * Test for null toppings in the map (should never happen, but testing for coverage).
   */
  @Test
  public void testNullValuesInToppingsMap() {
    ObservablePizza emptyPizza = new AlaCartePizza.AlaCartePizzaBuilder()
            .size(Size.Small)
            .crust(Crust.Classic)
            .build();
    assertEquals(3.0, emptyPizza.cost(), 0.01);
    assertNull(emptyPizza.hasTopping(ToppingName.Cheese));
  }

  /**
   * Test all price calculations for accuracy.
   */
  @Test
  public void testAllPriceCalculations() {
    for (Size size : Size.values()) {
      for (Crust crust : Crust.values()) {
        ObservablePizza basicPizza = new AlaCartePizza.AlaCartePizzaBuilder()
                .size(size)
                .crust(crust)
                .build();

        assertEquals(size.getBaseCost(), basicPizza.cost(), 0.01);

        for (ToppingPortion portion : ToppingPortion.values()) {
          ObservablePizza oneToppingPizza = new AlaCartePizza.AlaCartePizzaBuilder()
                  .size(size)
                  .crust(crust)
                  .addTopping(ToppingName.Cheese, portion)
                  .build();

          double expectedCost = size.getBaseCost() +
                  (ToppingName.Cheese.getCost() * portion.getCostMultiplier());
          assertEquals(expectedCost, oneToppingPizza.cost(), 0.01);
        }
      }
    }
  }

  /**
   * Test various combinations of pizza toppings on the original Pizza interface.
   */
  @Test
  public void testOriginalPizzaToppings() {
    Pizza customPizza = new pizza.AlaCartePizza(Size.Medium, Crust.Classic);

    customPizza.addTopping(ToppingName.Cheese, ToppingPortion.Full);
    customPizza.addTopping(ToppingName.Sauce, ToppingPortion.Full);
    customPizza.addTopping(ToppingName.Pepperoni, ToppingPortion.LeftHalf);
    customPizza.addTopping(ToppingName.GreenPepper, ToppingPortion.RightHalf);
    customPizza.addTopping(ToppingName.Onion, ToppingPortion.Full);

    assertEquals(ToppingPortion.Full, customPizza.hasTopping(ToppingName.Cheese));
    assertEquals(ToppingPortion.Full, customPizza.hasTopping(ToppingName.Sauce));
    assertEquals(ToppingPortion.LeftHalf, customPizza.hasTopping(ToppingName.Pepperoni));
    assertEquals(ToppingPortion.RightHalf, customPizza.hasTopping(ToppingName.GreenPepper));
    assertEquals(ToppingPortion.Full, customPizza.hasTopping(ToppingName.Onion));

    double expectedCost = Size.Medium.getBaseCost() +
            ToppingName.Cheese.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Sauce.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Pepperoni.getCost() * ToppingPortion.LeftHalf.getCostMultiplier() +
            ToppingName.GreenPepper.getCost() * ToppingPortion.RightHalf.getCostMultiplier() +
            ToppingName.Onion.getCost() * ToppingPortion.Full.getCostMultiplier();

    assertEquals(expectedCost, customPizza.cost(), 0.01);

    customPizza.removeTopping(ToppingName.Cheese);
    assertNull(customPizza.hasTopping(ToppingName.Cheese));

    customPizza.setSize(Size.Large);
    customPizza.setCrust(Crust.Thin);

    expectedCost = Size.Large.getBaseCost() +
            ToppingName.Sauce.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Pepperoni.getCost() * ToppingPortion.LeftHalf.getCostMultiplier() +
            ToppingName.GreenPepper.getCost() * ToppingPortion.RightHalf.getCostMultiplier() +
            ToppingName.Onion.getCost() * ToppingPortion.Full.getCostMultiplier();

    assertEquals(expectedCost, customPizza.cost(), 0.01);
  }

  /**
   * Test all the methods of the VeggiePizza builder.
   */
  @Test
  public void testAllVeggiePizzaBuilderMethods() {
    ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
            .size(Size.Large)
            .crust(Crust.Thin)
            .noBlackOlive()
            .noGreenPepper()
            .noOnion()
            .noJalapeno()
            .noTomato()
            .noCheese()
            .noSauce()
            .build();

    for (ToppingName topping : new ToppingName[] {
            ToppingName.BlackOlive, ToppingName.GreenPepper, ToppingName.Onion,
            ToppingName.Jalapeno, ToppingName.Tomato, ToppingName.Cheese, ToppingName.Sauce
    }) {
      assertNull("Topping " + topping + " should be null", veggie.hasTopping(topping));
    }

    assertEquals(7.0, veggie.cost(), 0.01);

    ObservablePizza customVeggie = new VeggiePizza.VeggiePizzaBuilder()
            .size(Size.Medium)
            .crust(Crust.Classic)
            .noTomato()
            .addTopping(ToppingName.Tomato, ToppingPortion.LeftHalf) // Add it back but half
            .build();

    assertEquals(ToppingPortion.LeftHalf, customVeggie.hasTopping(ToppingName.Tomato));

    double expectedCost = Size.Medium.getBaseCost() +
            ToppingName.Cheese.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Sauce.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.BlackOlive.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.GreenPepper.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Onion.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Jalapeno.getCost() * ToppingPortion.Full.getCostMultiplier() +
            ToppingName.Tomato.getCost() * ToppingPortion.LeftHalf.getCostMultiplier();

    assertEquals(expectedCost, customVeggie.cost(), 0.01);
  }
}