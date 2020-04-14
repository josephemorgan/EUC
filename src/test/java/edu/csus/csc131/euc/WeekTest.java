package edu.csus.csc131.euc;

public class WeekTest {

    public static void main(String[] args) {
        testWeek();
    }


    public static void testWeek(){

        double[] summerRates = new double[24];
        double[] winterRates = new double[24];

        for(int i = 0; i < summerRates.length; i++){
            summerRates[i] = 4.0;
        }
        for(int i = 0; i < winterRates.length; i++){
            winterRates[i] = 2.0;
        }

        Rates summer = new Rates(summerRates);
        Rates winter = new Rates(winterRates);

        Week week = new Week(summer, winter);

        week.fetchDayFromFile("import\\dailyElectricityUsage_2020_02_28.json");

        double total = week.getTotalUsage();
        System.out.println(total);
    }
}
