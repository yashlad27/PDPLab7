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


//  @Before
//  public void setup() {
//    alacarte = new AlaCartePizza(Size.Medium,Crust.Classic);
//    alacarte.addTopping(ToppingName.Cheese, ToppingPortion.Full);
//    alacarte.addTopping(ToppingName.Sauce,ToppingPortion.Full);
//    alacarte.addTopping(ToppingName.GreenPepper,ToppingPortion.Full);
//    alacarte.addTopping(ToppingName.Onion,ToppingPortion.Full);
//    alacarte.addTopping(ToppingName.Jalapeno,ToppingPortion.LeftHalf);
//
//    cheese = new CheesePizza(Size.Large,Crust.Thin);
//
//    halfCheese = new CheesePizza(Size.Large,Crust.Thin);
//    //put cheese only on left half
//    halfCheese.addTopping(ToppingName.Cheese,ToppingPortion.LeftHalf);
//  }

//  @Test
//  public void testCost() {
//    assertEquals(8.25,alacarte.cost(),0.01);
//    assertEquals(9,cheese.cost(),0.01);
//    assertEquals(8.5,halfCheese.cost(),0.01);
//
//  }

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

    // Create a cheese pizza
    ObservablePizza cheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    // Create a stuffed crust, medium cheese pizza
    ObservablePizza stuffedCheese = new CheesePizza.CheesePizzaBuilder()
            .crust(Crust.Stuffed)
            .size(Size.Medium)
            .build();

    // Create a thin crust, large veggie pizza
    ObservablePizza veggie = new VeggiePizza.VeggiePizzaBuilder()
            .crust(Crust.Thin)
            .size(Size.Large)
            .build();

    // Test costs
    assertEquals(8.25, alacarte.cost(), 0.01);
    assertEquals(9.0, cheese.cost(), 0.01);
    assertEquals(7.0, stuffedCheese.cost(), 0.01);
    assertEquals(11.0, veggie.cost(), 0.01);
  }

}