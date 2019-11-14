package org.marineDigitalJournal.neuralnet;

public class DisplayData_Thread extends Thread{
	
	
	public void launch() {
		new Thread() {
		    public void run() {
		    	DisplayData displayData = new DisplayData();
				displayData.display();
		    }
		}.start();
		}

	

}
