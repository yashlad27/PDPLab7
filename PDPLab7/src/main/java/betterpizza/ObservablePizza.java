package betterpizza;

import pizza.ToppingName;
import pizza.ToppingPortion;

/**
 * This interface represents the operations that observe a pizza without modifying it.
 */
public interface ObservablePizza {

  /**
   * Get the cost of this pizza.
   *
   * @return the cost of this pizza.
   */
  double cost();

  /**
   * Determines if the specified topping is on this pizza and if so, return its portion.
   *
   * @param name name of the topping.
   * @return the portion of topping on this pizza, or null if given topping is not on this pizza.
   */
  ToppingPortion hasTopping(ToppingName name);
}
