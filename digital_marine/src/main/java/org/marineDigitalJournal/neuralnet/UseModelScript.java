package org.marineDigitalJournal.neuralnet;

import org.marineDigitalJournal.Application;

/**
 * 
 * This class effectively calls the useModel script.
 *
 */

public class UseModelScript extends PythonScript {

	public static void main(String[] args) {

		System.out.println("Current dir: " + Application.getWorkingDir());
		UseModelScript display = new UseModelScript();
		display.display();

	}

	public void display() {

		execute("/org/marineDigitalJournal/neuralnet/useModel.py");
	}

}
