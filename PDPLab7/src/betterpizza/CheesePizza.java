package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This Class represents a cheese pizza.
 */
public class CheesePizza extends AlaCartePizza {

  /**
   * Protected constructor that takes in the size, crust and map of toppings.
   *
   * @param size     size of this pizza.
   * @param crust    crust of this pizza.
   * @param toppings toppings of this pizza.
   */
  protected CheesePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    super(size, crust, toppings);
  }

  public static class CheesePizzaBuilder extends PizzaBuilder {
    private Size size;
    private Crust crust;
    private Map<ToppingName, ToppingPortion> toppings;

    public CheesePizzaBuilder() {
      this.toppings = new HashMap<>();
      this.toppings.put(ToppingName.Cheese, ToppingPortion.Full);
      this.toppings.put(ToppingName.Sauce, ToppingPortion.Full);
    }

    @Override
    public CheesePizzaBuilder crust(Crust crust) {
      this.crust = crust;
      return this;
    }

    @Override
    public CheesePizzaBuilder size(Size size) {
      this.size = size;
      return this;
    }

    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalStateException("Size must be specified");
      }
      return new CheesePizza(size, crust != null ? crust : Crust.Classic, toppings);
    }

  }
}
