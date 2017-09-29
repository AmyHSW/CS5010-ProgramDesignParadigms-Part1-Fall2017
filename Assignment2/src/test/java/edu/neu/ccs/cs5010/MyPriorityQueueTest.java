package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * The <code>MyPriorityQueueTest</code> is a test class for <code>MyPriorityQueue</code>.
 *
 * @author Shuwan Huang
 */
public class MyPriorityQueueTest {

    private IPriorityQueue<String> myPQ = null;
    private static final String[] WORDS = {
            "Java",
            "C++",
            "Python",
            "Go",
            "JavaScript"
    };

    /**
     * Sets up the priority queue before each case test.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        myPQ = new MyPriorityQueue<String>();
        for (int i = 0; i < 5; i++) myPQ.insert(WORDS[i]);
    }

    /**
     * Tests that MyPriorityQueue returns correct head element.
     *
     * @throws Exception
     */
    @Test
    public void front() throws Exception {
        assertEquals("C++", myPQ.front());
    }

    /**
     * Tests that MyPriorityQueue inserts the element to the correct position.
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        myPQ.insert("C");
        assertEquals("C", myPQ.front());
    }

    /**
     * Tests that MyPriorityQueue removes and returns the head element.
     *
     * @throws Exception
     */
    @Test
    public void remove() throws Exception {
        assertEquals("C++", myPQ.remove());
        assertEquals("Go", myPQ.remove());
    }

    /**
     * Tests that MyPriorityQueue correctly checks if the queue is empty.
     *
     * @throws Exception
     */
    @Test
    public void isEmpty() throws Exception {
        assertFalse(myPQ.isEmpty());
    }

    /**
     * Tests that MyPriorityQueue returns a list with contents in forward traversal order.
     * @throws Exception
     */
    @Test
    public void testForwardTraversal() throws Exception {
        List<String> list = myPQ.testForwardTraversal();
        assertEquals("C++", list.get(0));
    }

    /**
     * Tests that MyPriorityQueue returns a list with contents in backward traversal order.
     * @throws Exception
     */
    @Test
    public void testReverseTraversal() throws Exception {
        List<String> list = myPQ.testReverseTraversal();
        assertEquals("C++", list.get(4));
    }

    /**
     * Tests that front method throws NoSuchElementException if the queue is empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void frontThrowsNoSuchElementException() {
        new MyPriorityQueue().front();
    }

    /**
     * Tests that remove method throws NoSuchElementException if the queue is empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void removeThrowsNoSuchElementException() {
        new MyPriorityQueue().remove();
    }
}