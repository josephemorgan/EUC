package edu.csus.csc131.euc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static java.time.ZoneId.systemDefault;

/**
 * The parser class is used to retrieve relevant data from a JSON file to be used by the Day class
 * when calculating electricity usage and cost. It is an object that holds the relevant data. Use the
 * fetchData method to retrieve data from a new file, with the file path as the argument.
 *
 * @author Amrin Sandhar
 * @version 4 April 2020
 */

public class Parser {

    private static final int HOURS_IN_DAY = 24;

    private ObjectMapper mapper = new ObjectMapper();

    private int userId;
    private String unit;
    private String siteTimeZoneId;

    private ArrayList<String> startTimesString = new ArrayList();
    private ArrayList<String> endTimesString = new ArrayList();
    private ArrayList<String> valuesString = new ArrayList();

    private ArrayList<LocalDate> startTimes = new ArrayList();
    private ArrayList<LocalDate> endTimes = new ArrayList();

    private double[] values = new double[HOURS_IN_DAY];

    public Parser() {
    }

    /**
     * Use this method to update the Parser object with data from the given file.
     * After data has been fetched, use getter methods to extract relevant data.
     *
     * @param filePath the file path to the JSON file to be read from
     * @throws IOException
     */

    public void fetchData(String filePath) {

        try {

            //Define JsonNode to read data from
            JsonNode root = mapper.readTree(new File(filePath));

            //Assignment of simple valuesString
            userId = root.get("userId").asInt();
            unit = root.get("unit").asText();
            siteTimeZoneId = root.get("siteTimeZoneId").asText();

            //Assignment of array valuesString under the "reads" object
            root.findValuesAsText("startTime", startTimesString);
            root.findValuesAsText("endTime", endTimesString);
            root.findValuesAsText("value", valuesString);

        } catch (IOException e) {
            e.printStackTrace();
        }

        convertTimeArrays();
        convertValueArray();
    }

    /**
     * Print out data currently stored in Parser object.
     */

    public void printData() {
        System.out.println("UserId: " + userId);
        System.out.println("Unit: " + unit);
        System.out.println("siteTimeZoneId" + siteTimeZoneId);

        for (int i = 0; i < startTimesString.size(); i++) {
            System.out.println();
            System.out.println("Hour: " + i);
            System.out.println("startTime: " + startTimesString.get(i));
            System.out.println("endTime: " + endTimesString.get(i));
            System.out.println("value: " + valuesString.get(i));
            System.out.println();
        }
    }

    //Converts the arrays which contain time in String format to LocalDate format
    private void convertTimeArrays() {

        for (String stringTime : startTimesString) {
            startTimes.add(convertTimeString(stringTime));
        }

        for (String stringTime : endTimesString) {
            endTimes.add(convertTimeString(stringTime));
        }

    }

    //Converts a time in String format to LocalDate format
    private LocalDate convertTimeString(String in) {

        in = in.substring(0, 19);    //Truncate sub-seconds and time zone data from time string

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        LocalDate out = null;

        try {
            //Parses date in String format into a Date object
            Date date = format.parse(in);

            //Assigns the value of the date to the LocalDate object, uses local time zone
            out = date.toInstant().atZone(systemDefault()).toLocalDate();
        } catch (ParseException e) {
            System.out.println("Time String parsing exception.");
            e.printStackTrace();
        }

        return out;
    }

    //Converts String valuesString for usage into doubles
    private void convertValueArray(){
        int i = 0;
        for (String stringValue  : valuesString) {
            values[i] = Double.parseDouble(stringValue);
            i++;
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUnit() {
        return unit;
    }

    public String getSiteTimeZoneId() {
        return siteTimeZoneId;
    }

    public ArrayList<LocalDate> getStartTimes() {
        return startTimes;
    }

    public ArrayList<LocalDate> getEndTimes() {
        return endTimes;
    }

    public ArrayList<String> getValuesString() {
        return valuesString;
    }

    public double[] getValues() {
        return values;
    }
}
