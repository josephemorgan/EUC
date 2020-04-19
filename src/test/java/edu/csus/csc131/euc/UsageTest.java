package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsageTest {

    private static final int HOURS_IN_DAY = 24;

    Usage usage = new Usage();

    @Test
    void getUsage() {
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += usage.getUsage(i);
        }
        assertEquals(0, tot);
    }

    @Test
    void setUsage() {
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            usage.setUsage(i, 1.0);
        }
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += usage.getUsage(i);
        }
        assertEquals(HOURS_IN_DAY, tot);
    }

    @Test
    void setUsage1() {
        double[] hours = new double[HOURS_IN_DAY];
        for(int i = 0; i < HOURS_IN_DAY; i++){
            hours[i] = 2;
        }
        usage.setUsage(hours);
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += usage.getUsage(i);
        }
        assertEquals(HOURS_IN_DAY * 2, tot);
    }
}