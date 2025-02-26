package betterbst;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import bst.NothingThereException;

/**
 * This class represents a non empty node in a binary search tree.
 * It contains data and pointers to its left and right children.
 * All relevant operations are implemented as methods that may mutate the tree.
 *
 * @param <T> the type of data stored in the tree must be comparable.
 */
public class BSTElementNode<T extends Comparable<T>> extends BSTNode<T> {
  private BSTNode<T> left;
  private BSTNode<T> right;
  private T data;

  /**
   * Constructs a new element node with the specified data and children.
   *
   * @param data  data to store in this node.
   * @param left  left child of this node.
   * @param right right child of this node.
   */
  public BSTElementNode(T data, BSTNode<T> left, BSTNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /**
   * Returns the data stored in this node.
   *
   * @return the data stored in this node.
   */
  public T getData() {
    return this.data;
  }

  /**
   * Inserts new data into the tree rooted at this node.
   * if the data is already present, no changes are made.
   *
   * @param data the data to insert.
   * @return this node after possible modifications.
   */
  @Override
  public BSTNode<T> insert(T data) {
    if (data.compareTo(this.data) < 0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data) > 0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  /**
   * Finds the minimum element in the tree rooted at this node.
   * The minimum element is the leftmost element in th tree.
   *
   * @return the minimum element is the tree.
   * @throws NothingThereException if the tree does not contain any data.
   */
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

  /**
   * Finds the maximum element in the tree rooted at this node.
   * The maximum element is the rightmost element in the tree.
   *
   * @return the maximum element in the tree.
   * @throws NothingThereException if the tree does not contain any date.
   */
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

  /**
   * Checks if the specified data is present in the tree rooted at this node.
   *
   * @param data data to be searched.
   * @return true if the data is present, false otherwise.
   */
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

  /**
   * Returns a string representation of the tree rooted at this node.
   * The string contains all elements in ascending order, separated by spaces.
   *
   * @return a string representation of the tree.
   */
  @Override
  public String toString() {
    String left;
    String right;
    String middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (!left.isEmpty()) {
      left = left + " ";
    }
    if (!right.isEmpty()) {
      right = " " + right;
    }
    return left + middle + right;
  }

  /**
   * Performs a pre-order traversal of the tree rooted at this node.
   * In this traversal, the node is processed first, followed by its left subtree,
   * and then right subtree.
   *
   * @param consumer the operation to perform on each element
   */
  @Override
  public void preorder(Consumer<T> consumer) {
    consumer.accept(this.data);
    this.left.preorder(consumer);
    this.right.preorder(consumer);
  }

  /**
   * Performs an inorder traversal of the tree rooted at this node.
   * In inorder traversal, left subtree is processed first, followed by the node itself,
   * and then its right subtree. This results in processing of elements in ascending order.
   *
   * @param consumer the operation to perform on each element
   */
  @Override
  public void inorder(Consumer<T> consumer) {
    this.left.inorder(consumer);
    consumer.accept(this.data);
    this.right.inorder(consumer);
  }

  /**
   * Performs a post order traversal of the tree rooted at this node.
   * In post order traversal, the left subtree is processed first, followed by the rightsubtree.
   * and then node itself.
   *
   * @param consumer the operation to perform on each element
   */
  @Override
  public void postorder(Consumer<T> consumer) {
    this.left.postorder(consumer);
    this.right.postorder(consumer);
    consumer.accept(this.data);
  }

  /**
   * Calculates the height of the tree rooted at this node.
   * The height is the length of the longest path from node to a leaf.
   *
   * @return the height of the tree.
   */
  @Override
  public int height() {
    int leftHeight = left.height();
    int rightHeight = right.height();

    return Math.max(leftHeight, rightHeight) + 1;
  }

  /**
   * Checks if the tree rooted at this node is balanced.
   * A tree is balanced if the heights of the left and right subtrees differ by at most 1,
   * and both subtrees are also balanced.
   *
   * @return true if the tree is balanced, false otherwise.
   */
  @Override
  public boolean isBalanced() {
    if (!left.isBalanced() || !right.isBalanced()) {
      return false;
    }

    int leftHeight = left.height();
    int rightHeight = right.height();

    return Math.abs(leftHeight - rightHeight) <= 1;
  }

  /**
   * Returns a list containing the left and right children of this node.
   * This method is used to support iterative tree traversal algos.
   *
   * @return a list containing the left and right children.
   */
  @Override
  public List<BSTNode<T>> getChildren() {
    List<BSTNode<T>> children = new ArrayList<>();
    children.add(left);
    children.add(right);
    return children;
  }
}