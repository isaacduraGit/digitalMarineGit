package org.marineDigitalJournal.neuralnet;
//Trigger CMEMS satellite download process 
public class DownloadindSatNRTData extends Thread{
	
	public void launch() {
	new Thread() {
	    public void run() {
	    	DownloadMotu downloadMotu = new DownloadMotu();

			 downloadMotu.downloadSatNearRealTimeData();
	    }
	}.start();
	}

}
