package edu.csus.csc131.euc;

import java.time.LocalDate;

public class Day {

    // Index represents hours with contents of array representing the kW/h usage and hourly usage rates respectively.
    private static final int HOURS_IN_DAY = 24;
    private LocalDate date;
    private Rates rates;
    private Usage usage;

    // Constructors
    // Basic constructor sets the date to today.
    public Day(Rates r) {
        date = LocalDate.now();
        rates = r;
    }

    // Sets date to today minus offset number of days.
    public Day(Rates r, long offset) {
        date = LocalDate.now().minusDays(offset);
        rates = r;
    }

    // Creates Day class with explicit date.
    public Day(Rates r, LocalDate date) {
        date = date;
        rates = r;
    }

    // Creates Date class with specific date as a string formatted as yyyy-mm-dd
    public Day(Rates r, CharSequence dayString) {
        date = LocalDate.parse(dayString);
        rates = r;
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
    public void setUsage(double kWh[]) {
        // TODO: EUC-8
        System.out.println("In setUsage(double kWh)");
    }

    public void setUsage(double kWh, int index) {
        // TODO: EUC-8
        System.out.println("In setUsage(double kWh, int index)");
    }

    // Calculates total usage for the day in kW/h.
    public double calculateDailyUsage() {
        // Refactored because of creation or Rates and Usage class.
        double totalUsage = 0;
        for (int i = 0; i < HOURS_IN_DAY; i++) {
            totalUsage += usage.getUsage(i);
        }
        return totalUsage;
    }

    // Calculates total cost for the day in $ based on associated hourly usage and hourly rates.
    public double calculateDailyCost() {
        // TODO: EUC-13 Assigned: Kelly
        System.out.println("In calculateDailyCost");
        return 0;
    }

    @Override
    public String toString() {
        return "Day{" +
                "date=" + date +
                ", rates=" + rates +
                ", usage=" + usage +
                '}';
    }
}
