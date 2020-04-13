package edu.csus.csc131.euc;

import java.util.HashMap;
import java.util.Map;

public class Usage {
    private static final int HOURS_IN_DAY = 24;
    private Map<Integer, Double> u;

    public Usage() {
        u = initMap();
    }

    public double getUsage(int i) {
        return u.get(i);
    }

    public void setUsage(Double[] u) {

    }

    public void setUsage(int i, double r) {
        u.replace(i, r);
    }

    @Override
    public String toString() {
        return "Usage{" +
                "Usage=" + u +
                '}';
    }

    private static Map<Integer, Double> initMap() {
        Map<Integer, Double> m = new HashMap<>();
        for (int i = 0; i < HOURS_IN_DAY; ++i) {
            m.put(i, 0.0);
        }
        return m;
    }
}
