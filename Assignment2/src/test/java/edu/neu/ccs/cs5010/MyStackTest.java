package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyStackTest {

    private IStack stack = null;

    /**
     * Sets up an IStack with integers before each test case;
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        stack = (new MyStack()).push(1).push(2).push(3).push(4);
    }

    /**
     * Tests that top method returns the most recently-added integer.
     * @throws Exception
     */
    @Test
    public void top() throws Exception {
        assertEquals(4, stack.top());
    }

    /**
     * Tests that push adds an integer to the stack and returns that stack.
     * @throws Exception
     */
    @Test
    public void push() throws Exception {
        assertEquals(5, stack.push(5).top());
    }

    /**
     * Tests that pop removes the most recently-added integer and returns that stack.
     * @throws Exception
     */
    @Test
    public void pop() throws Exception {
        assertEquals(3, stack.pop().top());
    }

    /**
     * Tests that isEmpty returns true for an empty stack and false otherwise.
     * @throws Exception
     */
    @Test
    public void isEmpty() throws Exception {
        assertFalse(stack.isEmpty());
    }

}