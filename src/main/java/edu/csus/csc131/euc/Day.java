package edu.csus.csc131.euc;

import java.time.LocalDate;

public class Day {

    // Index represents hours with contents of array representing the kW/h usage and hourly usage rates respectively.
    private static final int HOURS_IN_DAY = 24;
    private LocalDate date;
    private Rates rates = null;
    private Usage usage = new Usage();

    // Constructors

    // Basic constructor sets the date to today.
    public Day() {
        this.date = LocalDate.now();
    }

    public Day(Rates r) {
        rates = r;
    }

    // Sets date to today minus offset number of days.
    public Day(Rates r, long offset) {
        this.date = LocalDate.now().minusDays(offset);
        this.rates = r;
    }

    // Creates Day class with explicit date.
    public Day(LocalDate date) {
        this.date = date;
    }

    // Creates Day class with explicit date.
    public Day(Rates r, LocalDate date) {
        this.date = date;
        this.rates = r;
    }

    // Creates Date class with specific date as a string formatted as yyyy-mm-dd
    public Day(CharSequence dayString) {
        this.date = LocalDate.parse(dayString);
    }

    // Creates Date class with specific date as a string formatted as yyyy-mm-dd
    public Day(Rates r, CharSequence dayString) {
        this.date = LocalDate.parse(dayString);
        this.rates = r;
    }

    public Day (CharSequence dayString, Usage u) {
        this.usage = u;
        this.date = LocalDate.parse(dayString);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateAsString(){
        String out = "";
        out += this.date.getYear();
        out += "-";
        out += String.format("%02d", this.date.getMonthValue());
        out += "-";
        out += String.format("%02d", this.date.getDayOfMonth());
        return out;
    }


    public void setUsage(double[] kWh) {
        this.usage.setUsage(kWh);
    }

    public void setUsage(int index, double kWh) {
        usage.setUsage(index, kWh);
    }

    public void setRates(Rates in){
        this.rates = in;
    }

    // Calculates total usage for the day in kW/h.
    public double getDailyUsage() {

        if(this.rates == null){
            System.out.println("Error: Rates have not yet been set.");
            return -1.0;
        }

        double totalUsage = 0;
        for (int i = 0; i < HOURS_IN_DAY; i++) {
            totalUsage += usage.getUsage(i);
        }
        return totalUsage;

    }

    // Calculates total cost for the day in $ based on associated hourly usage and hourly rates.
    public double getDailyCost() {

        if(this.rates == null){
            System.out.println("Error: Rates have not yet been set.");
            return -1.0;
        }

    	double totalCost = 0;
        for (int i = 0; i < HOURS_IN_DAY; i++) 
        {
            totalCost += usage.getUsage(i) * rates.getRate(i);
        }
        return totalCost;
    }

    @Override
    public String toString() {

        if(this.rates == null){
            System.out.println("Error: Rates have not yet been set.");
            return null;
        }

        return "Day{" +
                "date=" + date +
                ", rates=" + rates +
                ", usage=" + usage +
                '}';
    }
}
