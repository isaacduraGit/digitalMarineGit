package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BackgroundImage_Product extends javax.swing.JPanel {

	// Creates a new form BackgroundImage
	public BackgroundImage_Product() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(screenSize.width, screenSize.height);


	}
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Dimension size = getSize();

		ImageIcon backgroundImage_Product = new ImageIcon(
				
				
				this.getClass().getResource("results.png"));

		g.drawImage(backgroundImage_Product.getImage(), 0, 0, size.width, size.height, null);

		setOpaque(false);
		
		
		
		
	}
	
	


}
