package org.marineDigitalJournal.presentation.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;


/**
 * Class to manipulate newly generated images
 */
public class BackgroundImageCHLPredictionsNextDay extends javax.swing.JPanel {
	ImageIcon backgroundImageCHLPredictionsNextDay;
	Dimension size;

	public BackgroundImageCHLPredictionsNextDay() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		this.setSize(screenSize.width, screenSize.height);

		size = getSize();

		backgroundImageCHLPredictionsNextDay = new ImageIcon(

				this.getClass().getResource("CHL_predictions_next_day.png"));

	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(backgroundImageCHLPredictionsNextDay.getImage(), 0, 0, size.width, size.height, null);

		setOpaque(false);

	}

}
