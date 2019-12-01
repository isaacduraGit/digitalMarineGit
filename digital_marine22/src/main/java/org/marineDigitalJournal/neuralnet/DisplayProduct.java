package org.marineDigitalJournal.neuralnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.marineDigitalJournal.Application;
import org.python.util.PythonInterpreter;

public class DisplayProduct {

	public static void main(String[] args) {

		System.out.println("Current dir: " + Application.getWorkingDir());

		DisplayProduct display = new DisplayProduct();
		display.display();

	}

	public void display() {

		// RetrieveEmail retrieveEmail=new RetrieveEmail();

		PythonInterpreter interpreter = new PythonInterpreter();

		String scriptPath = Application.getWorkingDir() + "/org/marineDigitalJournal/neuralnet/plot_product.py";

		if (!Files.exists(Paths.get(scriptPath))) { 
			System.out.println("Script file doens't exists" + scriptPath);
		}
		
		try {

			System.out.println("executing python script: " + scriptPath);
			
			ProcessBuilder pb = new ProcessBuilder("python", scriptPath);

			Process p = pb.start();

			p.waitFor();

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = br.readLine();

			while (line != null) {

//				if (line.contains("forecastVAlue")) {
//
//					DigitalMarineApplication.limit_CHL= "-1";//line.substring(13, 15);
//
//				}

				line = br.readLine();
				System.out.println(line);
			}

			// retrieveEmail.retrieveEmail();

//			SendEmail sendEmail = new SendEmail();
//			
//			ArrayList email=retrieveEmail.retrieveEmail();
//			
//			for(int i=0;i<email.size();i++) {
//				
//				
//				if(((String) email.get(i)).contains("@")) {
//			
//			if (DigitalMarineApplication.limit_CHL == "-1") {
//
//				sendEmail.send(email.get(i).toString());
//			}}
//
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
