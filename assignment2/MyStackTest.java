package edu.neu.ccs.cs5010.assignment2.section1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * The <code>MyStackTest</code> is a test class for <code>MyStack</code>.
 *
 * @author Shuwan Huang
 */
public class MyStackTest {

  private IStack stack = null;

  /**
   * Declares an empty MyStack before each test.
   */
  @Before
  public void setUp() {
    stack = new MyStack();
  }

  /**
   * Tests that isEmpty returns true for an empty stack.
   */
  @Test
  public void testIsEmptyOnEmpty() {
    assertTrue("isEmpty does not return true for empty stack",
        stack.isEmpty());
  }

  /**
   * Tests that isEmpty returns false for a non-empty stack.
   */
  @Test
  public void testIsEmptyOnNonEmpty() {
    stack = stack.push(1);
    assertFalse("isEmpty does not return false for non-empty stack",
        stack.isEmpty());
  }

  /**
   * Tests pushing one integer to the stack.
   */
  @Test
  public void testOnePush() {
    IStack stack2 = stack.push(1);
    assertTrue(stack.isEmpty());
    assertTrue(!stack2.isEmpty());
  }

  /**
   * Tests that pop throws a NoSuchElementException on empty stack.
   * Ignored because not required.
   */
  @Ignore
  @Test(expected = NoSuchElementException.class)
  public void testPopOnEmpty() {
    stack = stack.pop();
  }

  /**
   * Tests poping one integer from the stack.
   */
  @Test
  public void testOnePop() {
    IStack stack2 = stack.push(1);
    IStack stack3 = stack2.pop();
    assertTrue(!stack2.isEmpty());
    assertTrue(stack3.isEmpty());
  }

  /**
   * Tests that top return the most recently-added element.
   */
  @Test
  public void testTop() {
    stack = stack.push(1).push(2).push(3).push(4).push(5);
    assertTrue("top does not return the most recently-added element",
        5 == stack.top());
  }

  /**
   * Pushes 10 element to stack and tests if stack stores the elements
   * in LIFO order by poping elements from stack one by one.
   */
  @Test
  public void test10Inputs() {
    testByComparingToLinkedList(10);
  }

  /**
   * Pushes 50 element to stack and tests if stack stores the elements
   * in LIFO order by poping elements from stack one by one.
   */
  @Test
  public void test50Inputs() {
    testByComparingToLinkedList(50);
  }

  /**
   * Pushes 100 element to stack and tests if stack stores the elements
   * in LIFO order by poping elements from stack one by one.
   */
  @Test
  public void test100Inputs() {
    testByComparingToLinkedList(100);
  }

  // a helper method that compares the behavior of MyStack and LinkedList to test if stack follows
  // LIFO order when pushing and poping elements.
  private void testByComparingToLinkedList(int n) {
    LinkedList<Integer> list = new LinkedList<Integer>();
    Random rd = new Random();
    for (int i = 0; i < n; i++) {
      int r = rd.nextInt(50);
      stack = stack.push(r);
      list.add(r);
    }
    if (n > 0) {
      assertFalse("stack is empty but it should not be", stack.isEmpty());
    }
    while (!stack.isEmpty()) {
      assertTrue("LinkedList is empty but MyStack is not.", !list.isEmpty());
      assertTrue("elements in MyStack and LinkedList do not match.",
          stack.top() == list.removeLast());
      stack = stack.pop();
    }
  }
}