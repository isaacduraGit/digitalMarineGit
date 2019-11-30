package org.marineDigitalJournal.neuralnet;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class DownloadMotu {
	
	/*public static void main(String[] args) {
		
		DownloadMotu downloadMotu=new DownloadMotu();
		downloadMotu.downloadSatNear_Real_TimeData();
		
		
	}*/

	public  String downloadSatNear_Real_TimeData() {
		
		
		
		String lineTerminal="";

		try {
			
			

			ProcessBuilder pb = new ProcessBuilder("python",
					"neuralnet/downloadMotu.py");
			Process p = pb.start();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = br.readLine();
			
			System.out.print("pepino");
			
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
