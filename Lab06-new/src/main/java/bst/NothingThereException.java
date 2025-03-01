package bst;

/**
 * Exception thrown when an operation encounters an empty or non-existent element,
 * in a binary search tree (BST).
 */
public class NothingThereException extends RuntimeException {

  /**
   * Constructs a new {@code NothingThereException} with the specified detail message.
   *
   * @param message the detail message explaining the reason for the exception
   */
  public NothingThereException(String message) {
    super(message);
  }
}