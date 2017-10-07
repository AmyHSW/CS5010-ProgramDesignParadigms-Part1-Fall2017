package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * The <code>EmailGeneratorTest</code> is a test class for <code>EmailGenerator</code>.
 *
 * @author Shuwan Huang
 */
public class EmailGeneratorTest {

    private IEmailGenerator emailGenerator = null;

    /**
     * Initializes a new EmailGenerator with the email template filename and flight information map.
     */
    @Before
    public void setUp() throws Exception {
        Map<String, String> flightInfo = new HashMap<>();
        flightInfo.put("event", "arrival");
        flightInfo.put("departure-city", "Vancouver");
        flightInfo.put("destination-city", "Seattle");
        emailGenerator = new EmailGenerator("email-template.txt", flightInfo);
    }

    /**
     * Tests that getEmail returns a string that contains the email with placeholders replaced by
     * member information and flight information.
     */
    @Test
    public void getEmail() throws Exception {
        Map<String, String> info = new HashMap<>();
        info.put("first_name", "James");
        info.put("last_name", "Butt");
        info.put("email", "jbutt@gmail.com");
        info.put("rewards", "silver");
        IMember member = new Member(info);
        String email = emailGenerator.getEmail(member);
        System.out.println(email);
    }

    /**
     * Tests that EmailGenerator throws an InvalidPlaceholderException if placeholder information
     * cannot be determined.
     */
    @Test(expected = InvalidPlaceholderException.class)
    public void testThrowsInvalidPlaceholderException() throws Exception {
        Map<String, String> info = new HashMap<>();
        info.put("first_name", "James");
        info.put("email", "jbutt@gmail.com");
        info.put("rewards", "silver");
        IMember member = new Member(info);
        emailGenerator.getEmail(member);
    }
}