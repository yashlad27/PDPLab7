package bst;

import java.util.function.Consumer;

import betterbst.NothingThereException;

/**
 * This interface represents all the operations that a binary search tree
 * should support. It can store any type of data that implements the
 * Comparable interface
 */
public interface BST<T extends Comparable<T>> {
  /**
   * Add data to the binary search tree. This is ignored if the data item is
   * already present
   * @param data the data to be added
   */
  void insert(T data);

  /**
   * Find if this data is present in the binary search tree
   * @param data the data to be searched
   * @return true if the data is present, false otherwise
   */
  boolean present(T data);

  /**
   * Determine and return the minimum data in the tree as defined by its
   * ordering
   * @return the minimum data if it exists, null otherwise
   * @throws NothingThereException if the tree does not have any data
   */
  T minimum() throws NothingThereException;

  /**
   * Determine and return the maximum data in the tree as defined by its
   * ordering
   * @return the maximum data if it exists, null otherwise
   * @throws NothingThereException if the tree does not have any data
   */
  T maximum() throws NothingThereException;

  /**
   * Returns a string that present all the data in the tree, sorted in
   * ascending order. The string is formatted as [d1 d2 ... dn]
   * @return
   */
  String toString();

  void preorder(Consumer<T> consumer);
  void postorder(Consumer<T> consumer);

  void inorder(Consumer<T> consumer);

  boolean isBalanced();

//  BST<T> balance();





}
