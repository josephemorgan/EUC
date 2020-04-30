package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Day day = new Day(DayTest.getSampleRates());
    Week week = new Week(WeekTest.getSampleSummerRates(), WeekTest.getSampleWinterRates());

    private static final int HOURS_IN_DAY = 24;
    private static final String UNIX_TEST_FILE_PATH = "import/dailyElectricityUsage_2020_02_28.json";
    private static final String UNIX_TEST_FILE_PATH_SUMMER = "import/dailyElectricityUsage_2020_07_28.json";

    @Test
    void calculateDailyCost() {
        day.setUsage(DayTest.getSampleUsage());

        assertEquals(26.8152 * 2, day.getDailyCost());
    }

    @Test
    void calculateDailyCost_1() {

        Day altDay = new Day(null);

        altDay.setUsage(DayTest.getSampleUsage());

        assertEquals(-1, altDay.getDailyCost());
    }

    @Test
    void calculateDailyUsage() {

        day.setUsage(DayTest.getSampleUsage());

        assertEquals(26.8152, day.getDailyUsage());
    }

    @Test
    void calculateDailyUsage_1() {

        double[] usageValues = DayTest.getSampleUsage();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            day.setUsage(i, usageValues[i]);
        }

        assertEquals(26.8152, day.getDailyUsage());
    }

    @Test
    void calculateDailyUsage_2() {

        Day altDay = new Day((Rates)null);

        double[] usageValues = DayTest.getSampleUsage();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            altDay.setUsage(i, usageValues[i]);
        }

        assertEquals(-1.0, altDay.getDailyUsage());
    }

    @Test
    void calculateDailyUsage_3() {

        Usage u = new Usage();
        u.setUsage(DayTest.getSampleUsage());

        Day altDay = new Day("2020-03-02", u);

        assertEquals("03-02-2020", altDay.getDateAsString());
    }


    @Test
    void calculateTotalCost() {
        week.fetchDayFromFile(UNIX_TEST_FILE_PATH);

        assertEquals(80.4456, week.calculateTotalCost());

        week.fetchDayFromFile(UNIX_TEST_FILE_PATH_SUMMER);

        assertEquals(134.076, week.calculateTotalCost());

    }

    @Test
    void calculateTotalUsage() {
        week.fetchDayFromFile(UNIX_TEST_FILE_PATH);

        assertEquals(26.8152, week.calculateTotalUsage());
        assertEquals("02-28-2020", week.getDay(0).getDateAsString());
    }
}