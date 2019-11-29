package org.marineDigitalJournal.presentation.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BackgroundImage extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	

	// Creates a new form BackgroundImage
	public BackgroundImage() {
		
		super(new BorderLayout());
		try {
			image = ImageIO.read(BackgroundImage.class.getClassLoader().getResource("background.jpg"));
			
			JLabel logo = new JLabel(new ImageIcon(
					ImageIO.read(BackgroundImage.class.getClassLoader().getResource("EOValue.jpg"))));
			logo.setSize(360, 70);
			add(logo, BorderLayout.NORTH);

			
			JLabel blogo = new JLabel(new ImageIcon(
					ImageIO.read(BackgroundImage.class.getClassLoader().getResource("ec-logo.jpg"))));
			blogo.setSize(100, 80);
			add(blogo, BorderLayout.SOUTH);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

	}

}
