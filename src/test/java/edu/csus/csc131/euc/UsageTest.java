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

    @Test
    void setUsage2() {
        usage.setUsage(1, -1);
        assertEquals(0, usage.getUsage(1));
    }

    @Test
    void setUsage3() {
        usage.setUsage(-1, 1);
        usage.setUsage(24, 1);
        assertEquals(0, usage.getUsage(0));
    }

    @Test
    void setUsage4() {
        double[] hours = new double[HOURS_IN_DAY];
        for(int i = 0; i < HOURS_IN_DAY; i++){
            hours[i] = 2;
        }
        hours[5] = -1;
        usage.setUsage(hours);
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += usage.getUsage(i);
        }
        assertEquals(0, tot);
    }
}