package edu.csus.csc131.euc;

import java.util.HashMap;
import java.util.Map;

public class Usage {

    private static final int HOURS_IN_DAY = 24;
    private Map<Integer, Double> usage;

    //Construct usage object with 0.0 usage for each hour
    public Usage() {
        usage = initMap();
    }

    //Get value at key i
    public double getUsage(int i) {
        return usage.get(i);
    }

    //Copy values from double array into usage map
    public void setUsage(double[] in) {
        for(int i = 0; i < in.length; i++){
            if(in[i] < 0){
                System.out.println("Error: usage at hour \"" + i + "\" cannot be below zero.");
                this.usage = initMap();
                System.out.println("Usage has been reset to 0 for all hours.");
                return;
            }
            this.usage.replace(i, in[i]);
        }
    }

    //Place one value r at the given key i
    public void setUsage(int i, double r) {
        if(r < 0){
            System.out.println("Error: usage entered at hour \"" + i + "\" cannot be below zero.");
            System.out.println("Usage change not accepted.");
            return;
        }
        else if(i < 0 || i > HOURS_IN_DAY - 1){
            System.out.println("Error: hour cannot be less than 0 or greater than 23.");
            return;
        }
        usage.replace(i, r);
    }

    @Override
    public String toString() {
        return "Usage{" +
                "Usage=" + usage +
                '}';
    }

    //Initialize map with hours of day for key and 0.0 for value
    private static Map<Integer, Double> initMap() {
        Map<Integer, Double> m = new HashMap<>();
        for (int i = 0; i < HOURS_IN_DAY; ++i) {
            m.put(i, 0.0);
        }
        return m;
    }
}
