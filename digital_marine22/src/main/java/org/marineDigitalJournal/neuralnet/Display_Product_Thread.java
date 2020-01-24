package org.marineDigitalJournal.neuralnet;
//Activates the generation of a dinamic map based on near-real time EO data
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
