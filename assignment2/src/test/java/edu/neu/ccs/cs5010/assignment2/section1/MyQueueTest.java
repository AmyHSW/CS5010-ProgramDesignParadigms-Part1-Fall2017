package edu.neu.ccs.cs5010.assignment2.section1;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * The <code>MyQueueTest</code> is a test class for <code>MyQueue</code>.
 *
 * @author Shuwan Huang
 */
public class MyQueueTest {

  private IQueue queue = null;

  /**
   * Declares a new MyQueue before each test case.
   */
  @Before
  public void setUp() {
    queue = new MyQueue();
  }

  /**
   * Tests that isEmpty returns true for empty queue.
   */
  @Test
  public void testIsEmptyOnEmpty() {
    assertTrue("isEmpty does not return true for empty queue",
        queue.isEmpty());
  }

  /**
   * Tests that isEmpty return false for non-empty queue.
   */
  @Test
  public void testIsEmptyOnNonEmpty() {
    queue = queue.enqueue(1);
    assertFalse("isEmpty does not return false for non-empty queue",
        queue.isEmpty());
  }

  /**
   * Tests that enqueue adds a new integer to the queue.
   */
  @Test
  public void testOneEnqueue() {
    IQueue queue2 = queue.enqueue(1);
    assertTrue(queue.isEmpty());
  }

  /**
   * Tests that dequeue throws a NoSuchElementException on an empty queue.
   */
  @Ignore // ignored because not required.
  @Test(expected = NoSuchElementException.class)
  public void testDequeueOnEmpty() {
    queue = queue.dequeue();
  }

  /**
   * Tests that dequeue removes the least recently-added element and returns that new queue.
   */
  @Test
  public void testOneDequeue() {
    queue = queue.enqueue(1).dequeue();
  }

  /**
   * Tests that front returns the least recently-added element.
   */
  @Test
  public void testFront() {
    queue = queue.enqueue(1).enqueue(2).enqueue(3).enqueue(4);
    assertEquals("front does not return the least recently-added element", 1, queue.front());
  }

  /**
   * Enqueues 10 element to queue and tests if queue stores the elements
   * in FIFO order by dequeueing elements one by one.
   */
  @Test
  public void test10Inputs() {
    testByComparingToLinkedList(10);
  }

  /**
   * Enqueues 50 element to queue and tests if queue stores the elements
   * in FIFO order by dequeueing elements one by one.
   */
  @Test
  public void test50Inputs() {
    testByComparingToLinkedList(50);
  }

  /**
   * Enqueues 100 element to queue and tests if queue stores the elements
   * in FIFO order by dequeueing elements one by one.
   */
  @Test
  public void test100Inputs() {
    testByComparingToLinkedList(100);
  }

  // a helper method that compares the behavior of MyQueue and LinkedList to test if queue follows
  // FIFO order when enqueueing and dequeueing elements.
  private void testByComparingToLinkedList(int n) {
    LinkedList<Integer> list = new LinkedList<Integer>();
    Random rd = new Random();
    for (int i = 0; i < n; i++) {
      int r = rd.nextInt(50);
      queue = queue.enqueue(r);
      list.add(r);
    }
    if (n > 0) {
      assertFalse("queue is empty but it should not be", queue.isEmpty());
    }
    while (!queue.isEmpty()) {
      assertTrue("LinkedList is empty but MyQueue is not.", !list.isEmpty());
      assertTrue("Elements in MyQueue and LinkedList do not match.",
          queue.front() == list.remove());
      queue = queue.dequeue();
    }
  }
}
