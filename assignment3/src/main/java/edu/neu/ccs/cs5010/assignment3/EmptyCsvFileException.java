package edu.neu.ccs.cs5010.assignment3;

/**
 * Thrown to indicate that the csv file is empty.
 *
 * @author Shuwan Huang
 */
public class EmptyCsvFileException extends RuntimeException {

    /**
     * Constructs an EmptyCsvFileException with the specified detail message.
     * @param msg the detail message
     */
    public EmptyCsvFileException(String msg) {
        super(msg);
    }
}
