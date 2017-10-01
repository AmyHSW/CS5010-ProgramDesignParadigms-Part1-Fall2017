package edu.neu.ccs.cs5010.assignment2.section2;

/**
 * Thrown to indicate that ERSimulator constructor has been passed a negative or zero numRoom.
 *
 * @author Shuwan Huang
 */
public class InvalidNumRoomException extends RuntimeException {

    /**
     * Constructs an InvalidNumRoomException with the specified detail message.
     * @param s the detail message
     */
    public InvalidNumRoomException(String s) {
        super(s);
    }

}
