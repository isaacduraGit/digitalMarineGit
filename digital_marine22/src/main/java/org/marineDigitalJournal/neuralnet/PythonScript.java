package org.marineDigitalJournal.neuralnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.marineDigitalJournal.Application;

public class PythonScript {
	

	protected void execute(String pythonScript) {
			
			String scriptPath = Application.getWorkingDir() + pythonScript;
	
			if (!Files.exists(Paths.get(scriptPath))) { 
				System.out.println("Script file doens't exists" + scriptPath);
				return;
			}
			
			try {
	
				System.out.println("executing python script: " + scriptPath);
				
				ProcessBuilder pb = new ProcessBuilder("python", scriptPath);
	
				Process p = pb.start();
	
				p.waitFor();
	
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
	
				while ((line = br.readLine())  != null) {
	
	//				if (line.contains("forecastVAlue")) {
	//
	//					DigitalMarineApplication.limit_CHL= "-1";//line.substring(13, 15);
	//
	//				}
	
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
				
				if (p != null && p.exitValue() == 0) {
					System.out.println("script " + scriptPath + " exited with success.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
