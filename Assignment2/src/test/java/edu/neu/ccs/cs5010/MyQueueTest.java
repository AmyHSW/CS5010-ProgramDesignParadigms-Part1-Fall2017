package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyQueueTest {

    private IQueue queue = null;

    /**
     * Sets up an IQueue with integers before each test case.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        queue = (new MyQueue()).enqueue(-1).enqueue(4).enqueue(3).enqueue(9);
    }

    /**
     * Tests that front method returns the least recently-added integer.
     * @throws Exception
     */
    @Test
    public void front() throws Exception {
        assertEquals(-1, queue.front());
    }

    /**
     * Tests that enqueue adds an integer to the queue and returns that queue.
     * @throws Exception
     */
    @Test
    public void enqueue() throws Exception {
        assertEquals(-1, queue.enqueue(7).front());
    }

    /**
     * Tests that dequeue removes the least recently-added integer and returns that queue.
     * @throws Exception
     */
    @Test
    public void dequeue() throws Exception {
        assertEquals(4, queue.dequeue().front());
    }

    /**
     * Tests that isEmpty returns true for an empty queue and false otherwise.
     * @throws Exception
     */
    @Test
    public void isEmpty() throws Exception {
        assertFalse(queue.isEmpty());
    }

}