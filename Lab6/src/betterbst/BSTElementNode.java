package betterbst;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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

  /**
   * Get the data stored in this node
   *
   * @return the data
   */
  public T getData() {
    return this.data;
  }

  @Override
  public BSTNode<T> insert(T data) {
    if (data.compareTo(this.data) < 0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data) > 0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  @Override
  public T minimum() {
    T minimum;

    try {
      minimum = this.left.minimum();
    } catch (NothingThereException e) {
      minimum = this.data;
    }
    return minimum;
  }

  @Override
  public T maximum() {
    T maximum;

    try {
      maximum = this.right.maximum();
    } catch (NothingThereException e) {
      maximum = this.data;
    }

    return maximum;
  }

  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult == 0) {
      return true;
    } else if (compareResult < 0) {
      return this.left.contains(data);
    } else {
      return this.right.contains(data);
    }
  }

  @Override
  public String toString() {
    String left, right, middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (left.length() > 0) left = left + " ";
    if (right.length() > 0) right = " " + right;
    return left + middle + right;
  }

  @Override
  public void preorder(Consumer<T> consumer) {
    // Process current node first
    consumer.accept(this.data);
    // Then process left subtree
    this.left.preorder(consumer);
    // Finally process right subtree
    this.right.preorder(consumer);
  }

  @Override
  public void inorder(Consumer<T> consumer) {
    // Process left subtree first
    this.left.inorder(consumer);
    // Then process current node
    consumer.accept(this.data);
    // Finally process right subtree
    this.right.inorder(consumer);
  }

  @Override
  public void postorder(Consumer<T> consumer) {
    // Process left subtree first
    this.left.postorder(consumer);
    // Then process right subtree
    this.right.postorder(consumer);
    // Finally process current node
    consumer.accept(this.data);
  }

  @Override
  public int height() {
    int leftHeight = left.height();
    int rightHeight = right.height();

    // Return the height of the taller subtree + 1 (for this node)
    return Math.max(leftHeight, rightHeight) + 1;
  }

  @Override
  public boolean isBalanced() {
    // First check if the subtrees are balanced
    if (!left.isBalanced() || !right.isBalanced()) {
      return false;
    }

    // Then check if the height difference is at most 1
    int leftHeight = left.height();
    int rightHeight = right.height();

    return Math.abs(leftHeight - rightHeight) <= 1;
  }

  @Override
  public List<BSTNode<T>> getChildren() {
    List<BSTNode<T>> children = new ArrayList<>();
    children.add(left);
    children.add(right);
    return children;
  }
}