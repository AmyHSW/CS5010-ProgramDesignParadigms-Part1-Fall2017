package edu.neu.ccs.cs5010.assignment3;

/**
 * The <code>IMember</code> interface represents a member.
 *
 * @author Shuwan Huang
 */
public interface IMember {

    /**
     * Returns true if this member has corresponding information of the query attribute; false otherwise.
     * @param attribute the query attribute
     * @return true if this member has corresponding information of the query attribute; false otherwise.
     */
    boolean hasAttribute(String attribute);

    /**
     * Returns the corresponding member information of the query attribute; null if this member does not
     * have the corresponding information.
     * @param attribute the query attribute
     * @return the corresponding member information of the query attribute; null if this member does not
     * have the corresponding information.
     */
    String getAttribute(String attribute);

}
