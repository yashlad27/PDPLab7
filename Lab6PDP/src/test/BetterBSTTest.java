import org.junit.Assert;
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

    Assert.assertEquals(output, tree.toString());
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

    Assert.assertEquals(expected.first(), tree.minimum());
    Assert.assertEquals(expected.last(), tree.maximum());
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
      Assert.assertEquals(expected.contains(i), tree.present(i));
    }
  }

  @Test
  public void testPreorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);
    List<Integer> actualList = new ArrayList<>();

    tree.preorder(actualList::add);
    Assert.assertEquals(expectedList, actualList);
  }

  @Test
  public void testInorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(1, 2, 5, 6, 7, 8, 10, 15, 19, 20);
    List<Integer> actualList = new ArrayList<>();

    tree.inorder(actualList::add);
    Assert.assertEquals(expectedList, actualList);
  }

  @Test
  public void testPostorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);
    List<Integer> actualList = new ArrayList<>();

    tree.postorder(actualList::add);
    Assert.assertEquals(expectedList, actualList);
  }

  @Test
  public void testTraversalsWithSum() {
    buildTestTree();

    SumConsumer preorderSum = new SumConsumer();
    tree.preorder(preorderSum);
    Assert.assertEquals(93, preorderSum.getSum());

    SumConsumer inorderSum = new SumConsumer();
    tree.inorder(inorderSum);
    Assert.assertEquals(93, inorderSum.getSum());

    SumConsumer postorderSum = new SumConsumer();
    tree.postorder(postorderSum);
    Assert.assertEquals(93, postorderSum.getSum());
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

    Assert.assertTrue(((BSTImpl<Integer>) tree).isBalanced());
  }

  @Test
  public void testUnbalancedTree() {
    tree.insert(10);
    tree.insert(15);
    tree.insert(20);
    tree.insert(25);
    tree.insert(30);

    Assert.assertFalse(((BSTImpl<Integer>) tree).isBalanced());
  }

  @Test
  public void testIterativePreorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).preorderIterative(actualList::add);
    Assert.assertEquals(expectedList, actualList);
  }

  @Test
  public void testIterativePreorder2() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).preorderIterative2(actualList::add);
    Assert.assertEquals(expectedList, actualList);
  }

  @Test
  public void testIterativeInorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(1, 2, 5, 6, 7, 8, 10, 15, 19, 20);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).inorderIterative(actualList::add);
    Assert.assertEquals(expectedList, actualList);
  }

  @Test
  public void testIterativePostorder() {
    buildTestTree();
    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);
    List<Integer> actualList = new ArrayList<>();

    ((BSTImpl<Integer>) tree).postorderIterative(actualList::add);
    Assert.assertEquals(expectedList, actualList);
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
}