package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    Week week = new Week(getSampleSummerRates(), getSampleWinterRates());

    Day sampleDay = new Day(null);
    Day sampleDayWithDate = new Day(null, "2020-03-02");

    private static final String TEST_FILE_PATH = "import\\dailyElectricityUsage_2020_02_28.json";
    private static final int HOURS_IN_DAY = 24;

    @Test
    void getDay() {
        week.addDay(sampleDay);
        Day day = week.getDay(0);
        assertEquals(day.getDate(), LocalDate.now());
    }

    @Test
    void getDay_1() {
        week.addDay(sampleDay);
        week.addDay(sampleDayWithDate);

        String target = "2020-03-02";
        String fetch = week.getDay(target).getDateAsString();

        assertEquals(target, fetch);
    }

    @Test
    void getDay_2() {
        week.addDay(sampleDay);
        week.addDay(sampleDayWithDate);

        String target = "2020-03-03";

        assertNull(week.getDay(target));
    }

    @Test
    void addDay() {
        Day altDay = new Day(null, "2020-07-11");

        week.addDay(altDay);

        assertEquals(altDay, week.getDay(0));
    }

    @Test
    void fetchDayFromFile() {
        week.fetchDayFromFile(TEST_FILE_PATH);

        assertEquals(26.8152, week.getTotalUsage());
        assertEquals("2020-02-28", week.getDay(0).getDateAsString());
    }

    @Test
    void getTotalUsage() {
        //Tested by fetchDayFromFile()
    }

    @Test
    void getTotalCost() {
        week.fetchDayFromFile(TEST_FILE_PATH);

        assertEquals(80.4456, week.getTotalCost());
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