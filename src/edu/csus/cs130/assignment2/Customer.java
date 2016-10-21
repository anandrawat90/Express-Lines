package edu.csus.cs130.assignment2;

/**
 * 
 * Customer.java - 06-Mar-2016
 * @author Anand Rawat
 * This class represents a Customer object
 */
public class Customer {
	private int numberOfItems;
	private int allocationTime;
	private int completedTime;
	private int remainigItems;

	Customer(int numberOfItems, int allocationTime) {
		this.numberOfItems = numberOfItems;
		this.allocationTime = allocationTime;
		this.remainigItems = numberOfItems;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getAllocationTime() {
		return allocationTime;
	}

	public void setAllocationTime(int allocationTime) {
		this.allocationTime = allocationTime;
	}

	public int getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(int completedTime) {
		this.completedTime = completedTime;
	}
	
	/**
	 * process the item and return the remaining item
	 * @return remaining items 
	 */
	public int processItem() {
		return --remainigItems;
	}

	@Override
	public String toString() {
		return "Customer [numberOfItems=" + numberOfItems + ", allocationTime="
				+ allocationTime + ", completedTime=" + completedTime
				+ ", remainigItems=" + remainigItems + "]";
	}
}
