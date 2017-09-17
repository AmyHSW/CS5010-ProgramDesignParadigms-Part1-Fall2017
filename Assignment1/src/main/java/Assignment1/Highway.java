/**
 * This is part of Assignment 1: Environment Setup and Review of Java for PDP, Fall 2017
 */
package Assignment1;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Collections;

/**
 * This is a container that can be used to contain Vehicles.
 * A given Vehicle may only appear on a Highway once.
 */
public class Highway implements Iterable<Vehicle> {

    // Contents of the Highway.
    private final Set<Vehicle> contents;
    private final TreeMap<Vehicle, Integer> west;
    private final TreeMap<Vehicle, Integer> east;
    private int nWestVehicle;

    /**
     * Constructor that creates a new Highway.
     */
    public Highway() {
        contents = new LinkedHashSet<Vehicle>();
        west = new TreeMap<Vehicle, Integer>(Vehicle.BY_VELOCITY);
        east = new TreeMap<Vehicle, Integer>(Vehicle.BY_VELOCITY);
        nWestVehicle = 0;
    }

   /**
     * Implements the Iterable interface for this container.
     * @return an Iterator over the Vehicle objects contained
     * in this container.
     */
    public Iterator<Vehicle> iterator() {
           // If we just returned the iterator of "contents", a client
        // could call the remove() method on the iterator and modify
        // it behind our backs.  Instead, we wrap contents in an
        // "unmodifiable set"; calling remove() on this iterator
        // throws an exception.  This is an example of avoiding
        // "representation exposure."  
        return Collections.unmodifiableSet(contents).iterator();
    }

    /**
     * Adds a vehicle on the Highway. This method returns <tt>true</tt>
     * if a Vehicle was successfully added on the Highway, i.e. vehicle is
     * not already on the Highway. (You are allowed to put
     * a Vehicle on a Highway only once. Hence, this method returns
     * <tt>false</tt>, if a Vehicle is already on the Highway).
     * @param v Vehicle to be added.
     * @requires v != null.
     * @return true if vehicle was successfully added on the highway,
     * i.e. vehicle is not already on the highway. Returns false, if vehicle is
     * already on the highway.
     * @throws IllegalArgumentException if input is null.
     */
    public boolean add(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("v is null.");
        }
        if (!contents.add(v)) return false;
        switch (v.getDirection()) {
            case 1:
                if (east.containsKey(v)) {
                    east.put(v, east.get(v) + 1);
                } else {
                    east.put(v, 1);
                }
                return true;
            case 2:
                if (west.containsKey(v)) {
                    west.put(v, west.get(v) + 1);
                } else {
                    west.put(v, 1);
                }
                nWestVehicle++;
                return true;
            default: return false;
        }
    }

    /**
     * Removes a vehicle from the highway. This method returns
     * <tt>true</tt> if vehicle was successfully removed from the
     * highway, i.e. vehicle is actually on the highway. You cannot
     * remove a Vehicle if it is not already on the Highway, and so ths
     * method will return <tt>false</tt>, otherwise.
     * @param v Vehicle to be removed.
     * @requires v != null.
     * @return true if vehicle was successfully removed from the Highway,
     * i.e. vehicle is actually on the highway. Returns false, if vehicle is not
     * on the highway.
     * @throws IllegalArgumentException if input is null.
     */
    public boolean remove(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("v is null.");
        }
        if (!contents.remove(v)) return false;
        switch (v.getDirection()) {
            case 1:
                if (east.get(v) > 1) {
                    east.put(v, east.get(v) - 1);
                } else {
                    east.remove(v);
                }
                return true;
            case 2:
                if (west.get(v) > 1) {
                    west.put(v, west.get(v) - 1);
                } else {
                    west.remove(v);
                }
                nWestVehicle--;
                return true;
            default: return false;
        }
    }

    /**
     * Each Vehicle has a velocity. This method returns the velocity of the slowest vehicle in the Eastbound direction of the highway.
     * @return the velocity of the slowest eastbound vehicle; returns -1 if no eastbound vehicle.
     */
    public double getVelocityEastbound() {
        if (east.isEmpty()) return -1;
        return east.firstKey().getVelocity();
    }
    
    /**
     * Each Vehicle has a velocity. This method returns the velocity of the slowest vehicle in the Westbound direction of the highway.
     * @return the velocity of the slowest westbound vehicle; returns -1 if no westbound vehicle.
     */
    public double getVelocityWestbound() {
        if (west.isEmpty()) return -1;
        return west.firstKey().getVelocity();
    }

    /**
     * Returns the number of Vehicles headed Eastbound.
     * @return the number of Vehicles headed Eastbound on the highway
     */
    public int numberVehiclesEastbound() {
        return contents.size() - nWestVehicle;
    }
    
     /**
     * Returns the number of Vehicles headed Westbound.
     * @return the number of Vehicles headed Westbound on the highway
     */
    public int numberVehiclesWestbound() {
        return nWestVehicle;
    }

    /**
     * This method returns <tt>true</tt> if a specific Vehicle is on the Highway. 
	 * It will return <tt>false</tt> otherwise.
	 * @param v Vehicle to be checked if its on the Highway
     * @requires v != null.
     * @return true if this vehicle is on the Highway. Returns
     * false, otherwise.
     * @throws IllegalArgumentException if input is null.
     */
    public boolean contains(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("v is null.");
        }
        return contents.contains(v);
    }

}