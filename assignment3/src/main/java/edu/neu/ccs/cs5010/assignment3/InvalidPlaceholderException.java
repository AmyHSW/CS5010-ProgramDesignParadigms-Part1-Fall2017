package edu.neu.ccs.cs5010.assignment3;

/**
 * Thrown to indicate the placeholder information can not be determined.
 *
 * @author Shuwan Huang
 */
public class InvalidPlaceholderException extends RuntimeException {

    /**
     * Constructs an InvalidPlaceholderException with the specified detail message.
     * @param msg the detail message
     */
    public InvalidPlaceholderException(String msg) {
        super(msg);
    }
}
