package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatesTest {

    private static final int HOURS_IN_DAY = 24;

    Rates rates = new Rates();

    @Test
    void getRate() {
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += rates.getRate(i);
        }
        assertEquals(0, tot);
    }

    @Test
    void setRates() {
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            rates.setRates(i, 1.0);
        }
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += rates.getRate(i);
        }
        assertEquals(HOURS_IN_DAY, tot);
    }

    @Test
    void setRates1() {
        double[] hours = new double[HOURS_IN_DAY];
        for(int i = 0; i < HOURS_IN_DAY; i++){
            hours[i] = 2;
        }
        rates.setRates(hours);
        int tot = 0;
        for(int i = 0; i < HOURS_IN_DAY; i++){
            tot += rates.getRate(i);
        }
        assertEquals(HOURS_IN_DAY * 2, tot);
    }
}