package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ArgumentsParserTest {

    private IArgumentsParser legalArguments1, legalArguments2, illegalArguments1, illegalArguments2;

    @Before
    public void setUp() throws Exception {
        String[] args1 = {
                "--email-template", "email-template.txt",
                "--output-dir", "emails",
                "--csv-file", "Flight363FromSeattleToBoston.csv",
                "--event", "arrival"
        };
        String[] args2 = {
                "--csv-file", "Flight363FromSeattleToBoston.csv",
                "--email-template", "email-template.txt",
                "--output-dir", "emails",
                "--event", "arrival"
        };
        String[] args3 = {
                "--email-template", "--output-dir",
                "--csv-file", "Flight363FromSeattleToBoston.csv",
                "--event", "arrival"
        };
        String[] args4 = {
                "--email-template", "email-template.txt",
                "--output-dir", "emails",
                "--csv-file", "Flight363.csv",
                "--event", "arrival"
        };
        legalArguments1 = new ArgumentsParser(args1);
        legalArguments2 = new ArgumentsParser(args2);
        illegalArguments1 = new ArgumentsParser(args3);
        illegalArguments2 = new ArgumentsParser(args4);
    }

    @Test
    public void isLegalFormat() throws Exception {
        assertTrue(legalArguments1.isLegalFormat());
        assertTrue(legalArguments2.isLegalFormat());
        assertTrue(!illegalArguments1.isLegalFormat());
        assertTrue(!illegalArguments2.isLegalFormat());
    }

    @Test
    public void getErrorMessage() throws Exception {
        assertTrue(legalArguments1.getErrorMessage() == null);
        assertTrue(legalArguments2.getErrorMessage() == null);
        assertTrue(illegalArguments1.getErrorMessage() != null);
        assertTrue(illegalArguments2.getErrorMessage() != null);

        System.out.println(illegalArguments1.getErrorMessage());
        System.out.println(illegalArguments2.getErrorMessage());
    }

    @Test
    public void getEmailTemplate() throws Exception {
        assertTrue("legalArguments1 returns wrong email template",
                    legalArguments1.getEmailTemplate().equals("email-template.txt"));
        assertTrue("legalArguments2 returns wrong email template",
                    legalArguments2.getEmailTemplate().equals("email-template.txt"));
        assertTrue(illegalArguments1.getEmailTemplate() == null);
        assertTrue(illegalArguments2.getEmailTemplate() == null);
    }

    @Test
    public void getOutputDirection() throws Exception {
        assertTrue("legalArguments1 returns wrong output dir",
                   legalArguments1.getOutputDir().equals("emails"));
        assertTrue("legalArguments2 returns wrong output dir",
                   legalArguments2.getOutputDir().equals("emails"));
        assertTrue(illegalArguments1.getOutputDir() == null);
        assertTrue(illegalArguments2.getOutputDir() == null);
    }

    @Test
    public void getCsvFile() throws Exception {
        assertTrue(legalArguments1.getCsvFile().equals("Flight363FromSeattleToBoston.csv"));
        assertTrue(legalArguments2.getCsvFile().equals("Flight363FromSeattleToBoston.csv"));
        assertTrue(illegalArguments1.getCsvFile() == null);
        assertTrue(illegalArguments2.getCsvFile() == null);
    }

    @Test
    public void getFlightInfo() throws Exception {
        Map<String, String> info = legalArguments1.getFlightInfo();
        assertTrue(info.get("event").equals("arrival"));
        assertTrue(info.get("departure-city").equals("Seattle"));
        assertTrue(info.get("destination-city").equals("Boston"));
        assertTrue(info.get("flight-number").equals("Flight363"));

        info = legalArguments2.getFlightInfo();
        assertTrue(info.get("event").equals("arrival"));
        assertTrue(info.get("departure-city").equals("Seattle"));
        assertTrue(info.get("destination-city").equals("Boston"));
        assertTrue(info.get("flight-number").equals("Flight363"));

        assertTrue(illegalArguments1.getFlightInfo() == null);
        assertTrue(illegalArguments2.getFlightInfo() == null);
    }

}