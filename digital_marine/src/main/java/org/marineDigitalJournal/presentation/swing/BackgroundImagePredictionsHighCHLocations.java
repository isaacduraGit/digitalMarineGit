package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;


public class BackgroundImagePredictionsHighCHLocations extends javax.swing.JPanel {
	ImageIcon backgroundImagePredictionsHighCHLocations;
	Dimension size;

	public BackgroundImagePredictionsHighCHLocations() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(screenSize.width, screenSize.height);

		size = getSize();

		backgroundImagePredictionsHighCHLocations = new ImageIcon(this.getClass().getResource("predictions_high_CHL_locations.png"));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(backgroundImagePredictionsHighCHLocations.getImage(), 0, 0, size.width, size.height, null);

		setOpaque(false);

	}

}

	



