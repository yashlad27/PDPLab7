import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

import betterbst.BSTImpl;
import bst.BST;
import bst.NothingThereException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Junit test class to check implementations of preorder, postorder and inorder traversals
 * in a binary search tree.
 */
public class BetterBSTTest {

  private BST<Integer> tree;

  @Before
  public void setup() {
    tree = new BSTImpl<>();
  }

  @Test
  public void testInsertions() {
    Set<Integer> expected = new TreeSet<>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }

    StringBuilder sb = new StringBuilder();
    for (Integer e : expected) {
      sb.append(e).append(" ");
    }
    String output = sb.toString();
    output = "[" + output.substring(0, output.length() - 1) + "]";

    assertEquals(output, tree.toString());
  }

  @Test
  public void testMinMax() {
    TreeSet<Integer> expected = new TreeSet<>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }

    assertEquals(expected.first(), tree.minimum());
    assertEquals(expected.last(), tree.maximum());
  }

  @Test(expected = NothingThereException.class)
  public void testMinWhenEmpty() {
    new BSTImpl<Integer>().minimum();
  }

  @Test(expected = NothingThereException.class)
  public void testMaxWhenEmpty() {
    new BSTImpl<Integer>().maximum();
  }

  @Test
  public void testContains() {
    List<Integer> expected = new ArrayList<>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }

    for (int i = -1000; i <= 1000; i++) {
      assertEquals(expected.contains(i), tree.present(i));
    }
  }

  @Test
  public void testPreorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);
    List<Integer> actualList = new ArrayList<>();

    tree.preorder(actualList::add);
    assertEquals(expectedList, actualList);
  }

  @Test
  public void testInorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(1, 2, 5, 6, 7, 8, 10, 15, 19, 20);
    List<Integer> actualList = new ArrayList<>();

    tree.inorder(actualList::add);
    assertEquals(expectedList, actualList);
  }

  @Test
  public void testPostorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);
    List<Integer> actualList = new ArrayList<>();

    tree.postorder(actualList::add);
    assertEquals(expectedList, actualList);
  }

  @Test
  public void testTraversalsWithSum() {
    buildTestTree();

    SumConsumer preorderSum = new SumConsumer();
    tree.preorder(preorderSum);
    assertEquals(93, preorderSum.getSum());

    SumConsumer inorderSum = new SumConsumer();
    tree.inorder(inorderSum);
    assertEquals(93, inorderSum.getSum());

    SumConsumer postorderSum = new SumConsumer();
    tree.postorder(postorderSum);
    assertEquals(93, postorderSum.getSum());
  }

  @Test
  public void testBalancedTree() {
    tree.insert(10);
    tree.insert(5);
    tree.insert(15);
    tree.insert(3);
    tree.insert(7);
    tree.insert(12);
    tree.insert(17);

    assertTrue(((BSTImpl<Integer>) tree).isBalanced());
  }

  @Test
  public void testUnbalancedTree() {
    tree.insert(10);
    tree.insert(15);
    tree.insert(20);
    tree.insert(25);
    tree.insert(30);

    assertFalse(((BSTImpl<Integer>) tree).isBalanced());
  }

  @Test
  public void testIterativePreorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).preorderIterative(actualList::add);
    assertEquals(expectedList, actualList);
  }

  @Test
  public void testIterativePreorder2() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).preorderIterative2(actualList::add);
    assertEquals(expectedList, actualList);
  }

  @Test
  public void testIterativeInorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(1, 2, 5, 6, 7, 8, 10, 15, 19, 20);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).inorderIterative(actualList::add);
    assertEquals(expectedList, actualList);
  }

  @Test
  public void testIterativePostorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).postorderIterative(actualList::add);
    assertEquals(expectedList, actualList);
  }


  private void buildTestTree() {
    tree = new BSTImpl<>();
    tree.insert(10);
    tree.insert(15);
    tree.insert(5);
    tree.insert(1);
    tree.insert(2);
    tree.insert(7);
    tree.insert(6);
    tree.insert(8);
    tree.insert(20);
    tree.insert(19);
  }

  /**
   * Consumer implementation that sums values.
   */
  private static class SumConsumer implements Consumer<Integer> {
    private int sum = 0;

    @Override
    public void accept(Integer value) {
      sum += value;
    }

    public int getSum() {
      return sum;
    }
  }

  @Test
  public void testEmptyTreeIsBalanced() {
    BST<Integer> emptyTree = new BSTImpl<>();
    assertTrue(((BSTImpl<Integer>) emptyTree).isBalanced());
  }

  @Test
  public void testSingleNodeIsBalanced() {
    BST<Integer> singleNodeTree = new BSTImpl<>();
    singleNodeTree.insert(5);
    assertTrue(((BSTImpl<Integer>) singleNodeTree).isBalanced());
  }

  @Test
  public void testBoundaryBalanceCases() {
    // Create a tree where the difference between left and right subtree heights is exactly 1
    BST<Integer> boundaryTree = new BSTImpl<>();
    boundaryTree.insert(10);
    boundaryTree.insert(5);
    boundaryTree.insert(15);
    boundaryTree.insert(3);
    // This should still be balanced
    assertTrue(((BSTImpl<Integer>) boundaryTree).isBalanced());
  }

  @Test
  public void testTraversalOnEmptyTree() {
    BST<Integer> emptyTree = new BSTImpl<>();
    List<Integer> result = new ArrayList<>();

    // These should work without exceptions and result in an empty list
    emptyTree.preorder(result::add);
    emptyTree.inorder(result::add);
    emptyTree.postorder(result::add);

    assertTrue(result.isEmpty());
  }

  @Test
  public void testNullConsumerHandling() {
    // This tests if your code handles null consumers appropriately
    // You may want to decide whether to throw an exception or silently ignore
    tree.insert(5);
    try {
      tree.preorder(null);
      // If you choose to silently ignore null consumers, add an assertion here
    } catch (NullPointerException e) {
      // If you choose to throw an exception, this catch block confirms expected behavior
      assertTrue(true);
    }
  }
}