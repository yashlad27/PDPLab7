package betterpizza;

import pizza.Crust;
import pizza.Size;

/**
 * Abstract class for building pizzas.
 */
public abstract class PizzaBuilder<T extends PizzaBuilder<T>> {

  /**
   * Set the crust for the pizza being built.
   *
   * @param crust crust to set
   * @return this builder
   */
  public abstract T crust(Crust crust);

  /**
   * Set the size for the pizza being built.
   *
   * @param size the size to set
   * @return this builder
   */
  public abstract T size(Size size);

  /**
   * Build the pizza with current configuration.
   *
   * @return the built pizza
   * @throws IllegalStateException if the pizza configuration is invalid
   */
  public abstract ObservablePizza build();

  /**
   * Return the builder instance.
   */
  protected abstract T returnBuilder();
}
