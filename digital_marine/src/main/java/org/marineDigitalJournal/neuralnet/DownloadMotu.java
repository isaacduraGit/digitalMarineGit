package org.marineDigitalJournal.neuralnet;

public class DownloadMotu extends PythonScript {

	public static void main(String[] args) {

		DownloadMotu display = new DownloadMotu();
		display.downloadSatNearRealTimeData();

	}

	public void downloadSatNearRealTimeData() {

		execute("/org/marineDigitalJournal/neuralnet/downloadMotu.py");

	}
}
