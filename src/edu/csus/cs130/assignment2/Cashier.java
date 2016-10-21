package edu.csus.cs130.assignment2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Cashier.java - 06-Mar-2016
 * @author Anand Rawat
 * This class represents a Cashier Object
 */
public class Cashier {
	
	/*
	 *  This is the queue associated with the cashier.
	 *  Queues provide additional insertion, extraction, and inspection operations
	 */
	private Queue<Customer> queue;
	
	Cashier(){
		// Using linked list implemention as the cashier queues don't have a fixed size 
		// and need to dynamically expand and reduce as per the number of customers.
		queue = new LinkedList<Customer>();
	}
	
	/**
	 * 
	 * This method is used for getQueueSize
	 * @return size of the cashier queue
	 */
	public int getQueueSize(){
		return queue.size();
	}
	
	/**
	 * 
	 * This method is used for adding Customer to the cashier queue
	 * @param customer
	 */
	public void addCustomer(Customer customer){
		queue.add(customer);
	}
	
	/**
	 * 
	 * This method is used for processing Customers
	 * @param tick
	 * @return true/false - indicates if the customer is processed completely.
	 */
	public boolean processCustomer(int tick){
		Customer cust = queue.peek();
		boolean customerCompleted = false;
		// process an item of the customer and check if there are no remaining items
		if(cust.processItem()== 0){
			// set Completed time  
			cust.setCompletedTime(tick);
			int processingTime = tick - cust.getAllocationTime();
			//If customer had less then 20 items
			if(cust.getNumberOfItems() < Statistics.getSingleton().NUMBER_OF_ITEMS_LIMITATION){
				//If the time is less than the minimum time encountered, save it. 
				if(processingTime < Statistics.getSingleton().MIN_TIME_UNDER_20I)
					Statistics.getSingleton().MIN_TIME_UNDER_20I = processingTime;
				//If the time is less than the maximum time encountered, save it.
				if(processingTime > Statistics.getSingleton().MAX_TIME_UNDER_20I)
					Statistics.getSingleton().MAX_TIME_UNDER_20I = processingTime;
				//Add the total time to the over all time
				Statistics.getSingleton().TOTAL_TIME_UNDER_20I+=processingTime;
			}
			// if customer had 20 or more items
			else {
				// Record the time if minimum of all time
				if(processingTime < Statistics.getSingleton().MIN_TIME_OVER_20I)
					Statistics.getSingleton().MIN_TIME_OVER_20I = processingTime;
				// Record the time if maximum of all time
				if(processingTime > Statistics.getSingleton().MAX_TIME_OVER_20I)
					Statistics.getSingleton().MAX_TIME_OVER_20I = processingTime;
				//Add the total time to the over all time
				Statistics.getSingleton().TOTAL_TIME_OVER_20I+=processingTime;
			}
			//Dequeue the customer
			queue.remove();
			customerCompleted = true;
		}
		if(Statistics.getSingleton().LOGGING_ENABLED)
			System.out.println("Tick: "+tick+" Customer Processed:"+cust);
		return customerCompleted;
	}
	
	//This method is used to indicate if the cashier is free.
	public boolean isFree(){
		return queue.isEmpty();
	}
}
