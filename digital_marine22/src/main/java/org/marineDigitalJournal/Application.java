package org.marineDigitalJournal;

import java.io.File;
import java.net.URISyntaxException;

import org.marineDigitalJournal.neuralnet.DisplayProduct;

public class Application {

	public static String getWorkingDir() {
		try {
			return new File(DisplayProduct.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}

}
