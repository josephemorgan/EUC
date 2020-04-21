package edu.csus.csc131.euc;

import java.time.LocalDate;
import java.util.ArrayList;

public class Week {

    private Parser parser = new Parser();

    private Rates summerRates;
    private Rates winterRates;

    private ArrayList<Day> days = new ArrayList();
    private int numOfDays = 0;

    //Constructor
    public Week() {
    }

    public Week(Rates summerRates, Rates winterRates) {
        this.summerRates = summerRates;
        this.winterRates = winterRates;
    }

    public Day getDay(int i) {
        return days.get(i);
    }

    public Day getDay(String date) {
        for (Day day : days) {
            if (date.equals(day.getDateAsString())) {
                return day;
            }
        }
        return null;
    }

    public void addDay(Day dayToAdd) {

        if (isWinter(dayToAdd.getDate()))
            dayToAdd.setRates(this.winterRates);
        else
            dayToAdd.setRates(this.winterRates);

        days.add(dayToAdd);
        numOfDays++;
    }

    public void fetchDayFromFile(String filePath) {

        parser.fetchData(filePath);

        Day in;

        //Create instance of day with either summer or winter rates, depending on month
        if (isWinter(parser.getDate()))
            in = new Day(this.winterRates);
        else
            in = new Day(this.summerRates);

        in.setDate(parser.getDate());
        in.setUsage(parser.getValues());

        days.add(in);
        numOfDays++;
    }

    public double getTotalUsage() {
        double totalUsage = 0.0;
        for (Day day : days) {
            totalUsage += day.getDailyUsage();
        }

        return totalUsage;
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        for (Day day : days) {
            totalCost += day.getDailyCost();
        }
        return totalCost;
    }

    public void setSummerRates(Rates summerRates) {
        this.summerRates = summerRates;
    }

    public void setWinterRates(Rates winterRates) {
        this.winterRates = winterRates;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    private boolean isWinter(LocalDate in) {
        int month = in.getMonthValue();
        return (month >= 10 || month <= 5);
    }
}
