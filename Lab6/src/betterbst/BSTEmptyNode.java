package betterbst;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This node represents an empty node in the binary search tree (i.e. the
 * leaves)
 */
public class BSTEmptyNode<T extends Comparable<T>> extends BSTNode<T> {
  @Override
  public BSTNode<T> insert(T data) {
    return new BSTElementNode<>(data, new BSTEmptyNode<>(), new BSTEmptyNode<>());
  }

  @Override
  public T minimum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  @Override
  public T maximum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  @Override
  public boolean contains(T data) {
    return false;
  }

  @Override
  public String toString() {
    return "";
  }

  @Override
  public void preorder(Consumer<T> consumer) {
    // Empty node, nothing to do
  }

  @Override
  public void inorder(Consumer<T> consumer) {
    // Empty node, nothing to do
  }

  @Override
  public void postorder(Consumer<T> consumer) {
    // Empty node, nothing to do
  }

  @Override
  public int height() {
    return 0; // Height of an empty node is 0
  }

  @Override
  public boolean isBalanced() {
    return true; // An empty tree is always balanced
  }

  @Override
  public List<BSTNode<T>> getChildren() {
    return new ArrayList<>(); // Empty node has no children
  }
}