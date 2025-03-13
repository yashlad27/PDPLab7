package betterpizza;

import pizza.Crust;
import pizza.Size;

/**
 * Abstract class for building pizzas.
 */
public abstract class PizzaBuilder {
  public abstract PizzaBuilder crust(Crust crust);

  public abstract PizzaBuilder size(Size size);

  public abstract ObservablePizza build();
}
