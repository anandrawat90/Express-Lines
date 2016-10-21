package edu.csus.cs130.assignment2;

/**
 * 
 * ExpressQueueSimulation.java - 06-Mar-2016
 * @author Anand Rawat
 * This class is used for simulating scenario with two 2 express queues for checkout
 */
public class ExpressQueueSimulation extends Simulation {
	
	private final int NUMBER_OF_EXPRESS_QUEUES = 2;
	
	/**
	 * assign the newly created customer to a queue
	 */
	@Override
	protected void assignCustomer(Customer customer) {
		int cashierIndex = 0;
		// Get Queue based on customer cart size
		if(customer.getNumberOfItems() < Statistics.getSingleton().NUMBER_OF_ITEMS_LIMITATION)
			cashierIndex = findTheShortestQueue(0,NUMBER_OF_EXPRESS_QUEUES);
		else
			cashierIndex = findTheShortestQueue(NUMBER_OF_EXPRESS_QUEUES,Statistics.getSingleton().TOTAL_CASHIERS);
		cashiers[cashierIndex].addCustomer(customer);
		queueDensityLookUp[cashierIndex]++;
	}
}
