package edu.neu.ccs.cs5010.assignment2.section1;

/**
 * Created by zontakm on 9/12/2017.
 * This interface captures Queue ADT
 */
public interface IQueue {
    IQueue enqueue(int elt);
    IQueue dequeue();
    int front();
    boolean isEmpty();
}
