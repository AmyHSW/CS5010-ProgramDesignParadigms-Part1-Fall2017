package edu.neu.ccs.cs5010.assignment3;

import java.util.List;

/**
 * The <code>IMemberDatabase</code> interface represents a member database.
 *
 * @author Shuwan Huang
 */
public interface IMemberDatabase {

    /**
     * Returns a list that contains all members in the csv file.
     * @return a list that contains all members in the csv file.
     */
    List<IMember> getMemberList();
}
