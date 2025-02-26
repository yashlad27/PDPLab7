package betterbst;

import java.util.List;
import java.util.function.Consumer;

import bst.NothingThereException;

/**
 * This represents the operations on all nodes of a binary search tree.
 */
public abstract class BSTNode<T extends Comparable<T>> {
  /**
   * Inserts new data into the tree rooted at this node, and return the resulting tree.
   *
   * @param data the data to insert.
   * @return the updated tree after insertion.
   */
  abstract BSTNode<T> insert(T data);

  /**
   * Determine and return the minimum element in the tree rooted at this node.
   *
   * @return the minimum element.
   * @throws NothingThereException if the tree does not have any data.
   */
  abstract T minimum() throws NothingThereException;

  /**
   * Determine and return the maximum element in the tree rooted at this node.
   *
   * @return the maximum element.
   * @throws NothingThereException if the tree does not have any data.
   */
  abstract T maximum() throws NothingThereException;

  /**
   * Search to see if the specific data is present in the tree rooted at this node.
   *
   * @param data data to be searched.
   * @return true if data is present in the tree, false otherwise.
   */
  abstract boolean contains(T data);

  /**
   * Perform a pre-order traversal of the tree rooted at this node.
   * Pre-order: Process node, then left subtree, then right subtree.
   *
   * @param consumer the operation to perform on each element.
   */
  abstract void preorder(Consumer<T> consumer);

  /**
   * Perform an in-order traversal of the tree rooted at this node.
   * In-order: Process left subtree, then node, then right subtree.
   *
   * @param consumer the operation to perform on each element.
   */
  abstract void inorder(Consumer<T> consumer);

  /**
   * Perform a post-order traversal of the tree rooted at this node.
   * Post-order: Process left subtree, then right subtree, then node.
   *
   * @param consumer the operation to perform on each element.
   */
  abstract void postorder(Consumer<T> consumer);

  /**
   * Calculate the height of the tree rooted at this node.
   *
   * @return the height of the tree.
   */
  abstract int height();

  /**
   * Check if the tree rooted at this node is balanced.
   * A tree is balanced if the difference in height between the left and right subtrees is atmost 1,
   * and both subtrees are also balanced.
   *
   * @return true if the tree is balanced, false otherwise.
   */
  abstract boolean isBalanced();

  /**
   * Returns a list of the children of this node.
   *
   * @return a list containing the left and right children.
   */
  abstract List<BSTNode<T>> getChildren();
}