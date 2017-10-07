package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * The <code>MemberTest</code> is a test class for <code>Member</code>.
 *
 * @author Shuwan Huang
 */
public class MemberTest {

    private IMember member = null;

    /**
     * Initializes a new Member with the map before each test.
     */
    @Before
    public void setUp() throws Exception {
        Map<String, String> info = new HashMap<>();
        info.put("first_name", "James");
        info.put("last_name", "Butt");
        info.put("email", "jbutt@gmail.com");
        info.put("rewards", "silver");
        member = new Member(info);
    }

    /**
     * Tests that hasAttribute returns true if this member has the information.
     */
    @Test
    public void hasAttribute() throws Exception {
        assertTrue(member.hasAttribute("first_name"));
    }

    /**
     * Tests the getAttribute returns the correct information.
     */
    @Test
    public void getAttribute() throws Exception {
        assertTrue(member.getAttribute("first_name").equals("James"));
        assertTrue(member.getAttribute("phone") == null);
    }

    /**
     * Tests that equals returns true for equal members; false if this member is compared to null or
     * non-member object.
     */
    @Test
    public void equals() throws Exception {
        Map<String, String> copy = new HashMap<>();
        copy.put("first_name", "James");
        copy.put("last_name", "Butt");
        copy.put("email", "jbutt@gmail.com");
        copy.put("rewards", "silver");
        assertTrue(member.equals(new Member(copy)));
        assertTrue(!member.equals(null));
        assertTrue(member.equals(member));
        assertTrue(!member.equals("notMember"));
    }

    /**
     * Tests that equal members have the same hashcode.
     */
    @Test
    public void testHashCode() throws Exception {
        Map<String, String> copy = new HashMap<>();
        copy.put("first_name", "James");
        copy.put("last_name", "Butt");
        copy.put("email", "jbutt@gmail.com");
        copy.put("rewards", "silver");
        assertTrue(member.hashCode() == (new Member(copy)).hashCode());
    }

}