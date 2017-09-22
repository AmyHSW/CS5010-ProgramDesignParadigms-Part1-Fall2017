package edu.neu.ccs.cs5010;

/**
 * Created by zontakm on 9/12/2017.
 * This interface captures Stack ADT
 */
public interface IStack {
    IStack push(int elt);
    IStack pop();
    int top();
    boolean isEmpty();
}
