package org.marineDigitalJournal.utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class FileChooser {
	
	public boolean fileChoose() {

	JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

	int returnValue = jfc.showOpenDialog(null);

	if(returnValue==JFileChooser.APPROVE_OPTION)
	{

		File selectedFile = jfc.getSelectedFile();

		System.out.println(selectedFile.getAbsolutePath());

	}
	return false;
	}
}
