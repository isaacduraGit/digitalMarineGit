package org.marineDigitalJournal.presentation.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.marineDigitalJournal.ImageRenderer;
import org.marineDigitalJournal.PopulateTable;
import org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository.ConnectionDB;

/**
 * Class to manipulate data from DB and populate Swing table
 */
public class Database extends JFrame implements TableModelListener  {
	
	public Database() {
		try {

			Connection connection = null;
			PreparedStatement st = null;

			ConnectionDB connectionDB = new ConnectionDB();

			ResultSet rs = connectionDB.conectionUpdate(connection, st);

			// Retrieve meta data from ResultSet
			ResultSetMetaData metaData = rs.getMetaData();

			// Get number of columns from meta data
			int columnCount = metaData.getColumnCount();

			DefaultTableModel tableModel = new DefaultTableModel(){
			    @Override
			    public boolean isCellEditable(int row, int column) {
			    	if (column > 0 && column < 6) {
						return true;
					}
			        return false;
			    }
			};

			// Get all column names from meta data and add columns to table model
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				tableModel.addColumn(metaData.getColumnLabel(columnIndex));
			}

			PopulateTable populateTable = new PopulateTable();

			tableModel = populateTable.getRowsFromResultSet(rs, tableModel);
			
			JScrollPane jScrollPane = new javax.swing.JScrollPane();
			JTable table = new JTable();

			table.setModel(tableModel);

			table.setRowHeight(48);
			table.getModel().addTableModelListener(this);

			// Ensure that "user_file" column gets its custom image renderer
			TableColumn userFileColumn = table.getColumnModel().getColumn(6);
			userFileColumn.setCellRenderer(new ImageRenderer());

			jScrollPane.setViewportView(table);

			getContentPane().add(jScrollPane, BorderLayout.CENTER);

			pack();

			setSize(900, 900);

			EventQueue.invokeLater(() -> {
				setVisible(true);
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
	    int col = e.getColumn();
	    DefaultTableModel model = (DefaultTableModel) e.getSource();
	    String column = model.getColumnName(col);
	    Object data = model.getValueAt(row, col);
	    Object id = model.getValueAt(row, 0);
	    ConnectionDB connectionDB = new ConnectionDB();
	    boolean result = connectionDB.updateRecord(column, data, id);
	    if(result) {
    		JOptionPane.showMessageDialog(this, "DB update Successful.",
				"Notification", JOptionPane.PLAIN_MESSAGE);
    	}else {
    		JOptionPane.showMessageDialog(this, "Error in DB update!",
				"Notification", JOptionPane.PLAIN_MESSAGE);
    	}
	}
}
