package org.marineDigitalJournal.neuralnet;

public class DownloadMotu extends PythonScript {

	public static void main(String[] args) {

		DownloadMotu display = new DownloadMotu();
		display.downloadSatNear_Real_TimeData();

	}

	public void downloadSatNear_Real_TimeData() {

		execute("/org/marineDigitalJournal/neuralnet/downloadMotu.py");

	}
}
