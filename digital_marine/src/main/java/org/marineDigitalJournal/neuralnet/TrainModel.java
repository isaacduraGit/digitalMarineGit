package org.marineDigitalJournal.neuralnet;

/**
 * 
 * Activates the iterative model training script
 *
 */

public class TrainModel extends PythonScript {

	public static void main(String[] args) {

		TrainModel display = new TrainModel();
		display.trainModel();

	}

	public void trainModel() {
		execute("/org/marineDigitalJournal/neuralnet/trainModel.py");
	}

}
