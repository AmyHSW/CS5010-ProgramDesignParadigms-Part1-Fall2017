package edu.neu.ccs.cs5010.Assignment2.section2;

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
            "C",
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
        myPQ = new MyPriorityQueue<>();
        for (int i = 0; i < WORDS.length; i++) myPQ.insert(WORDS[i]);
    }

    /**
     * Tests that MyPriorityQueue returns correct head element.
     *
     * @throws Exception
     */
    @Test
    public void front() throws Exception {
        assertEquals("C", myPQ.front());
    }

    /**
     * Tests that MyPriorityQueue inserts the element to the correct position.
     *
     * @throws Exception
     */
    @Test
    public void insert() throws Exception {
        myPQ.insert("A");
        assertEquals("A", myPQ.front());
    }

    /**
     * Tests that MyPriorityQueue removes and returns the head element.
     *
     * @throws Exception
     */
    @Test
    public void remove() throws Exception {
        assertEquals("C", myPQ.remove());
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
        assertTrue("The 1st in the order should be C but it is not", list.get(0).equals("C"));
        assertTrue("The 2nd in the order should be Go but it is not",list.get(1).equals("Go"));
        assertTrue("The 3rd in the order should be Java but it is not",list.get(2).equals("Java"));
        assertTrue("The 4th in the order should be Python but it is not",list.get(3).equals("Python"));
        assertTrue("The 5th in the order should be JavaScript but it is not",list.get(4).equals("JavaScript"));
    }

    /**
     * Tests that MyPriorityQueue returns a list with contents in backward traversal order.
     * @throws Exception
     */
    @Test
    public void testReverseTraversal() throws Exception {
        List<String> list = myPQ.testReverseTraversal();
        assertTrue("The 5th in the order should be C but it is not",list.get(4).equals("C"));
        assertTrue("The 4th in the order should be Go but it is not",list.get(3).equals("Go"));
        assertTrue("The 3rd in the order should be Java but it is not",list.get(2).equals("Java"));
        assertTrue("The 2nd in the order should be Python but it is not",list.get(1).equals("Python"));
        assertTrue("The 1st in the order should be JavaScript but it is not",list.get(0).equals("JavaScript"));
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