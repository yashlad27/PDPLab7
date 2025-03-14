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

  /**
   * Builder class for VeggiePizza.
   */
  public static class VeggiePizzaBuilder extends PizzaBuilder<VeggiePizzaBuilder> {
    private Size size;
    private Crust crust;
    private Map<ToppingName, ToppingPortion> toppings;

    /**
     * Create a new VeggiePizzaBuilder with default toppings for a veggie pizza.
     */
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
      return returnBuilder();
    }

    @Override
    public VeggiePizzaBuilder size(Size size) {
      this.size = size;
      return returnBuilder();
    }

    /**
     * Remove cheese from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noCheese() {
      this.toppings.remove(ToppingName.Cheese);
      return returnBuilder();
    }

    /**
     * Remove sauce from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noSauce() {
      this.toppings.remove(ToppingName.Sauce);
      return returnBuilder();
    }

    /**
     * Remove black olives from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noBlackOlive() {
      this.toppings.remove(ToppingName.BlackOlive);
      return returnBuilder();
    }

    /**
     * Remove green peppers from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noGreenPepper() {
      this.toppings.remove(ToppingName.GreenPepper);
      return returnBuilder();
    }

    /**
     * Remove onions from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noOnion() {
      this.toppings.remove(ToppingName.Onion);
      return returnBuilder();
    }

    /**
     * Remove jalapenos from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noJalapeno() {
      this.toppings.remove(ToppingName.Jalapeno);
      return returnBuilder();
    }

    /**
     * Remove tomatoes from the pizza.
     *
     * @return this builder
     */
    public VeggiePizzaBuilder noTomato() {
      this.toppings.remove(ToppingName.Tomato);
      return returnBuilder();
    }

    /**
     * Add a topping to the Pizza.
     *
     * @param name    name of the topping
     * @param portion portion size of the topping
     * @return this builder
     */
    public VeggiePizzaBuilder addTopping(ToppingName name, ToppingPortion portion) {
      this.toppings.put(name, portion);
      return returnBuilder();
    }

    @Override
    public ObservablePizza build() {
      if (size == null) {
        throw new IllegalStateException("Size must be specified");
      }
      return new VeggiePizza(size, crust != null ? crust : Crust.Classic, toppings);
    }

    @Override
    protected VeggiePizzaBuilder returnBuilder() {
      return this;
    }
  }
}
