package org.marineDigitalJournal.presentation.swing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * Creates a new form BackgroundImage
 */
public class BackgroundImage extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private BufferedImage eulogo;
	

	public BackgroundImage() {
		
		super(new BorderLayout());
		try {
			image = ImageIO.read(BackgroundImage.class.getClassLoader().getResource("background.jpg"));
			
			JLabel logo = new JLabel(new ImageIcon(
					ImageIO.read(BackgroundImage.class.getClassLoader().getResource("EOValue.jpg"))));
			logo.setSize(518, 100);
			add(logo, BorderLayout.NORTH);

			eulogo = ImageIO.read(BackgroundImage.class.getClassLoader().getResource("ec-logo.jpg"));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(eulogo, getWidth()-200, getHeight()-150, 200, 150, this);

	}

}
