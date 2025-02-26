package betterbst;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import bst.BST;

/**
 * Main class which implements Binary search tree data structure.
 * It maintains a root node and delegates most operations to the node hierarchy.
 * The class also provides recursive implementations of tree traversal operations.
 */
public class BSTImpl<T extends Comparable<T>> implements BST<T> {
  private BSTNode<T> root;

  /**
   * Constructs a new empty binary search tree.
   */
  public BSTImpl() {
    root = new BSTEmptyNode<T>(); // no tree
  }

  /**
   * Inserts new data into the tree.
   * If data is present then no changes are to be made.
   *
   * @param data the data to be added
   */
  @Override
  public void insert(T data) {
    root = root.insert(data);
  }

  /**
   * Checks if the specified data is present in the tree.
   *
   * @param data the data to be searched
   * @return true if the data is present, false otherwise.
   */
  @Override
  public boolean present(T data) {
    return root.contains(data);
  }

  /**
   * Finds the minimum element in the tree.
   * The min element is the leftmost element in the tree.
   *
   * @return the minimum element in the tree.
   */
  @Override
  public T minimum() {
    return root.minimum();
  }

  /**
   * Finds the maximum element in the tree.
   * The max element is the rightmost element in the tree.
   *
   * @return the maximum element in the tree.
   */
  @Override
  public T maximum() {
    return root.maximum();
  }

  /**
   * Returns a string representation of the tree.
   * The string contains all elements in ascending order, enclosed in square brackets.
   *
   * @return a string representation of the tree.
   */
  @Override
  public String toString() {
    return "[" + root.toString() + "]";
  }

  /**
   * Performs a recursive preorder traversal of the tree.
   * In preorder traversal, a node is processed first, followed by its left subtree
   * and then its right subtree.
   *
   * @param consumer the operation to perform on each element.
   */
  @Override
  public void preorder(Consumer<T> consumer) {
    root.preorder(consumer);
  }

  /**
   * Performs a recursive post order traversal of the tree.
   * In Postorder traversal, the left subtree is processed first, followed by the
   * right and then the node itself.
   *
   * @param consumer the operation to perform on each element.
   */
  @Override
  public void postorder(Consumer<T> consumer) {
    root.postorder(consumer);
  }

  /**
   * Performs a recursive inorder traversal of the tree.
   * In inorder traversal, the left subtree is processed first, followed by the node itself,
   * and then it's right subtree. This results in processing elements in ascending order.
   *
   * @param consumer the operation to perform on each element.
   */
  @Override
  public void inorder(Consumer<T> consumer) {
    root.inorder(consumer);
  }

  /**
   * Checks if the tree is balanced.
   * A tree is balanced if the heights of the left and right subtrees of every node
   * differ by atmost 1, and both subtrees are also balanced.
   *
   * @return true is the tree is balanced, false otherwise.
   */
  @Override
  public boolean isBalanced() {
    return root.isBalanced();
  }

  /**
   * Performs an iterative preorder traversal of the tree using a simple stack approach.
   *
   * @param consumer the operation to perform on each element.
   */
  public void preorderIterative(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return;
    }

    Stack<BSTNode<T>> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      BSTNode<T> current = stack.pop();
      if (current instanceof BSTEmptyNode) {
        continue;
      }

      if (current instanceof BSTElementNode) {
        BSTElementNode<T> elementNode = (BSTElementNode<T>) current;
        consumer.accept(elementNode.getData());

        List<BSTNode<T>> children = elementNode.getChildren();
        if (children.size() == 2) {
          stack.push(children.get(1));
          stack.push(children.get(0));
        }
      }
    }
  }

  /**
   * Enum of node operations for the command pattern approach.
   */
  private enum NodeOperation {
    TRAVERSE,
    PROCESS
  }

  /**
   * Helper class to store node and its operation.
   */
  private class NodeCommand {
    BSTNode<T> node;
    NodeOperation operation;

    NodeCommand(BSTNode<T> node, NodeOperation operation) {
      this.node = node;
      this.operation = operation;
    }
  }

  /**
   * Performs more complex preorder traversal in iterative manner using command pattern.
   * This way allows for more complex traversal logic.
   *
   * @param consumer the operation to perform on each element.
   */
  public void preorderIterative2(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return;
    }

    Stack<NodeCommand> stack = new Stack<>();
    stack.push(new NodeCommand(root, NodeOperation.TRAVERSE));

    while (!stack.isEmpty()) {
      NodeCommand command = stack.pop();

      if (command.node instanceof BSTEmptyNode) {
        continue;
      }

      if (command.operation == NodeOperation.PROCESS) {
        if (command.node instanceof BSTElementNode) {
          BSTElementNode<T> elementNode = (BSTElementNode<T>) command.node;
          consumer.accept(elementNode.getData());
        }
      } else if (command.operation == NodeOperation.TRAVERSE) {
        // For preorder: push right, left, and then process (in reverse order due to LIFO stack)
        if (command.node instanceof BSTElementNode) {
          BSTElementNode<T> elementNode = (BSTElementNode<T>) command.node;
          List<BSTNode<T>> children = elementNode.getChildren();

          if (children.size() == 2) {
            stack.push(new NodeCommand(children.get(1), NodeOperation.TRAVERSE)); //right
            stack.push(new NodeCommand(children.get(0), NodeOperation.TRAVERSE)); //left
            stack.push(new NodeCommand(command.node, NodeOperation.PROCESS)); //node
          }
        }
      }
    }
  }

  /**
   * Performs an iterative inorder traversal using command pattern.
   * In inorder traversal, the left subtree is processed first, followed by the node,
   * and then it's right subtree.
   *
   * @param consumer the operation to perform on each element.
   */
  public void inorderIterative(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return;
    }

    Stack<NodeCommand> stack = new Stack<>();
    stack.push(new NodeCommand(root, NodeOperation.TRAVERSE));

    while (!stack.isEmpty()) {
      NodeCommand command = stack.pop();
      if (command.node instanceof BSTEmptyNode) {
        continue;
      }

      if (command.operation == NodeOperation.PROCESS) {
        if (command.node instanceof BSTElementNode) {
          BSTElementNode<T> elementNode = (BSTElementNode<T>) command.node;
          consumer.accept(elementNode.getData());
        }
      } else if (command.operation == NodeOperation.TRAVERSE) {
        // For inorder: push right, process, then left (in reverse order due to LIFO stack)
        if (command.node instanceof BSTElementNode) {
          BSTElementNode<T> elementNode = (BSTElementNode<T>) command.node;
          List<BSTNode<T>> children = elementNode.getChildren();

          if (children.size() == 2) {
            stack.push(new NodeCommand(children.get(1), NodeOperation.TRAVERSE)); //right
            stack.push(new NodeCommand(command.node, NodeOperation.PROCESS));     //node
            stack.push(new NodeCommand(children.get(0), NodeOperation.TRAVERSE)); //left
          }
        }
      }
    }
  }

  /**
   * Performs an iterative post order traversal using command pattern.
   * In post order traversal the left subtree is processed first,
   * followed by the right subtree and then node itself.
   *
   * @param consumer the operation to perform on each element.
   */
  public void postorderIterative(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return;
    }

    Stack<NodeCommand> stack = new Stack<>();
    stack.push(new NodeCommand(root, NodeOperation.TRAVERSE));

    while (!stack.isEmpty()) {
      NodeCommand command = stack.pop();
      if (command.node instanceof BSTEmptyNode) {
        continue;
      }

      if (command.operation == NodeOperation.PROCESS) {
        if (command.node instanceof BSTElementNode) {
          BSTElementNode<T> elementNode = (BSTElementNode<T>) command.node;
          consumer.accept(elementNode.getData());
        }
      } else if (command.operation == NodeOperation.TRAVERSE) {
        // For postorder: push process, right, then left (in reverse order due to LIFO stack)
        if (command.node instanceof BSTElementNode) {
          BSTElementNode<T> elementNode = (BSTElementNode<T>) command.node;
          List<BSTNode<T>> children = elementNode.getChildren();

          if (children.size() == 2) {
            stack.push(new NodeCommand(command.node, NodeOperation.PROCESS)); //node
            stack.push(new NodeCommand(children.get(1), NodeOperation.TRAVERSE)); //right
            stack.push(new NodeCommand(children.get(0), NodeOperation.TRAVERSE)); //left
          }
        }
      }
    }
  }
}