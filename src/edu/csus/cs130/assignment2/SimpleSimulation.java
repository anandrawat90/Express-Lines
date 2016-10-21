package edu.csus.cs130.assignment2;
 
/**
 * 
 * SimpleSimulation.java - 06-Mar-2016
 * @author Anand Rawat
 * This class is used for simulating simple scenario where there is no express checkout
 */
public class SimpleSimulation extends Simulation {

	/**
	 * assign the newly created customer to a queue
	 */
	@Override
	protected void assignCustomer(Customer customer) {
		// find the shortest cashier queue
		int cashierIndex = findTheShortestQueue(0,queueDensityLookUp.length);
		cashiers[cashierIndex].addCustomer(customer);
		queueDensityLookUp[cashierIndex]++;

	}
}
