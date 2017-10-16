package edu.neu.ccs.cs5010.assignment2.section2;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
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
     */
    @Before
    public void setUp() throws Exception {
        myPQ = new MyPriorityQueue<>();
        for (String WORD : WORDS) myPQ.insert(WORD);
    }

    /**
     * Tests that MyPriorityQueue returns correct head element.
     */
    @Test
    public void front() throws Exception {
        assertEquals("C", myPQ.front());
    }

    /**
     * Tests that MyPriorityQueue inserts the element to the correct position.
     */
    @Test
    public void insert() throws Exception {
        myPQ.insert("R");
        assertEquals("C", myPQ.front());
        myPQ.insert("A");
        assertEquals("A", myPQ.front());
    }

    /**
     * Tests that MyPriorityQueue removes and returns the head element.
     */
    @Test
    public void remove() throws Exception {
        assertEquals("C", myPQ.remove());
        assertEquals("Go", myPQ.remove());
    }

    /**
     * Tests that MyPriorityQueue correctly checks if the queue is empty.
     */
    @Test
    public void isEmpty() throws Exception {
        assertFalse(myPQ.isEmpty());
    }

    /**
     * Tests that MyPriorityQueue returns a list with contents in forward traversal order.
     */
    @Test
    public void testForwardTraversal() throws Exception {
        List<String> list = myPQ.testForwardTraversal();
        assertTrue("The 1st in the order should be C but it is not", list.get(0).equals("C"));
        assertTrue("The 2nd in the order should be Go but it is not",list.get(1).equals("Go"));
        assertTrue("The 3rd in the order should be Java but it is not",list.get(2).equals("Java"));
        assertTrue("The 4th in the order should be JavaScript but it is not",list.get(3).equals("JavaScript"));
        assertTrue("The 5th in the order should be Python but it is not",list.get(4).equals("Python"));
    }

    /**
     * Tests that MyPriorityQueue returns a list with contents in backward traversal order.
     */
    @Test
    public void testReverseTraversal() throws Exception {
        List<String> list = myPQ.testReverseTraversal();
        assertTrue("The 5th in the order should be C but it is not",list.get(4).equals("C"));
        assertTrue("The 4th in the order should be Go but it is not",list.get(3).equals("Go"));
        assertTrue("The 3rd in the order should be Java but it is not",list.get(2).equals("Java"));
        assertTrue("The 2nd in the order should be JavaScript but it is not",list.get(1).equals("JavaScript"));
        assertTrue("The 1st in the order should be Python but it is not",list.get(0).equals("Python"));
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

    @Test
    public void testPatientQueue() throws Exception {
        IPriorityQueue<IPatient> patientQueue = new MyPriorityQueue<>();

        IPatient p1 = new Patient(LocalDateTime.now(), new Urgency(8), Duration.ofMinutes(1), 1);
        patientQueue.insert(p1);
        IPatient p2 = new Patient(LocalDateTime.now(), new Urgency(2), Duration.ofMinutes(1), 2);
        patientQueue.insert(p2);
        IPatient p3 = new Patient(LocalDateTime.now(), new Urgency(1), Duration.ofMinutes(1), 3);
        patientQueue.insert(p3);
        IPatient p4 = new Patient(LocalDateTime.now(), new Urgency(5), Duration.ofMinutes(1), 4);
        patientQueue.insert(p4);
        IPatient p5 = new Patient(LocalDateTime.now(), new Urgency(2), Duration.ofMinutes(1), 5);
        patientQueue.insert(p5);

        List<IPatient> patientList = patientQueue.testForwardTraversal();
        assertTrue("p3 should be 1st priority patient", patientList.get(0).equals(p3));
        assertTrue("p2 should be 2nd priority patient", patientList.get(1).equals(p2));
        assertTrue("p5 should be 3rd priority patient", patientList.get(2).equals(p5));
        assertTrue("p4 should be 4th priority patient", patientList.get(3).equals(p4));
        assertTrue("p1 should be 5th priority patient", patientList.get(4).equals(p1));
    }
}