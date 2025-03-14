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

  /**
   * Builder class for cheesePizza.
   */
  public static class CheesePizzaBuilder extends PizzaBuilder<CheesePizzaBuilder> {
    private Size size;
    private Crust crust;
    private Map<ToppingName, ToppingPortion> toppings;

    /**
     * Create a new CheesePizzaBuilder with default toppings for a cheese pizza.
     */
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

    /**
     * Put cheese only on the left half of the pizza.
     *
     * @return this builder.
     */
    public CheesePizzaBuilder noCheese() {
      this.toppings.remove(ToppingName.Cheese);
      return returnBuilder();
    }

    /**
     * Put cheese only on the left half of the pizza.
     *
     * @return this builder.
     */
    public CheesePizzaBuilder leftHalfCheese() {
      this.toppings.put(ToppingName.Cheese, ToppingPortion.LeftHalf);
      return returnBuilder();
    }

    /**
     * Add a topping to the Pizza.
     *
     * @param name    name of the topping
     * @param portion portion size of the topping
     * @return this builder
     */
    public CheesePizzaBuilder addTopping(ToppingName name, ToppingPortion portion) {
      this.toppings.put(name, portion);
      return returnBuilder();
    }

    /**
     * Put cheese only on the right half of the pizza.
     *
     * @return this builder.
     */
    public CheesePizzaBuilder rightHalfCheese() {
      this.toppings.put(ToppingName.Cheese, ToppingPortion.RightHalf);
      return returnBuilder();
    }

    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalStateException("Size must be specified");
      }
      return new CheesePizza(size, crust != null ? crust : Crust.Classic, toppings);
    }

    @Override
    protected CheesePizzaBuilder returnBuilder() {
      return this;
    }
  }
}
