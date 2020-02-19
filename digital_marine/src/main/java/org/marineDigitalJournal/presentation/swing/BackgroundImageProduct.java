package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


/**
 * Class to manipulate newly generated images
 */
public class BackgroundImageProduct extends javax.swing.JPanel {

	// Creates a new form BackgroundImage
	public BackgroundImageProduct() {
		
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
