package Assignment1;

import java.util.Iterator;

/**
 * The <code>ScenicRoad</code> class is a container for Vehicles.
 * Key difference between <code>ScenicRoad</code> and <code>Highway</code> is that
 * <code>ScenicRoad</code> has a lower bandwidth. When number of Vehicles >= bandwidth
 * in a single direction, it will cause all Vehicles to slow down to 5 mph.
 *
 * @see Assignment1.Highway
 * @author Shuwan Huang
 */
public class ScenicRoad {

    private final int bandwidth;
    private final Highway hw;

    /**
     * Constructor that creates a new <code>ScenicRoad</code>.
     * Sets the bandwidth of <code>ScenicRoad</code> and creates a <code>Highway</code> object to contain Vehicles.
     *
     * @param bandwidth the max number of Vehicles in a single direction
     */
    public ScenicRoad(int bandwidth) {
        this.bandwidth = bandwidth;
        hw = new Highway();
    }

    /**
     * @return an Iterator over the Vehicles objects contained in this container.
     */
    public Iterator<Vehicle> iterator() {
        return hw.iterator();
    }

    /**
     * Adds a vehicle on the <code>ScenicRoad</code>. This method returns <tt>true</tt>
     * if a Vehicle was successfully added, i.e. vehicle is not already on the <code>ScenicRoad</code>.
     * (You are allowed to put a Vehicle on a <code>ScenicRoad</code> only once. Hence, this method returns
     * <tt>false</tt>, if a Vehicle is already on the <code>ScenicRoad</code>). Slows down all Vehicles to
     * 5mph if number of Vehicles in a single direction >= bandwidth.
     *
     * @param v Vehicle to be added.
     * @requires v != null.
     * @return true if vehicle was successfully added on the scenic road,
     * i.e. vehicle is not already on the scenic road. Returns false, if vehicle is
     * already on the scenic road.
     * @throws IllegalArgumentException if v == null.
     */
    public boolean add(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("v is null.");
        }
        if (!hw.add(v)) return false;
        switch (v.getDirection()) {
            case 1:
                if (hw.numberVehiclesEastbound() >= bandwidth) {
                    slowdownEastVehicles(v);
                }
                return true;
            case 2:
                if (hw.numberVehiclesWestbound() >= bandwidth) {
                    slowdownWestVehicles(v);
                }
                return true;
            default: return false;
        }
    }

    // slows down all eastbound Vehicles to 5mph.
    private void slowdownEastVehicles(Vehicle v) {
        if (hw.numberVehiclesEastbound() == bandwidth) {
            for (Vehicle w : hw) {
                if (w.getDirection() == 1) w.setVelocity(5);
            }
        } else {
            v.setVelocity(5);
        }
    }

    // slows down all westbound Vehicles to 5mph.
    private void slowdownWestVehicles(Vehicle v) {
        if (hw.numberVehiclesWestbound() == bandwidth) {
            for (Vehicle w : hw) {
                if (w.getDirection() == 2) w.setVelocity(5);
            }
        } else {
            v.setVelocity(5);
        }
    }

    /**
     * Removes a vehicle from the scenic road. This method returns
     * <tt>true</tt> if vehicle was successfully removed from the
     * scenic road, i.e. vehicle is actually on the scenic road. You cannot
     * remove a Vehicle if it is not already on the scenic road, and so ths
     * method will return <tt>false</tt>, otherwise.
     * @param v Vehicle to be removed.
     * @requires v != null.
     * @return true if vehicle was successfully removed from the ScenicRoad,
     * i.e. vehicle is actually on the scenic road. Returns false, if vehicle is not
     * on the scenic road.
     * @throws IllegalArgumentException if v == null.
     */
    public boolean remove(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("v is null.");
        }
        return hw.remove(v);
    }

    /**
     * Each Vehicle has a velocity. This method returns the velocity of the
     * slowest vehicle in the Eastbound direction.
     * @return the velocity of the slowest eastbound vehicle; returns -1 if no eastbound vehicle.
     */
    public double getVelocityEastbound() {
        return hw.getVelocityEastbound();
    }

    /**
     * Each Vehicle has a velocity. This method returns the velocity of the
     * slowest vehicle in the Westbound direction.
     * @return the velocity of the slowest westbound vehicle; returns -1 if no westbound vehicle.
     */
    public double getVelocityWestbound() {
        return hw.getVelocityWestbound();
    }

    /**
     * Returns the number of Vehicles headed Eastbound.
     * @return the number of Vehicles headed Eastbound on the scenic road.
     */
    public int numberVehiclesEastbound() {
        return hw.numberVehiclesEastbound();
    }

    /**
     * Returns the number of Vehicles headed Westbound.
     * @return the number of Vehicles headed Westbound on the scenic road.
     */
    public int numberVehiclesWestbound() {
        return hw.numberVehiclesWestbound();
    }

    /**
     * This method returns <tt>true</tt> if a specific Vehicle is on the ScenicRoad.
     * It will return <tt>false</tt> otherwise.
     * @param v Vehicle to be checked if its on the ScenicRoad.
     * @requires v != null.
     * @return true if this vehicle is on the ScenicRoad. Returns false, otherwise.
     * @throws IllegalArgumentException if v == null.
     */
    public boolean contains(Vehicle v) {
        if (v == null) {
            throw new IllegalArgumentException("v is null.");
        }
        return hw.contains(v);
    }
}
