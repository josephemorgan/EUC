package edu.csus.csc131.euc;

import java.time.LocalDate;
import java.util.ArrayList;

public class Week {

    private Parser parser;

    private Rates summerRates;
    private Rates winterRates;

    private ArrayList<Day> days;

    //Constructor
    public Week(){}


    public void fetchDayFromFile(String filePath){

        parser.fetchData(filePath);

        Day in;

        if(isWinter(parser.getDate()))
            in = new Day(this.winterRates);
        else
            in = new Day(this.summerRates);

        in.setDate(parser.getDate());
        in.setUsage(parser.getValues());
    }


    private boolean isWinter(LocalDate in){
        int month = in.getMonthValue();
        return (month >= 10 || month <= 5);
    }


}
