package edu.csus.csc131.euc;

import com.fasterxml.jackson.databind.node.DoubleNode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

import static java.time.ZoneId.systemDefault;

public class ParserTest {

    public static void main(String[] args) {
        run();
    }


    public static void run(){

        Parser parser = new Parser();

        parser.fetchData("C:\\Users\\amrin\\Documents\\CSC131\\EUC\\euc\\import\\dailyElectricityUsage_2020_02_28.json");

        ArrayList<LocalDate> start = parser.getStartTimes();

        LocalDate first = start.get(1);

        System.out.println(first.getDayOfMonth());

        ArrayList values = parser.getValuesString();

        //DoubleNode single = values.get(1);

        System.out.println("Type: " + values.get(1).getClass());
    }


    public static void timeTest() {

        String timeString = "2020-02-28T01:00:00.000-08:00";
        timeString = timeString.substring(0, 19);

        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss"
        );

        LocalDate localdate = null;

        try {
            Date date = format.parse(timeString);
            localdate = date.toInstant().atZone(systemDefault()).toLocalDate();
        }
        catch (ParseException e) {
            System.out.println("Parse exception");
        }

        System.out.println(localdate.toString());

        System.out.println(localdate.getMonthValue());
    }
}
