package org.marineDigitalJournal.neuralnet;

import org.marineDigitalJournal.Application;

public class DisplayProduct extends PythonScript {

	public static void main(String[] args) {

		System.out.println("Current dir: " + Application.getWorkingDir());
		DisplayProduct display = new DisplayProduct();
		display.display();

	}

	public void display() {

		execute("/org/marineDigitalJournal/neuralnet/plot_product.py");
	}

}
