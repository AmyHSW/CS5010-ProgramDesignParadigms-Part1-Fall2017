/**
 * This is part of Assignment 1: Environment Setup and Review of Java for PDP, Fall 2017.
 */
package edu.neu.ccs.cs5010;

import java.util.Comparator;

/**
 * Vehicle is a simple object that has a velocity and a direction.
 * Vehicle will always has positive velocity, and it's direction can't be changed
 * once the Vehicle is created.
 *
 * @author Shuwan Huang
 */
public class Vehicle {

    /**
     * A comparator that compares Vehicles by their velocity.
     */
    public static final Comparator<Vehicle> BY_VELOCITY = new ByVelocity();

    private double velocity;
    private final int direction;

    /**
     * Constructor that creates a new vehicle object with the specified velocity and direction.
     * @param velocity Velocity of the new object. If the given velocity is negative, set it to be positive and
     *                 change the direction of Vehicle.
     * @param direction Direction of the new object, where 1 represents eastbound direction, and 2 westbound direction.
     * @throws IllegalArgumentException if direction is neither 1 or 2.
     */
    public Vehicle(double velocity, int direction) {
        if (direction < 1 || direction > 2) {
            throw new IllegalArgumentException("direction " + direction + " is neither 1 nor 2.");
        }

        this.velocity = Math.abs(velocity);

        // change the direction to the other direction if the given velocity is negative.
        if (velocity >= 0) { this.direction = direction; }
        else { this.direction = (direction == 1) ? 2 : 1; }
    }

    /**
     * @throws IllegalArgumentException if constructor is called when no inputs given.
     */
    public Vehicle() {
        throw new IllegalArgumentException("velocity and direction of this Vehicle are not given.");
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
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the velocity of Vehicle to the given number.
     * @param velocity to be set.
     * @throws IllegalArgumentException if velocity given is negative; because it's
     * assumed that direction of Vehicle won't change once Vehicle is created.
     */
    public void setVelocity(double velocity) {
        if (velocity < 0) {
            throw new IllegalArgumentException("velocity " + velocity + " is negative.");
        }
        this.velocity = velocity;
    }

    // provides a Comparator<Vehicle> to compare Vehicles by velocity.
    private static class ByVelocity implements Comparator<Vehicle> {
        public int compare(Vehicle a, Vehicle b) {
            double cmp = a.velocity - b.velocity;
            if (cmp < 0) return -1;
            else if (cmp > 0) return 1;
            else return 0;
        }
    }

}

