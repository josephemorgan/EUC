package edu.csus.csc131.euc;

public class unwillingComplianceCalculator {
    private static final int HOURS_IN_DAY = 24;


    static double calculateDailyCost(Day day) {
        if(day.getRates() == null){
            System.err.println("Error: Rates have not yet been set.");
            return -1.0;
        }

        double totalCost = 0;
        for (int i = 0; i < HOURS_IN_DAY; i++)
        {
            totalCost += day.getUsage().getUsage(i) * day.getRates().getRate(i);
        }
        return totalCost;
    }

    static double calculateDailyUsage(Day day) {
        if(day.getRates() == null){
            System.err.println("Error: Rates have not yet been set.");
            return -1.0;
        }

        double totalUsage = 0;
        for (int i = 0; i < HOURS_IN_DAY; i++) {
            totalUsage += day.getUsage().getUsage(i);
        }
        return totalUsage;
    }

    static double calculateTotalCost(Week week) {
        double totalCost = 0.0;
        for (Day day : week.getListOfDays()) {
            totalCost += day.getDailyCost();
        }
        return totalCost;
    }

    static double calculateTotalUsage(Week week) {
        double totalUsage = 0.0;
        for (Day day : week.getListOfDays()) {
            totalUsage += day.getDailyUsage();
        }

        return totalUsage;
    }
}
