package org.marineDigitalJournal.neuralnet;
//Activates the generation of a dynamic map based on near-real time EO data
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
