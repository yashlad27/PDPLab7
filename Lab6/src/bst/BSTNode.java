package bst;

/**
 * This represents the operations on all nodes of a binary search tree
 */
public abstract class BSTNode<T extends Comparable<T>> {
  /**
   * Inserts new data into the tree rooted at this node, and return the
   * resulting tree
   * @param data
   * @return
   */
  abstract BSTNode<T> insert(T data);

  /**
   * Determine and return the minimum element in the tree rooted at this node
   * @return
   * @throws NothingThereException if the tree does not have any data
   */
  abstract T minimum() throws NothingThereException;

  /**
   * Determine and return the maximum element in the tree rooted at this node
   * @return
   * @throws NothingThereException if the tree does not have any data
   */
  abstract T maximum() throws NothingThereException;

  /**
   * Search to see if the specific data is present in the tree rooted at this
   * node
   * @param data data to be searched
   * @return true if data is present in the tree, false otherwise
   */
  abstract boolean contains(T data);




}
