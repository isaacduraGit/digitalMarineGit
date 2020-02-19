package org.marineDigitalJournal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public    class ImageRenderer extends DefaultTableCellRenderer {

	 @Override
	 public Component getTableCellRendererComponent(
	 JTable table,
	 Object value,
	 boolean isSelected,
	 boolean hasFocus,
	 int row,
	 int column) {
	 //If we're dealing with column 7 ("user_file")
	 //And the value that was passed to the column
	 //was a buffered image -- then create a renderer using
	 //a JLabel and paint the image onto the label
	 //using an ImageIcon wrapper
	 if (column == 6 && value instanceof BufferedImage) {
		 
	 	 int width = 48;
		 int height = 48;
		 BufferedImage bufferedImage = (BufferedImage) value;

		 Image tmp = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		 BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		 Graphics2D g2d = resized.createGraphics();
		 g2d.drawImage(tmp, 0, 0, null);
		 g2d.dispose();

		 JLabel label = new JLabel(new ImageIcon(resized));
		 //Try to handle labels that might become too big
		 label.setPreferredSize(new Dimension(width, height));
		 label.setMaximumSize(new Dimension(width, height));
		 label.setMaximumSize(new Dimension(width, height));

		 return label;
	 
	 }
	 //If we're not dealing with column 7
	 //then let the table decide on its own
	 //what it should use to render the value
	 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	 }

	}  