package edu.csus.cs130.assignment2;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * Simulation.java - 05-Mar-2016
 * @author Anand Rawat 
 * This class contains the basic simulation functions.
 */
public abstract class Simulation {

	// Global tick
	private int tick;
	// This is the array list containing all the cashier
	protected Cashier cashiers[];
	// This array contains the size of the each of the cashier queues for faster
	// look up.
	protected int[] queueDensityLookUp;

	// Initialize the constants for the simulation
	private void init() {
		tick = 0;
		Statistics.reset();
		Statistics.getSingleton().TOTAL_CUSTOMERS = 1000;
		Statistics.getSingleton().TOTAL_CASHIERS = 10;
		Statistics.getSingleton().CUSTOMER_GENERATION_PROBABILITY = 0.33;
		Statistics.getSingleton().MAX_ITEMS_ALLOWED = 100;
		Statistics.getSingleton().NUMBER_OF_ITEMS_LIMITATION = 20;
		cashiers = new Cashier[Statistics.getSingleton().TOTAL_CASHIERS];
		for (int i = 0; i < Statistics.getSingleton().TOTAL_CASHIERS; i++) {
			cashiers[i] = new Cashier();
		}
		queueDensityLookUp = new int[Statistics.getSingleton().TOTAL_CASHIERS];
		Arrays.fill(queueDensityLookUp, 0);
	}

	public void run() {
		init();
		int numberOfCustomers = 0;
		while (true) {
			tick++;
			// Create Customer
			if (numberOfCustomers < Statistics.getSingleton().TOTAL_CUSTOMERS) {
				Customer customer = generateCustomer();
				// assign the customer to the queue
				if (customer != null) {
					numberOfCustomers++;
					assignCustomer(customer);
				}
			}
			// Process Queues
			processAllQueues();
			if (Statistics.getSingleton().LOGGING_ENABLED) {
				System.out.println("Tick: " + tick + " Queue Density: "
						+ printQueueDensity().toString());
				System.out
						.println("**********************NEXT OF TICK**********************");
			}

			// Check for completion
			if (Statistics.getSingleton().TOTAL_CUSTOMERS == Statistics
					.getSingleton().TOTAL_FINISHED_CUSTOMER)
				break;
		}

		// Print statistics
		printStatistics();
	}

	private StringBuffer printQueueDensity() {
		StringBuffer buffer = new StringBuffer();
		for (int i : queueDensityLookUp)
			buffer.append(i).append(" ");
		return buffer;
	}

	// Generate a customer with a probability of 33% with every tick.
	private Customer generateCustomer() {
		Customer newCustomer = null;
		Random rand = new Random();
		if (rand.nextDouble() < Statistics.getSingleton().CUSTOMER_GENERATION_PROBABILITY) {
			int nOI = rand.nextInt(Statistics.getSingleton().MAX_ITEMS_ALLOWED) + 1;
			newCustomer = new Customer(nOI, tick);
			if (nOI < Statistics.getSingleton().NUMBER_OF_ITEMS_LIMITATION)
				Statistics.getSingleton().TOTAL_CUSTOMERS_UNDER_20I++;
			else
				Statistics.getSingleton().TOTAL_CUSTOMERS_OVER_20I++;
		}
		if (Statistics.getSingleton().LOGGING_ENABLED && newCustomer != null)
			System.out.println("Tick: " + tick + " Customer Created:"
					+ newCustomer);
		return newCustomer;
	}

	protected abstract void assignCustomer(Customer customer);

	// find the shortest queue depending upon the customer eligibility
	protected int findTheShortestQueue(int startingQueueIndex,
			int numberOfQueues) {
		int cashierIndex = 0;
		int minQueueLength = Integer.MAX_VALUE;
		for (int i = startingQueueIndex; i < numberOfQueues; i++) {
			if (minQueueLength > queueDensityLookUp[i]) {
				minQueueLength = queueDensityLookUp[i];
				cashierIndex = i;
			}
		}
		return cashierIndex;
	}

	// Processing all queues
	private void processAllQueues() {
		int cashierIndex = 0;
		for (Cashier cashier : cashiers) {
			if (!cashier.isFree())
				if (cashier.processCustomer(tick)) {
					Statistics.getSingleton().TOTAL_FINISHED_CUSTOMER++;
					queueDensityLookUp[cashierIndex]--;
				}
			cashierIndex++;
		}
	}

	// To print the statistical data
	private void printStatistics() {
		System.out
				.println("+++++++++++++++++++++++STATISTICS+++++++++++++++++++++++");
		System.out.println("Minimum Time Under 20 Items: "
				+ Statistics.getSingleton().MIN_TIME_UNDER_20I);
		System.out.println("Maximum Time Under 20 Items: "
				+ Statistics.getSingleton().MAX_TIME_UNDER_20I);
		System.out.println("Number of Customer Under 20 Items: "
				+ Statistics.getSingleton().TOTAL_CUSTOMERS_UNDER_20I);
		System.out.println("Average Time Under 20 Items: "
				+ Statistics.getSingleton().getAverageTimeUnder20Items());
		System.out.println("Minimum Time Over 20 Items: "
				+ Statistics.getSingleton().MIN_TIME_OVER_20I);
		System.out.println("Maximum Time Over 20 Items: "
				+ Statistics.getSingleton().MAX_TIME_OVER_20I);
		System.out.println("Number of Customer Over 20 Items: "
				+ Statistics.getSingleton().TOTAL_CUSTOMERS_OVER_20I);
		System.out.println("Average Time Over 20 Items: "
				+ Statistics.getSingleton().getAverageTimeOver20Items());
		System.out.println("Total Time: " + tick);
		System.out.println("Total Customer: "
				+ Statistics.getSingleton().TOTAL_CUSTOMERS);
		System.out.println("Average Time Over All: "
				+ Statistics.getSingleton().getAverageTime(tick));
		System.out
				.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}
