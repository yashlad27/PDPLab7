package betterbst;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import bst.BST;

/**
 * This class represents a binary search tree. It implements the BST
 * interface
 */
public class BSTImpl<T extends Comparable<T>> implements BST<T> {
  private BSTNode<T> root;

  public BSTImpl() {
    root = new BSTEmptyNode<T>(); // no tree
  }

  @Override
  public void insert(T data) {
    root = root.insert(data);
  }

  @Override
  public boolean present(T data) {
    return root.contains(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  public String toString() {
    return "[" + root.toString() + "]";
  }

  @Override
  public void preorder(Consumer<T> consumer) {
    root.preorder(consumer);
  }

  @Override
  public void postorder(Consumer<T> consumer) {
    root.postorder(consumer);
  }

  @Override
  public void inorder(Consumer<T> consumer) {
    root.inorder(consumer);
  }

  @Override
  public boolean isBalanced() {
    return root.isBalanced();
  }

  /**
   * Iterative implementation of preorder traversal.
   * First attempt using simple stack-based approach.
   */
  public void preorderIterative(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return; // Empty tree, nothing to do
    }

    // Use a stack to keep track of nodes
    Stack<BSTNode<T>> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      BSTNode<T> current = stack.pop();

      // Skip empty nodes
      if (current instanceof BSTEmptyNode) {
        continue;
      }

      // Process the data (using type casting as we know it's an element node)
      if (current instanceof BSTElementNode) {
        BSTElementNode<T> elementNode = (BSTElementNode<T>) current;
        consumer.accept(elementNode.getData());

        // Push right child first so left is processed first (LIFO stack)
        List<BSTNode<T>> children = elementNode.getChildren();
        if (children.size() == 2) { // Should always be 2 for a binary tree
          stack.push(children.get(1)); // Right child
          stack.push(children.get(0)); // Left child
        }
      }
    }
  }

  // Different types of node operations for the command pattern
  private enum NodeOperation {
    TRAVERSE, // Visit the node's children
    PROCESS   // Process the node's data
  }

  // Helper class to store a node and its operation
  private class NodeCommand {
    BSTNode<T> node;
    NodeOperation operation;

    NodeCommand(BSTNode<T> node, NodeOperation operation) {
      this.node = node;
      this.operation = operation;
    }
  }

  /**
   * More sophisticated preorder traversal using the command pattern
   */
  public void preorderIterative2(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return; // Empty tree, nothing to do
    }

    Stack<NodeCommand> stack = new Stack<>();
    stack.push(new NodeCommand(root, NodeOperation.TRAVERSE));

    while (!stack.isEmpty()) {
      NodeCommand command = stack.pop();

      // Skip empty nodes
      if (command.node instanceof BSTEmptyNode) {
        continue;
      }

      if (command.operation == NodeOperation.PROCESS) {
        // Process the node
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
            stack.push(new NodeCommand(children.get(1), NodeOperation.TRAVERSE)); // Right
            stack.push(new NodeCommand(children.get(0), NodeOperation.TRAVERSE)); // Left
            stack.push(new NodeCommand(command.node, NodeOperation.PROCESS)); // Process
          }
        }
      }
    }
  }

  /**
   * Iterative implementation of inorder traversal using the command pattern
   */
  public void inorderIterative(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return; // Empty tree, nothing to do
    }

    Stack<NodeCommand> stack = new Stack<>();
    stack.push(new NodeCommand(root, NodeOperation.TRAVERSE));

    while (!stack.isEmpty()) {
      NodeCommand command = stack.pop();

      // Skip empty nodes
      if (command.node instanceof BSTEmptyNode) {
        continue;
      }

      if (command.operation == NodeOperation.PROCESS) {
        // Process the node
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
            stack.push(new NodeCommand(children.get(1), NodeOperation.TRAVERSE)); // Right
            stack.push(new NodeCommand(command.node, NodeOperation.PROCESS));     // Process
            stack.push(new NodeCommand(children.get(0), NodeOperation.TRAVERSE)); // Left
          }
        }
      }
    }
  }

  /**
   * Iterative implementation of postorder traversal using the command pattern
   */
  public void postorderIterative(Consumer<T> consumer) {
    if (root instanceof BSTEmptyNode) {
      return; // Empty tree, nothing to do
    }

    Stack<NodeCommand> stack = new Stack<>();
    stack.push(new NodeCommand(root, NodeOperation.TRAVERSE));

    while (!stack.isEmpty()) {
      NodeCommand command = stack.pop();

      // Skip empty nodes
      if (command.node instanceof BSTEmptyNode) {
        continue;
      }

      if (command.operation == NodeOperation.PROCESS) {
        // Process the node
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
            stack.push(new NodeCommand(command.node, NodeOperation.PROCESS));     // Process
            stack.push(new NodeCommand(children.get(1), NodeOperation.TRAVERSE)); // Right
            stack.push(new NodeCommand(children.get(0), NodeOperation.TRAVERSE)); // Left
          }
        }
      }
    }
  }
}