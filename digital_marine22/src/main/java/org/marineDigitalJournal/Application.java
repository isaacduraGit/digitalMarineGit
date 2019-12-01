package org.marineDigitalJournal;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.marineDigitalJournal.neuralnet.DisplayProduct;

public class Application {
	
	private boolean jarFile;

	public static String getWorkingDir() {
		try {
			URI uri = DisplayProduct.class.getProtectionDomain().getCodeSource().getLocation().toURI();
			if (uri.toString().startsWith("jar:")) {
				String jarFile = uri.toString().replaceFirst("jar:", "").replaceAll("!.+", "");
				String jarPath = new File(jarFile).getParentFile().getAbsolutePath();
				System.out.println("jar dir: " + jarPath);
				return jarPath;
			}
			return new File(uri).getPath();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URI("jar:file:/Developer/projects/work-projects/digitalMarineGit/digital_marine22/target/digital_marine-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!");
		if (uri.toString().startsWith("jar:")) {
			String jarFile = uri.toString().replaceFirst("jar:", "").replaceAll("!.+", "");
			String jarPath = new File(jarFile).getParentFile().getAbsolutePath();
			System.out.println("jar dir: " + jarPath);
		}		
	}

	public static String getImageDir() {
		return getWorkingDir();
	}

}
