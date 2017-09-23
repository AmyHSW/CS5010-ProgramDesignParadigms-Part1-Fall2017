package edu.neu.ccs.cs5010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The <code>MyPriorityQueue</code> class represents a priority queue of generic keys.
 * The implementation is based on a priority heap, where the elements are ordered by their
 * natural order, or by a Comparator provided at construction time.
 *
 * <code>MyPriorityQueue</code> supports inserting elements to the queue with O(lgN) time, removing the head
 * element from the priority queue with O(lgN) time, and returning the head element with O(1) time.
 * It also supports checking if the priority queue is empty, and can return list that contains
 * all elements in queue either in forward direction or backward direction.
 *
 * @param <E> the generic type of element on this priority queue
 *
 * @author Shuwan Huang
 */
public class MyPriorityQueue<E extends Comparable<E>> {

    private E[] pq;
    private int n; // number of elements on priority queue
    private Comparator<E> comparator;

    // a helper constructor that initializes the pq array with given initCapacity
    @SuppressWarnings("unchecked")
    private MyPriorityQueue(int initCapacity) {
        pq = (E[]) new Comparable[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MyPriorityQueue() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given comparator.
     *
     * @param comparator the order in which to compare the elements.
     */
    public MyPriorityQueue(Comparator<E> comparator) {
        this(1);
        this.comparator = comparator;
    }

    /**
     * Inserts the object in the priority queue.
     *
     * @param e the object to be inserted.
     */
    public void insert(E e) {
        if (n == pq.length - 1) resize(pq.length * 2);
        pq[++n] = e;
        promote(n);
    }

    /**
     * Removes and returns the object from the front.
     *
     * @return the head object on the priority queue.
     * @throws NoSuchElementException if the priority queue is empty.
     */
    public E remove() {
        if (n == 0) throw new NoSuchElementException("Priority Queue underflow.");
        E front = pq[1];
        swap(1, n--);
        demote(1);
        pq[n + 1] = null;
        if (n > 0 && n == (pq.length - 1) / 4) resize(pq.length / 2);
        return front;
    }

    /**
     * Returns the object at the front without changing the priority queue.
     *
     * @return the head object on the priority queue.
     * @throws NoSuchElementException if the priority queue is empty.
     */
    public E front() {
        if (n == 0) throw new NoSuchElementException("Priority Queue underflow.");
        return pq[1];
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the priority queue is empty and false otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * @return a list that contains contents in the priority queue in forward traversal order.
     */
    public List<E> testForwardTraversal() {
        List<E> list = new ArrayList<E>();
        list.addAll(Arrays.asList(pq).subList(1, n + 1));
        return list;
    }

    /**
     * @return a list that contains contents in the priority queue in backward traversal order.
     */
    public List<E> testReverseTraversal() {
        List<E> list = new ArrayList<E>();
        for (int i = n; i >= 1; i--) list.add(pq[i]);
        return list;
    }

    @SuppressWarnings("unchecked")
    private void resize(int sz) {
        E[] copy = (E[])new Comparable[sz];
        System.arraycopy(pq, 1, copy, 1, n);
        pq = copy;
    }

    // a helper method to restore the heap invariant.
    private void promote(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k / 2, k);
            k /= 2;
        }
    }

    // a helper method to restore the heap invariant.
    private void demote(int k) {
        while (k < n && less(k, k * 2)) {
            swap(k, k * 2);
            k *= 2;
        }
    }

    private void swap(int i, int j) {
        E tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    // a helper method for compares.
    private boolean less(int i, int j) {
        if (comparator == null) {
            return pq[i].compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

}
