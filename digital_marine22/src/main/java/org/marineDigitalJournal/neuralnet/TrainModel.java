package org.marineDigitalJournal.neuralnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.python.util.PythonInterpreter;

public class TrainModel {
	
	/*public static void main(String[] args) {
		TrainModel model=new TrainModel();
		model.train_model();
	}*/
	
	public void train_model() {

		 PythonInterpreter interpreter = new PythonInterpreter();
	        try {
	        
	        	ProcessBuilder pb = new ProcessBuilder("python","/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/neuralnet/train_model.py");
	        	
	        	Process p= pb.start();

	            
	          p.waitFor();
	            
	            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line = br.readLine();
	            System.out.println("hi1"+line);
	            while (line != null) {
	              
	                System.out.println("hi"+line);
	               line=br.readLine();
	              }
	            
	        
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
	

}
