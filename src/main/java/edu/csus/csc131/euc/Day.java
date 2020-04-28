package edu.csus.csc131.euc;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Day {

    // Index represents hours with contents of array representing the kW/h usage and hourly usage rates respectively.
    protected static final int HOURS_IN_DAY = 24;
    private LocalDate date;
    private Rates rates = null;
    private Usage usage = new Usage();

    // Constructors

    public Day(Rates r) {
        rates = r;
        this.date = LocalDate.now();
    }

    // Sets date to today minus offset number of days.
    public Day(Rates r, long offset) {
        this.date = LocalDate.now().minusDays(offset);
        this.rates = r;
    }

    // Creates Day class with explicit date.
    public Day(Rates r, LocalDate date) {
        this.date = date;
        this.rates = r;
    }

    // Creates Date class with specific date as a string formatted as yyyy-mm-dd
    public Day(Rates r, CharSequence dayString) {
        this.date = LocalDate.parse(dayString);
        this.rates = r;
    }

    public Day (CharSequence dayString, Usage u) {
        try {
            this.date = LocalDate.parse(dayString);
            this.usage = u;
        } catch (DateTimeParseException e) {
            //throw e;  //TODO: CHECK IF IT IS OKAY TO REMOVE THIS
            e.printStackTrace();
        }
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDateAsString(){
        String out = "";
        out += String.format("%02d", this.date.getMonthValue());
        out += "-";
        out += String.format("%02d", this.date.getDayOfMonth());
        out += "-";
        out += this.date.getYear();
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

    public Usage getUsage() {
        return this.usage;
    }

    public Rates getRates() {
        return this.rates;
    }

    // Calculates total usage for the day in kW/h.
    public double getDailyUsage() {
        return Calculator.calculateDailyUsage(this);
    }

    // Calculates total cost for the day in $ based on associated hourly usage and hourly rates.
    public double getDailyCost() {
        return Calculator.calculateDailyCost(this);
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
