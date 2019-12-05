package org.marineDigitalJournal.neuralnet;

public class TrainModel extends PythonScript {

	public static void main(String[] args) {

		TrainModel display = new TrainModel();
		display.train_model();

	}

	public void train_model() {
		execute("/org/marineDigitalJournal/neuralnet/train_model.py");
	}

}
