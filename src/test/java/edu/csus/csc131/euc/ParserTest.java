package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    Parser parser = new Parser();

    private static final int HOURS_IN_DAY = 24;
    private static final String TEST_FILE_PATH = "import\\dailyElectricityUsage_2020_02_28.json";

    @Test
    void getUserId() {
        parser.fetchData(TEST_FILE_PATH);
        assertEquals(1, parser.getUserId());
    }

    @Test
    void getUnit() {
        parser.fetchData(TEST_FILE_PATH);
        assertEquals("KWH", parser.getUnit());
    }

    @Test
    void getSiteTimeZoneId() {
        parser.fetchData(TEST_FILE_PATH);
        assertEquals("America/Los_Angeles", parser.getSiteTimeZoneId());
    }

    @Test
    void getValues() {
        parser.fetchData(TEST_FILE_PATH);

        double[] expectedValues = {
                0.4272,
                0.3768,
                0.3888,
                0.3888,
                0.5604,
                0.4152,
                0.5952,
                1.5900,
                1.2336,
                1.1556,
                3.4824,
                2.8968,
                1.0452,
                0.4044,
                0.4452,
                0.5088,
                0.5580,
                0.8592,
                1.8720,
                2.2704,
                1.7436,
                1.2960,
                1.1472,
                1.1544
        };
        double[] realValues = parser.getValues();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            assertEquals(expectedValues[i], realValues[i]);
        }
    }

    @Test
    void getDate() {
        parser.fetchData(TEST_FILE_PATH);
        LocalDate date = parser.getDate();

        assertEquals(2020, date.getYear());
        assertEquals(2, date.getMonthValue());
        assertEquals(28, date.getDayOfMonth());
    }
}