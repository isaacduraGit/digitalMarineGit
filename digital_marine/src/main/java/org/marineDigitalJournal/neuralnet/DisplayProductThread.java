package org.marineDigitalJournal.neuralnet;

/**
 * 
 * This class launches the plotting of the EO satellite retrieved data.
 *
 */

public class DisplayProductThread extends Thread {

	public void launch() {
		new Thread() {
			public void run() {
				DisplayProduct displayData = new DisplayProduct();
				displayData.display();
			}
		}.start();
	}

}
