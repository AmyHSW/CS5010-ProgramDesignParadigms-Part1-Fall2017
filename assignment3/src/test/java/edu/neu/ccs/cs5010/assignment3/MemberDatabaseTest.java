package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

/**
 * The <code>MemberDatabaseTest</code> is a test class for <code>MemberDatabase</code>.
 *
 * @author Shuwan Huang
 */
public class MemberDatabaseTest {

    private IMemberDatabase memberDB = null;

    /**
     * Initializes a new member database before each test with the sample csv file.
     */
    @Before
    public void setUp() throws Exception {
        memberDB = new MemberDatabase("Flight3FromVancouverToSeattle.csv");
    }

    /**
     * Tests that getMemberList returns a list of members. Tests that list size matches the number of members
     * in csv file. Tests that each member object contains attributes provided in the csv file.
     */
    @Test
    public void getMemberList() throws Exception {
        List<IMember> memberList = memberDB.getMemberList();
        assertTrue(memberList.size() == 3);
        for (IMember member : memberDB.getMemberList()) {
            assertTrue(member.hasAttribute("first_name"));
            assertTrue(member.hasAttribute("last_name"));
            assertTrue(member.hasAttribute("address"));
            assertTrue(member.hasAttribute("city"));
            assertTrue(member.hasAttribute("county"));
            assertTrue(member.hasAttribute("state"));
            assertTrue(member.hasAttribute("zip"));
            assertTrue(member.hasAttribute("phone"));
            assertTrue(member.hasAttribute("email"));
            assertTrue(member.hasAttribute("rewards"));

            assertTrue(!member.hasAttribute("Age"));
        }
    }

    /**
     * Tests that MemberDatabase throws an EmptyCsvFileException for an empty csv file.
     */
    @Test(expected = EmptyCsvFileException.class)
    public void testThrowsEmptyCsvFileException() throws Exception {
        File empty = new File("empty.csv");
        empty.createNewFile();
        IMemberDatabase db = new MemberDatabase("empty.csv");

    }

}