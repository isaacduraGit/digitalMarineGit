package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackgroundImage extends javax.swing.JPanel {

	// Creates a new form BackgroundImage
	public BackgroundImage() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		//this.setSize(screenSize.width, screenSize.height);
		this.setSize(600, 600);


	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Dimension size = getSize();

		ImageIcon backgroundImage = new ImageIcon(

				this.getClass().getResource("mainImage.png"));

		//g.drawImage(backgroundImage.getImage(), 0, 0, size.width, size.height, null);
		g.drawImage(backgroundImage.getImage(), 0, 0, 600, 600, null);

		setOpaque(false);
		
		
		
		
	}
	
	


}
