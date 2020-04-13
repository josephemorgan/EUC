package edu.csus.csc131.euc;

import java.util.HashMap;
import java.util.Map;

public class Rates {
    private static final int HOURS_IN_DAY = 24;
    private Map<Integer, Double> rates;

    public Rates() {
        rates = initMap();
    }

    public double getRate(int i) {
        return rates.get(i);
    }

    public void setRate(int i, double r) {
        rates.replace(i, r);
    }

    @Override
    public String toString() {
        return "Rates{" +
                "rates=" + rates +
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
