package betterpizza;

import java.util.HashMap;
import java.util.Map;

import pizza.Crust;
import pizza.Size;
import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This class represents a alacarte pizza ( x number of ingredients ).
 */
public class AlaCartePizza implements ObservablePizza {

  protected Crust crust;
  protected Size size;
  protected Map<ToppingName, ToppingPortion> toppings;

  /**
   * Create a pizza given its crust type, size and toppings.
   */
  public AlaCartePizza(Size size, Crust crush) {
    this.crust = crust;
    this.size = size;
    this.toppings = new HashMap<ToppingName, ToppingPortion>();
  }

  /**
   * Protected constructor that takes in the size, crust and map of toppings.
   *
   * @param size     size of this pizza
   * @param crust    crust of this pizza
   * @param toppings toppings on this pizza
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  protected AlaCartePizza(Size size, Crust crust, Map<ToppingName, ToppingPortion> toppings) {
    if (size == null || crust == null || toppings == null) {
      throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.size = size;
    this.crust = crust;
    this.toppings = new HashMap<>(toppings);
  }

  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza.
   */
  @Override
  public double cost() {
    double cost = 0.0;
    for (Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
      cost += item.getKey().getCost() * item.getValue().getCostMultiplier();
    }
    return cost + this.size.getBaseCost();
  }

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion.
   *
   * @param name name of the topping.
   * @return the portion of topping on this pizza, or null if given topping is not on this pizza.
   */
  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return this.toppings.getOrDefault(name, null);
  }
}
