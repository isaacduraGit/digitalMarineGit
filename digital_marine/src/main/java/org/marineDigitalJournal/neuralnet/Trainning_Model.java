package org.marineDigitalJournal.neuralnet;
//Activates the iterative model training script
public class Trainning_Model extends Thread {

	public void launch() {
		new Thread() {
			public void run() {
				TrainModel trainModel = new TrainModel();
				trainModel.train_model();

			}
		}.start();
	}

}
