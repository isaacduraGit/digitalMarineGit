package org.marineDigitalJournal;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SwingTableExample extends JPanel {
	private boolean DEBUG = false;

	public SwingTableExample() {
		super(new GridLayout(1, 0));

		JTable table = new JTable(new MyTableModel());

		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		System.out.print(new MyTableModel().getValueAt(0, 1));

		

		// Create the scroll pane and add the table to it
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.

		add(scrollPane);

	}

}
