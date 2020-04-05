package edu.csus.csc131.euc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The parser class is used to retrieve relevant data from a JSON file to be used by the Day class
 * when calculating electricity usage and cost. It is an object that holds the relevant data. Use the
 * fetchData method to retrieve data from a new file, with the file path as the argument.
 *
 * @author Amrin Sandhar
 * @version 4 April 2020
 */

public class Parser {

    private ObjectMapper mapper = new ObjectMapper();

    private int userId;
    private String unit;
    private String siteTimeZoneId;

    private ArrayList<String> startTimes = new ArrayList();
    private ArrayList<String> endTimes = new ArrayList();
    private ArrayList values = new ArrayList();

    public Parser(){}

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

            //Assignment of simple values
            userId = root.get("userId").asInt();
            unit = root.get("unit").asText();
            siteTimeZoneId = root.get("siteTimeZoneId").asText();

            //Assignment of array values under the "reads" object
            root.findValuesAsText("startTime", startTimes);
            root.findValuesAsText("endTime", endTimes);
            root.findValues("value", values);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print out data currently stored in Parser object.
     */

    public void printData(){
        System.out.println("UserId: " + userId);
        System.out.println("Unit: " + unit);
        System.out.println("siteTimeZoneId" + siteTimeZoneId);

        for(int i = 0; i < startTimes.size(); i++){
            System.out.println();
            System.out.println("Hour: " + i);
            System.out.println("startTime: " + startTimes.get(i));
            System.out.println("endTime: " + endTimes.get(i));
            System.out.println("value: " + values.get(i));
            System.out.println();
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

    public ArrayList<String> getStartTimes() {
        return startTimes;
    }

    public ArrayList<String> getEndTimes() {
        return endTimes;
    }

    public ArrayList getValues() {
        return values;
    }
}
