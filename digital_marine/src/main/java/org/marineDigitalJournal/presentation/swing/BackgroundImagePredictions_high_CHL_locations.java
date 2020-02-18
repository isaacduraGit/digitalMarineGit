package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


public class BackgroundImagePredictions_high_CHL_locations extends javax.swing.JPanel {
	ImageIcon BackgroundImagePredictions_high_CHL_locations;
	Dimension size;

	public BackgroundImagePredictions_high_CHL_locations() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(screenSize.width, screenSize.height);

		size = getSize();

		BackgroundImagePredictions_high_CHL_locations = new ImageIcon(this.getClass().getResource("predictions_high_CHL_locations.png"));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BackgroundImagePredictions_high_CHL_locations.getImage(), 0, 0, size.width, size.height, null);

		setOpaque(false);

	}

}

	



