package org.marineDigitalJournal.presentation.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.marineDigitalJournal.Application;

import com.google.common.io.Files;

public class ImageFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ImageFrame(String title, String image) {
		
		File imageFile = new File(Application.getImageDir(), image);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(title);
		getContentPane().setBackground(Color.WHITE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		JPanel panel = new JPanel(new BorderLayout());
		setSize(screenSize.width, screenSize.height);
		setContentPane(panel);
		
		System.out.println(" width: " + screenSize.width + ", height: " + screenSize.height);
		
		try {
			
			JButton downloadButton = new JButton(new ImageIcon(ImageIO.read(getClass().getResource("/download_icon.jpeg")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
			downloadButton.setSize(50, 50);
			downloadButton.setBorder(BorderFactory.createEmptyBorder());

			downloadButton.addActionListener(event -> saveImage(imageFile));
			panel.add(downloadButton, BorderLayout.NORTH);
			
			Image scaledInstance = new ImageIcon(ImageIO.read(imageFile)).getImage().getScaledInstance((int)screenSize.getWidth(), (int)screenSize.getHeight(), Image.SCALE_SMOOTH);
			//Image scaledInstance = new ImageIcon(ImageIO.read(imageFile)).getImage();
			JLabel imageLabel = new JLabel(new ImageIcon(scaledInstance));
			panel.add(imageLabel, BorderLayout.CENTER);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveImage(File imageFile) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a directory to save in");
		//fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setSelectedFile(new File("image.png"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("Image file","png"));

		int userSelection = fileChooser.showSaveDialog(this);
		
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			System.out.println("Save as file: " + fileToSave.getAbsolutePath());
			try {
				Files.copy(imageFile, fileToSave);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Failed to save the image: " + e.getMessage());
			}
		}
		
	}

}
