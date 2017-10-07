package edu.neu.ccs.cs5010.assignment3;

import java.util.Map;

/**
 * The <code>Member</code> class is a container of member information.
 * The underlined data structure is a map, where the keys are the attributes and the values are
 * the corresponding member information.
 *
 * @author Shuwan Huang
 */
public class Member implements IMember {

    private final Map<String, String> info;

    /**
     * Initializes the info map with the parameter.
     * @param info a map that contains member information.
     */
    public Member(Map<String, String> info) {
        this.info = info;
    }

    /**
     * Returns true if this member has corresponding information of the query attribute; false otherwise.
     * @param attribute the query attribute
     * @return true if this member has corresponding information of the query attribute; false otherwise.
     */
    @Override
    public boolean hasAttribute(String attribute) {
        return info.containsKey(attribute);
    }

    /**
     * Returns the corresponding member information of the query attribute; null if this member does not
     * have the corresponding information.
     * @param attribute the query attribute
     * @return the corresponding member information of the query attribute; null if this member does not
     * have the corresponding information.
     */
    @Override
    public String getAttribute(String attribute) {
        if (!hasAttribute(attribute)) {
            return null;
        }
        return info.get(attribute);
    }

    /**
     * Compares this member to the specified member.
     * @param o the other member
     * @return true if this member equals the other; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return info != null ? info.equals(member.info) : member.info == null;
    }

    /**
     * Returns an integer hash code for this member.
     * @return an integer hash code for this member.
     */
    @Override
    public int hashCode() {
        return info != null ? info.hashCode() : 0;
    }

}
