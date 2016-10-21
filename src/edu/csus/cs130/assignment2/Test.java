package edu.csus.cs130.assignment2;

/**
 * 
 * Test.java - 06-Mar-2016
 * @author Anand Rawat
 * This class is for running the simulations
 */
public class Test {

public static void main(String[] args) {
	// Pass 'true' as the first parameter to enable logs
	if(args.length> 0 && Boolean.valueOf(args[0].toLowerCase()))
		Statistics.getSingleton().LOGGING_ENABLED = Boolean.TRUE;
		
	Simulation sim = new SimpleSimulation();
	System.out.println("\nRunning Simulation Without Express Checkout:\n ");
	sim.run();
	
	System.out.println("\nRunning Simulation With 2 Express Checkout:\n ");
	Simulation sim2 = new ExpressQueueSimulation();
	sim2.run();
	}

}
