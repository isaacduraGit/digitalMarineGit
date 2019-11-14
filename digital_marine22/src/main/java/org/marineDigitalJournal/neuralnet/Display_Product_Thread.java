package org.marineDigitalJournal.neuralnet;

public class Display_Product_Thread extends Thread {

	public void launch() {
		new Thread() {
			public void run() {
				DisplayProduct displayData = new DisplayProduct();
				displayData.display();
			}
		}.start();
	}

}
