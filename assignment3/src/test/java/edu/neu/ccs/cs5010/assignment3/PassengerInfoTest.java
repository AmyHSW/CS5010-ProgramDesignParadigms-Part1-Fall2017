package edu.neu.ccs.cs5010.assignment3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PassengerInfoTest {

    private PassengerInfo psInfo = null;

    @Before
    public void setUp() throws Exception {
        psInfo = new PassengerInfo("Flight3FromVancouverToSeattle.csv");
    }

    @Test
    public void hasNextPassenger() throws Exception {
        int i = 0;
        while (psInfo.hasNextPassenger()) {
            psInfo.nextPassenger();
            i++;
        }
        assertTrue(i == 3);
    }

    @Test
    public void nextPassenger() throws Exception {
        assertTrue(psInfo.nextPassenger() != null);
    }

}