package org.marineDigitalJournal.neuralnet;

/**
 * 
 * This class launches the plotting of the satellite retrieved data.
 *
 */

public class DisplayDataThread extends Thread{
	
	
	public void launch() {
		new Thread() {
		    public void run() {
		    	DisplayProduct displayData = new DisplayProduct();
				displayData.display();
		    }
		}.start();
		}

	

}
