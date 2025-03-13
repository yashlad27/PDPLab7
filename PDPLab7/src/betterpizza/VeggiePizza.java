package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * Class represents a vegetarian pizza.
 */
public class VeggiePizza extends AlaCartePizza {

  /**
   * Protected constructor that takes in the size, crust and a map of toppings.
   *
   * @param size     the size of this pizza
   * @param crust    the crust of this pizza
   * @param toppings the toppings of this pizza
   */
  protected VeggiePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  public static class VeggiePizzaBuilder extends PizzaBuilder {
    private Size size;
    private Crust crust;
    private Map<ToppingName, ToppingPortion> toppings;

    public VeggiePizzaBuilder() {
      this.toppings = new HashMap<>();
      this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
      this.toppings.put(ToppingName.BlackOlive, ToppingPortion.Full);
      this.toppings.put(ToppingName.GreenPepper, ToppingPortion.Full);
      this.toppings.put(ToppingName.Onion, ToppingPortion.Full);
      this.toppings.put(ToppingName.Jalapeno, ToppingPortion.Full);
      this.toppings.put(ToppingName.Tomato, ToppingPortion.Full);
    }

    @Override
    public VeggiePizzaBuilder crust(Crust crust) {
      this.crust = crust;
      return this;
    }

    @Override
    public VeggiePizzaBuilder size(Size size) {
      this.size = size;
      return this;
    }

    @Override
    public ObservablePizza build() {
      if(size==null){
        throw new IllegalStateException("Size must be specified");
      }
      return new VeggiePizza(size, crust!=null?crust:Crust.Classic, toppings);
    }
  }
}
