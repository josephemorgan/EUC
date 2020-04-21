package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    Week week = new Week(getSampleSummerRates(), getSampleWinterRates());

    private static final int HOURS_IN_DAY = 24;

    @Test
    void getDay() {

    }

    @Test
    void getDay1() {
    }

    @Test
    void addDay() {
    }

    @Test
    void fetchDayFromFile() {
    }

    @Test
    void getTotalUsage() {
    }

    @Test
    void getTotalCost() {
    }

    @Test
    void getSeason() {
    }

    @Test
    void setSeason() {
    }

    @Test
    void setSummerRates() {
    }

    @Test
    void setWinterRates() {
    }


    Rates getSampleSummerRates(){
        Rates rates = new Rates();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            rates.setRates(i, 2.0);
        }

        return rates;
    }

    Rates getSampleWinterRates(){
        Rates rates = new Rates();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            rates.setRates(i, 3.0);
        }

        return rates;
    }
}