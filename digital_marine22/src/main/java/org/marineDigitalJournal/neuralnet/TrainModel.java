package org.marineDigitalJournal.neuralnet;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.python.util.PythonInterpreter;

public class TrainModel {

	public static void main(String[] args) {

		System.out.println("Current dir: " + getWorkingDir());

		TrainModel display = new TrainModel();
		display.train_model();

	}

	private static String getWorkingDir() {
		try {
			return new File(TrainModel.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}

	public void train_model() {

		// RetrieveEmail retrieveEmail=new RetrieveEmail();

		PythonInterpreter interpreter = new PythonInterpreter();

		String scriptPath = getWorkingDir() + "/org/marineDigitalJournal/neuralnet/train_model.py";

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
