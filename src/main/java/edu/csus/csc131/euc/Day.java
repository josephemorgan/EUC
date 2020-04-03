package edu.csus.csc131.euc;

import java.time.LocalDate;
import java.util.Arrays;

public class Day {

    // Index represents hours with contents of array representing the kW/h usage and hourly usage rates respectively.
    private final int hoursInDay = 24;

    private float[] kWhUsed = new float[hoursInDay];

    private float[] hourlyRates = new float[hoursInDay];

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
    public void setUsage(float kWh, int index) {
        // TODO: EUC-8
        System.out.println("In setUsage(int index)");
    }

    // Calculates total usage for the day in kW/h.
    public float calculateDailyUsage() {
        // TODO: EUC-12
        System.out.println("In calculateDailyUsage");
        return 0;
    }

    // Calculates total cost for the day in $ based on associated hourly usage and hourly rates.
    public float calculateDailyCost() {
        // TODO: EUC-13
        System.out.println("In calculateDailyCost");
        return 0;
    }

    @Override
    public String toString() {
        return "Day{" +
                "hoursInDay=" + hoursInDay +
                ", kWhUsed=" + Arrays.toString(kWhUsed) +
                ", hourlyRates=" + Arrays.toString(hourlyRates) +
                ", date=" + date +
                '}';
    }
}
