package org.marineDigitalJournal.neuralnet;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class DownloadMotu {

	public  String downloadSatNear_Real_TimeData() {
		
		String lineTerminal="";

		try {
			
			

			ProcessBuilder pb = new ProcessBuilder("python",
					"/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/neuralnet/downloadMotu.py");
			Process p = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = br.readLine();
			
			while (line != null) {

				
				line = br.readLine();
				
				lineTerminal=lineTerminal+line+"\n";
			}

			System.out.println("backto java ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lineTerminal;
	}
}
