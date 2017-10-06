package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EmailGeneratorTest {

    private IEmailGenerator emailGenerator = null;

    @Before
    public void setUp() throws Exception {
        emailGenerator = new EmailGenerator("email-template.txt",
                                            "arrival",
                                            "Vancouver",
                                            "Seattle");
    }

    @Test
    public void testGetEmail() throws Exception {
        Map<String, String> info = new HashMap<>();
        info.put("first_name", "James");
        info.put("last_name", "Butt");
        info.put("email", "jbutt@gmail.com");
        info.put("rewards", "silver");
        IMember member = new Member(info);
        String email = emailGenerator.getEmail(member);
        System.out.println(email);
    }

}