/**
 * This is part of Assignment 1: Environment Setup and Review of Java for PDP, Fall 2017.
 */
package Assignment1;
/**
 * Vehicle is a simple object that has a velocity and a direction.
 */
// You may not make Ball implement the Comparable interface.
public class Vehicle {

   private double velocity;
   private int direction;
   
    /**
     * Constructor that creates a new vehicle object with the specified velocity and direction.
     * @param velocity Velocity of the new object.
     * @param direction Direction of the new object, where 1 represents eastbound direction, and 2 westbound direction.
     * @throws IllegalArgumentException() if direction is neither 1 or 2.
     */
    public Vehicle(double velocity, int direction) {
        if (velocity < 0) {
            throw new IllegalArgumentException("velocity " + velocity + " is not positive.");
        }
        if (direction < 1 || direction > 2) {
            throw new IllegalArgumentException("direction " + direction + " is neither 1 nor 2.");
        }
        this.velocity = velocity;
        this.direction = direction;
    }

    /**
     * Constructor that throws an exception when no inputs is given.
     *
     * @throws IllegalArgumentException()
     */
    public Vehicle() {
        throw new IllegalArgumentException("No inputs is given.");
    }

    /**
     * Returns the velocity of the Vehicle.
     * @return the velocity of the Vehicle.
     */
    public double getVelocity() {
        return velocity;
    }

   /**
     * Returns the direction of the Vehicle.
     * @return the direction of the Vehicle.
     */
    public double getDirection() {
        return direction;
    }
}

