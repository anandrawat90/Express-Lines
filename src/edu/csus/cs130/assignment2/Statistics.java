package edu.csus.cs130.assignment2;
/**
 * Statistics.java - 04-Mar-2016
 * @author Anand Rawat
 * This Singleton class is for monitoring the statistics of the simulation
 */
public class Statistics {

	public static int MAX_TIME_UNDER_20I;
	public static int MIN_TIME_UNDER_20I;
	public static int TOTAL_TIME_UNDER_20I;
	public static int AVG_TIME_UNDER_20I;
	public static int TOTAL_CUSTOMERS_UNDER_20I;
	public static int MAX_TIME_OVER_20I;
	public static int MIN_TIME_OVER_20I;
	public static int TOTAL_TIME_OVER_20I;
	public static int AVG_TIME_OVER_20I;
	public static int TOTAL_CUSTOMERS_OVER_20I;
	public static int MAX_TIME;
	public static int MIN_TIME;
	public static int TOTAL_CUSTOMERS;
	public static int TOTAL_CASHIERS;
	public static int MAX_ITEMS_ALLOWED;
	public static int TOTAL_FINISHED_CUSTOMER;
	public static int NUMBER_OF_ITEMS_LIMITATION;
	public static double CUSTOMER_GENERATION_PROBABILITY;
	public static boolean LOGGING_ENABLED;

	private static Statistics stat = null;

	private Statistics() {
		MAX_TIME_UNDER_20I = 0;
		MIN_TIME_UNDER_20I = Integer.MAX_VALUE;
		TOTAL_TIME_UNDER_20I = 0;
		AVG_TIME_UNDER_20I = 0;
		TOTAL_CUSTOMERS_UNDER_20I = 0;
		MAX_TIME_OVER_20I = 0;
		MIN_TIME_OVER_20I = Integer.MAX_VALUE;
		TOTAL_TIME_OVER_20I = 0;
		AVG_TIME_OVER_20I = 0;
		TOTAL_CUSTOMERS_OVER_20I = 0;
		MAX_TIME = 0;
		MIN_TIME = Integer.MAX_VALUE;
		TOTAL_CUSTOMERS = 0;
		TOTAL_CASHIERS = 0;
		MAX_ITEMS_ALLOWED = 0;
		TOTAL_FINISHED_CUSTOMER = 0;
		NUMBER_OF_ITEMS_LIMITATION = 0;
		CUSTOMER_GENERATION_PROBABILITY = 0.0;
		LOGGING_ENABLED = Boolean.FALSE;
	}
	
	/**
	 * This method is used to obtain an instance of Statistics
	 */
	public static Statistics getSingleton() {
		if (stat == null)
			stat = new Statistics();
		return stat;
	}

	/**
	 * This method is used to calculate the average time under 20 items
	 * @return AverageTimeUnder20Items
	 */
	public double getAverageTimeUnder20Items() {
		return ((double)TOTAL_TIME_UNDER_20I /(double)TOTAL_CUSTOMERS_UNDER_20I);
	}

	/**
	 * This method is used calculate the average time over 20 items
	 * @return AverageTimeOver20Items
	 */
	public double getAverageTimeOver20Items() {
		return ((double)TOTAL_TIME_OVER_20I /(double) TOTAL_CUSTOMERS_OVER_20I);
	}

	/**
	 * This method is used calculate the average time
	 * @return Average Time
	 */
	public double getAverageTime(int totalTime) {
		return ((double)totalTime/(double)TOTAL_CUSTOMERS);
	}
	
	/**
	 * This method is used to reset the instance of the Statistics Class
	 */
	public static void reset(){
		boolean logging = stat.LOGGING_ENABLED;
		stat = null;
		getSingleton().LOGGING_ENABLED = logging;
	}
}
