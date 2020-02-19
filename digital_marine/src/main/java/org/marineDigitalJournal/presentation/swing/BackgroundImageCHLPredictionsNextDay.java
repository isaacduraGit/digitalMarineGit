package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class BackgroundImageCHLPredictionsNextDay extends javax.swing.JPanel {
	ImageIcon backgroundImageCHL_predictions_next_day;
	Dimension size;

	public BackgroundImageCHLPredictionsNextDay() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(screenSize.width, screenSize.height);

		size = getSize();

		backgroundImageCHL_predictions_next_day = new ImageIcon(

				this.getClass().getResource("CHL_predictions_next_day.png"));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(backgroundImageCHL_predictions_next_day.getImage(), 0, 0, size.width, size.height, null);

		setOpaque(false);

	}

}
