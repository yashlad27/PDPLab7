package betterbst;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import bst.NothingThereException;

/**
 * This node represents an empty node in the binary search tree.
 * Empty nodes serve as the leaves of the tree and provide base case implementations.
 *
 * @param <T> the type of data that would be stored in the tree must be comparable.
 */
public class BSTEmptyNode<T extends Comparable<T>> extends BSTNode<T> {

  /**
   * Inserts data into an empty node, creating a new element node.
   * This is the best case for the recursive insert operation.
   *
   * @param data the data to insert.
   * @return a new element node containing the data and two empty child nodes.
   */
  @Override
  public BSTNode<T> insert(T data) {
    return new BSTElementNode<>(data, new BSTEmptyNode<>(), new BSTEmptyNode<>());
  }

  /**
   * Attempts to find the minimum element in an empty tree.
   * Since an empty tree has no elements, this method will always throw an exception.
   *
   * @return never returns normally.
   * @throws NothingThereException always as an empty tree has no minimum element.
   */
  @Override
  public T minimum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  /**
   * Attempts to find the maximum element in an empty tree.
   *
   * @return never returns normally.
   * @throws NothingThereException always as an empty tree has no maximum element.
   */
  @Override
  public T maximum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  /**
   * Checks if the specified data is present in an empty tree.
   *
   * @param data data to be searched.
   * @return false, as empty tree never contains any data.
   */
  @Override
  public boolean contains(T data) {
    return false;
  }

  /**
   * Returns a string representation of an empty tree.
   * An empty tree is represented by an empty string.
   *
   * @return an empty string.
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Performs a per order traversal of an empty tree.
   * Since an empty tree has no elements, this method does nothing.
   *
   * @param consumer the operation to perform on each element.
   */
  @Override
  public void preorder(Consumer<T> consumer) {
    // Empty node, nothing to do
  }

  /**
   * Performs inorder traversal of an empty tree.
   * Since an empty tree has no elements, this method does nothing.
   *
   * @param consumer the operation to perform on each element.
   */
  @Override
  public void inorder(Consumer<T> consumer) {
    // Empty node, nothing to do
  }

  /**
   * Performs postorder traversal of an empty tree.
   * Since an empty tree has no elements, this method does nothing.
   *
   * @param consumer the operation to perform on each element.
   */
  @Override
  public void postorder(Consumer<T> consumer) {
    // Empty node, nothing to do
  }

  /**
   * Returns the height of an empty tree.
   * By definition, the height of an empty tree is 0.
   *
   * @return 0, the height of an empty tree.
   */
  @Override
  public int height() {
    return 0; // Height of an empty node is 0
  }

  /**
   * Checks if an empty tree is balanced.
   * An empty tree is always considered balanced.
   *
   * @return true as emtpy tree is always considered balanced.
   */
  @Override
  public boolean isBalanced() {
    return true; // An empty tree is always balanced
  }

  /**
   * Returns the children of an empty node.
   * Since, an empty node has no children this method returns an empty list.
   *
   * @return
   */
  @Override
  public List<BSTNode<T>> getChildren() {
    return new ArrayList<>(); // Empty node has no children
  }
}