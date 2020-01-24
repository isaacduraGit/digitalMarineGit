package org.marineDigitalJournal;

import java.io.File;

import javax.swing.table.AbstractTableModel;

//Customize class to customize the DB table.
public class MyTableModel extends AbstractTableModel {

	String[] columnNames = { "user_id", "user_name", "user_surname", "user_email", "user_phone", "user_comments",
			"user_file" };

	Object[][] data = {

			{ 1, "S", "B", "V", "V", "V", new File("F") }

	};

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}

	public String getColumnName(int col) {

		return columnNames[col];

	}

	public boolean isCellEditable(int row, int col) {

		if (col < 1)
			return false;
		else
			return true;

	}

	public void setValueAt(Object value, int row, int col) {

		data[row][col] = value;
		fireTableCellUpdated(row, col);

	}

}
