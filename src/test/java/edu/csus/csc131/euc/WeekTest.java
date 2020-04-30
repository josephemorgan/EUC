package edu.csus.csc131.euc;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    Week week = new Week(getSampleSummerRates(), getSampleWinterRates());

    Day sampleDay = new Day((Rates)null);
    Day sampleDayWithDate = new Day(null, "2020-03-02");

    private static final String TEST_FILE_PATH = "import\\dailyElectricityUsage_2020_02_28.json";
    private static final String UNIX_TEST_FILE_PATH = "import/dailyElectricityUsage_2020_02_28.json";
    private static final String UNIX_TEST_FILE_PATH_SUMMER = "import/dailyElectricityUsage_2020_07_28.json";
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

        String target = "03-02-2020";
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
    void getDay_3() {
        week.addDay(sampleDay);

        String target = "2020-03-04";

        assertNull(week.getDay(3));
    }

    @Test
    void addDay() {
        Day altDay = new Day(null, "2020-07-11");

        week.addDay(altDay);

        assertEquals(altDay, week.getDay(0));
    }

    @Test
    void fetchDayFromFile() {
        week.fetchDayFromFile(UNIX_TEST_FILE_PATH);

        assertEquals(26.8152, week.calculateTotalUsage());
        assertEquals("02-28-2020", week.getDay(0).getDateAsString());
    }

    @Test
    void getTotalUsage() {
        //Tested by fetchDayFromFile()
    }

    @Test
    void getTotalCost() {
        week.fetchDayFromFile(UNIX_TEST_FILE_PATH);

        assertEquals(80.4456, week.calculateTotalCost());

        week.fetchDayFromFile(UNIX_TEST_FILE_PATH_SUMMER);

        assertEquals(134.076, week.calculateTotalCost());
    }


    public static Rates getSampleSummerRates(){
        Rates rates = new Rates();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            rates.setRates(i, 2.0);
        }

        return rates;
    }

    public static Rates getSampleWinterRates(){
        Rates rates = new Rates();

        for(int i = 0; i < HOURS_IN_DAY; i++){
            rates.setRates(i, 3.0);
        }

        return rates;
    }


    @Test
    void getNumOfDays() {
        Week altWeek = new Week(getSampleSummerRates(), getSampleWinterRates());

        assertEquals(0, altWeek.getNumOfDays());

        altWeek.addDay(new Day(null, "2020-03-02"));
        altWeek.addDay(new Day(null, "2020-02-14"));
        altWeek.addDay(new Day(null, "2020-02-14"));

        assertEquals(2, altWeek.getNumOfDays());

        altWeek.removeDay("02-14-2020");

        assertEquals(1, altWeek.getNumOfDays());
    }
}