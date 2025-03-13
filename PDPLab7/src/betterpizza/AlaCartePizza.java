package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

public class AlaCartePizza implements ObservablePizza{
  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza.
   */
  @Override
  public double cost() {
    return 0;
  }

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion.
   *
   * @param name name of the topping.
   * @return the portion of topping on this pizza, or null if given topping is not on this pizza.
   */
  @Override
  public ToppingPortion hasTopping(ToppingName name) {
    return null;
  }
}
