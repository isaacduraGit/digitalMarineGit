package org.marineDigitalJournal.neuralnet;
//Trigger CMEMS satellite download process 
public class Downloadind_Sat_NRT_Data extends Thread{
	
	public void launch() {
	new Thread() {
	    public void run() {
	    	DownloadMotu downloadMotu = new DownloadMotu();

			 downloadMotu.downloadSatNear_Real_TimeData();
	    }
	}.start();
	}

}
