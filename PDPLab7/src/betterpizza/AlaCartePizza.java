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
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza.
   */
  @Override
  public double cost() {
    double cost = 0.0;
    for(Map.Entry<ToppingName, ToppingPortion> item : this.toppings.entrySet()) {
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
    return this.toppings.getDefault(name, null);
  }
}
