import org.junit.Test;

import betterpizza.AlaCartePizza;
import betterpizza.CheesePizza;
import betterpizza.ObservablePizza;
import betterpizza.VeggiePizza;
import pizza.Crust;
import pizza.Pizza;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

import static org.junit.Assert.assertEquals;

/**
 * The test class for pizzas.
 */
public class PizzaTest {
  private Pizza alacarte;
  private Pizza cheese;
  private Pizza halfCheese;

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

    assertEquals(null, customVeggie.hasTopping(ToppingName.Tomato));
    assertEquals(null, customVeggie.hasTopping(ToppingName.Jalapeno));
    assertEquals(ToppingPortion.Full, customVeggie.hasTopping(ToppingName.BlackOlive));
    assertEquals(10.5, customVeggie.cost(), 0.01);
  }

}