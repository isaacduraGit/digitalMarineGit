package org.marineDigitalJournal.utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
/**
 * This class picks a file from the local system
 */
public class FileChooser {
	
	public boolean fileChoose() {

	JFileChooser jfchooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

	int returnValue = jfchooser.showOpenDialog(null);

	if(returnValue==JFileChooser.APPROVE_OPTION)
	{

		File selectedFile = jfchooser.getSelectedFile();

		System.out.println(selectedFile.getAbsolutePath());

	}
	return false;
	}
}
