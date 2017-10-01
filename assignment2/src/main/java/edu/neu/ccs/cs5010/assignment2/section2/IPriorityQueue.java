package edu.neu.ccs.cs5010.assignment2.section2;

import java.util.List;

/**
 * The <code>IPriorityQueue</code> interface represents the common properties of a priority queue.
 *
 * @param <E> the generic type of object stored in priority queue.
 * @author Shuwan Huang
 */
public interface IPriorityQueue<E> {

    /**
     * Inserts the object in the priority queue.
     *
     * @param elt the object to be inserted.
     */
    void insert(E elt);

    /**
     * Removes and returns the object from the front.
     *
     * @return the head object on the priority queue.
     */
    E remove();

    /**
     * Gets the object at the front without changing the priority queue.
     *
     * @return the head object on the priority queue.
     */
    E front();

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty and false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns a list that contains contents in the priority queue in the order from highest priority to lowest.
     * @return a list that contains contents in the priority queue in the order from highest priority to lowest.
     */
    List<E> testForwardTraversal();

    /**
     * Returns a list that contains contents in the priority queue in the order from lowest priority to highest.
     * @return a list that contains contents in the priority queue in the order from lowest priority to highest.
     */
    List<E> testReverseTraversal();
}
