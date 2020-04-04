package edu.csus.csc131.euc;

import java.time.LocalDate;
import java.util.Arrays;

public class Day {

    // Index represents hours with contents of array representing the kW/h usage and hourly usage rates respectively.
    private static final int HOURS_IN_DAY = 24;

    private double[] kWhUsed = new double[HOURS_IN_DAY];

    private double[] hourlyRates = new double[HOURS_IN_DAY];

    private LocalDate date;

    // Constructors
    // Basic constructor sets the date to today.
    public Day() {
        this.date = LocalDate.now();
    }

    // Sets date to today minus offset number of days.
    public Day(long offset) {
        this.date = LocalDate.now().minusDays(offset);
    }

    // Creates Day class with explicit date.
    public Day(LocalDate date) {
        this.date = date;
    }

    // Creates Date class with specific date as a string formatted as yyyy-mm-dd
    public Day(CharSequence dayString) {
        this.date = LocalDate.parse(dayString);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(CharSequence dayString) {
        this.date = LocalDate.parse(dayString);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateAsString() {
        return this.date.toString();
    }

    // Sets hourly usage in kWh.
    public void setUsage(double kWh, int index) {
        // TODO: EUC-8
        System.out.println("In setUsage(int index)");
    }

    // Calculates total usage for the day in kW/h.
    public double calculateDailyUsage() {
        // TODO: EUC-12
        System.out.println("In calculateDailyUsage");
        return 0;
    }

    // Calculates total cost for the day in $ based on associated hourly usage and hourly rates.
    public double calculateDailyCost() {
        // TODO: EUC-13
        System.out.println("In calculateDailyCost");
        return 0;
    }

    @Override
    public String toString() {
        return "Day{" +
                "HOURS_IN_DAY=" + HOURS_IN_DAY +
                ", kWhUsed=" + Arrays.toString(kWhUsed) +
                ", hourlyRates=" + Arrays.toString(hourlyRates) +
                ", date=" + date +
                '}';
    }
}
