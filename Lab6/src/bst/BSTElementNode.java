package bst;

/**
 * This class represents a data-containing node of the binary search tree
 * It mutates on all relevant operations
 */
public class BSTElementNode<T extends Comparable<T>> extends BSTNode<T> {
  private BSTNode<T> left;
  private BSTNode<T> right;
  private T data;

  public BSTElementNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  @Override
  public BSTNode insert(T data) {
    if (data.compareTo(this.data)<0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data)>0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  @Override
  public T minimum() {
    T minimum;

    try {
      minimum = this.left.minimum();
    }
    catch (NothingThereException e) {
      minimum = this.data;
    }
    return minimum;
  }

  @Override
  public T maximum() {
    T maximum;

    try {
      maximum = this.right.maximum();
    }
    catch (NothingThereException e) {
      maximum = this.data;
    }

    return maximum;
  }

  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult==0)  {
      return true;
    }
    else if (compareResult<0) {
      return this.left.contains(data);
    }
    else {
      return this.right.contains(data);
    }
  }

  @Override
  public String toString() {
    String left,right,middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (left.length()>0) left = left + " ";
    if (right.length()>0) right = " " + right;
    return left + middle + right;
  }
}
