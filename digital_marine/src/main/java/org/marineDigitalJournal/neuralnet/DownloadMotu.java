package org.marineDigitalJournal.neuralnet;

/**
 * 
 * Trigger CMEMS satellite download process 
 *
 */

public class DownloadMotu extends PythonScript {

	public static void main(String[] args) {

		DownloadMotu display = new DownloadMotu();
		display.downloadSatNearRealTimeData();

	}

	public void downloadSatNearRealTimeData() {

		execute("/org/marineDigitalJournal/neuralnet/downloadMotu.py");

	}
}
