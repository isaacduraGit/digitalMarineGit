package org.marineDigitalJournal.neuralnet;

/**
 * 
 * Activates the iterative model training script
 *
 */

public class TrainningModel extends Thread {

	public void launch() {
		new Thread() {
			public void run() {
				TrainModel trainModel = new TrainModel();
				trainModel.trainModel();

			}
		}.start();
	}

}
