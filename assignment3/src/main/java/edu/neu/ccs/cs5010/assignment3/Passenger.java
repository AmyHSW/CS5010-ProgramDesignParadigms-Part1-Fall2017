package edu.neu.ccs.cs5010.assignment3;

import java.util.Map;

/**
 * The <code>Passenger</code> class is a container of passenger information.
 * The underlined data structure is a map, where the keys are the attributes and the values are
 * the corresponding passenger information.
 *
 * @author Shuwan Huang
 */
public class Passenger {

    private final Map<String, String> info;

    /**
     * Initializes the info map with the parameter.
     * @param info a map that contains passenger information.
     */
    public Passenger(Map<String, String> info) {
        this.info = info;
    }

    /**
     * Returns true if this Passenger has corresponding information of the query attribute; false otherwise.
     * @param attribute the query attribute
     * @return true if this passenger has corresponding information of the query attribute; false otherwise.
     */
    public boolean hasAttribute(String attribute) {
        return info.containsKey(attribute);
    }

    /**
     * Returns the corresponding passenger information of the query attribute; null if this passenger does not
     * have the corresponding information.
     * @param attribute the query attribute
     * @return the corresponding passenger information of the query attribute; null if this passenger does not
     * have the corresponding information.
     */
    public String getAttribute(String attribute) {
        if (!hasAttribute(attribute)) {
            return null;
        }
        return info.get(attribute);
    }

    /**
     * Compares this passenger to the specified passenger.
     * @param o the other passenger
     * @return true if this passenger equals the other; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;

        return info != null ? info.equals(passenger.info) : passenger.info == null;
    }

    /**
     * Returns an integer hash code for this passenger.
     * @return an integer hash code for this passenger.
     */
    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }

}
