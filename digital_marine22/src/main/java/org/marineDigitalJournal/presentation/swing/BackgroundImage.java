package org.marineDigitalJournal.presentation.swing;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import org.jfree.layout.CenterLayout;

public class BackgroundImage extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	

	// Creates a new form BackgroundImage
	public BackgroundImage() {
		super(new CenterLayout());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		ImageIcon backgroundImage = new ImageIcon(
				this.getClass().getResource("mainImage.png"));

		//g.drawImage(backgroundImage.getImage(), 0, 0, size.width, size.height, null);
		g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

	}

}
