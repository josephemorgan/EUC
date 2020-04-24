package edu.csus.csc131.euc;

import java.time.LocalDate;
import java.util.ArrayList;

public class Week {

    private Parser parser = new Parser();

    private Rates summerRates;
    private Rates winterRates;

    private ArrayList<Day> days = new ArrayList();

    //Constructor
    public Week() {
        double[] SR = {.1209d,.1209d,.1209d,.1209d,.1209d,.1209d,
                .1209d,.1209d,.1209d,.1209d,.1209d,.1209d,
                .1671d,.1671d,.1671d,.1671d,.1671d,.2941d,
                .2941d,.2941d,.1671d,.1671d,.1671d,.1671d,};
        double[] WR = {.1006d,.1006d,.1006d,.1006d,.1006d,.1006d,
                .1006d,.1006d,.1006d,.1006d,.1006d,.1006d,
                .1006d,.1006d,.1006d,.1006d,.1006d,.1388d,
                .1388d,.1388d,.1006,.1006,.1006,.1006,};
        summerRates = new Rates(SR);
        winterRates = new Rates(WR);
    }

    public Week(Rates summerRates, Rates winterRates) {
        this.summerRates = summerRates;
        this.winterRates = winterRates;
    }

    public void setSummerRates(Rates summerRates) {
        this.summerRates = summerRates;
    }

    public void setWinterRates(Rates winterRates) {
        this.winterRates = winterRates;
    }

    public int getNumOfDays() {
        return days.size();
    }

    public void addDay(Day dayToAdd) {

        if (isWinter(dayToAdd.getDate()))
            dayToAdd.setRates(this.winterRates);
        else
            dayToAdd.setRates(this.summerRates);
        days.add(dayToAdd);
    }

    public Day removeDay(int i) {
        Day temp = null;
        if (i < days.size()) {
            temp = days.get(i);
            days.remove(i);
        }
        return temp;
    }

    public void removeDay(CharSequence str) {
        for (Day day : days) {
            if (day.getDateAsString().equals(str)) {
                days.remove(day);
                break;
            }
        }
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

    public ArrayList<Day> getListOfDays() {
        return days;
    }

    public double calculateTotalUsage() {
        return unwillingComplianceCalculator.calculateTotalUsage(this);
    }

    public double calculateTotalCost() {
        return unwillingComplianceCalculator.calculateTotalCost(this);
    }

    public String[] getListOfDaysAsStringArray() {
        ArrayList<String> ret = new ArrayList<>();
        for (Day day : days) {
            ret.add(day.getDateAsString());
        }
        return ret.toArray(new String[ret.size()]);
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
    }

    private boolean isWinter(LocalDate in) {
        int month = in.getMonthValue();
        return (month >= 10 || month <= 5);
    }
}
