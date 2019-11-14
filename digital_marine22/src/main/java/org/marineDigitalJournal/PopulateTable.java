package org.marineDigitalJournal;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class PopulateTable {

    private static final Logger LOG = Logger.getLogger(PopulateTable.class.getName());
    DefaultTableModel tableModel = null;

    public JTable resultSetToTableModel(ResultSet rs, JTable table) throws SQLException {

//        try {
//            //Create new table model
//            tableModel = new DefaultTableModel(); //{
//            /* @Override
//             public Class<?> getColumnClass(int column) {
//                 if (column==6) return ImageIcon.class;
//                 return Object.class;
//             }
//        	
//         };
//             */
//
//            //Retrieve meta data from ResultSet
//            ResultSetMetaData metaData = rs.getMetaData();
//
//            //Get number of columns from meta data
//            int columnCount = metaData.getColumnCount();
//
//            //Get all column names from meta data and add columns to table model
//            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
//                tableModel.addColumn(metaData.getColumnLabel(columnIndex));
//            }
//
//            //Create array of Objects with size of column count from meta data
//            Object[] row = new Object[columnCount];
//
//            //Scroll through result set
//            while (rs.next()) {
//                //Get object from column with specific index of result set to array of objects
//                for (int i = 0; i < columnCount; i++) {
//                    int columnIndex = i + 1;
//
//                    //column 7 ("user_file") has blobs
//                    //So let's deal with it a little bit differently
//                    if (columnIndex == 7) {
//                        //Retrieve the binary stream from column 7
//                        //using try-with-resources so that the stream
//                        //gets closed automatically
//                        try (InputStream inputStream = rs.getBinaryStream(columnIndex)) {
//                            row[i] = ImageIO.read(inputStream);
//                        }
//                    } else {
//                        row[i] = rs.getObject(columnIndex);
//                    }
//                }
//
//                //Now add row to table model with that array of objects as an argument
//                tableModel.addRow(row);
//            }
//        } catch (IOException | SQLException e) {
//            LOG.log(Level.SEVERE, null, e);
//        }
//        //Now add that table model to your table and you are done :D
//        table.setModel(model);
//        //Ensure that "user_file" column gets its custom image renderer
//        TableColumn userFileColumn = table.getColumnModel().getColumn(7);
//        userFileColumn.setCellRenderer(new ImageRenderer());
//        
//        return table;

        //Create a swing worker that will do all the
        //hard work of pulling values from database
        //in a background thread
        SwingWorker<DefaultTableModel, Void> worker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() throws Exception {
                //Create new table model
                tableModel = new DefaultTableModel();

                //Retrieve meta data from ResultSet
                ResultSetMetaData metaData = rs.getMetaData();

                //Get number of columns from meta data
                int columnCount = metaData.getColumnCount();

                //Get all column names from meta data and add columns to table model
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    tableModel.addColumn(metaData.getColumnLabel(columnIndex));
                }

                //Create array of Objects with size of column count from meta data
                Object[] row = new Object[columnCount];

                //Scroll through result set
                while (rs.next()) {
                    //Get object from column with specific index of result set to array of objects
                    for (int i = 0; i < columnCount; i++) {
                        int columnIndex = i + 1;

                        //column 7 ("user_file") has blobs
                        //So let's deal with it a little bit differently
                        if (columnIndex == 7) {
                            //Retrieve the binary stream from column 7
                            //using try-with-resources so that the stream
                            //gets closed automatically
                            try (InputStream inputStream = rs.getBinaryStream(columnIndex)) {
                                row[i] = ImageIO.read(inputStream);
                            }
                        } else {
                            row[i] = rs.getObject(columnIndex);
                        }
                    }

                    //Now add row to table model with that array of objects as an argument
                    tableModel.addRow(row);
                }

                return tableModel;
            }
        };

        //Listen to whether the swing worker
        //has finished reading values from database
        worker.addPropertyChangeListener(evt -> {
            //Only continue if the thread has
            //finished its tasks
            if (evt.getPropertyName().equals("state")
                    && evt.getNewValue() == StateValue.DONE) {

                DefaultTableModel model = null;

                //Attempt to retrieve the values that
                //the swing worker pulled from database
                try {
                    model = worker.get();
                } catch (InterruptedException | ExecutionException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }

                //Only continue if the swing worker
                //did not encounter any errors; e.g., SQLExceptions
                if (model != null) {
                    //Now add that table model to your table and you are done :D
                    table.setModel(model);
                    //Ensure that "user_file" column gets its custom image renderer
                    TableColumn userFileColumn = table.getColumnModel().getColumn(7);
                    userFileColumn.setCellRenderer(new ImageRenderer());
                }
            }
        });

        //Run the swing worker in a background thread
        worker.execute();

        //You can now return the table
        return table;
    }

    /**
     * A custom renderer that paints images on its column cells.
     */
    private class ImageRenderer extends DefaultTableCellRenderer {

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
            //if (column == 7 && value instanceof BufferedImage) {
        	
        	if(column == 6 && value != null && value instanceof BufferedImage) {
                BufferedImage image = (BufferedImage) value;
                JLabel label = new JLabel(new ImageIcon(image));
                //Try to handle labels that might become too big
                label.setPreferredSize(new Dimension(18, 18));
                label.setMaximumSize(new Dimension(18, 18));
                return label;
            }
            //If we're nor dealing with column 7
            //then let the table decide on its own
            //what it should use to render the value
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }

    }
    
    
    
    public Object[] getRowsFromResultSet(ResultSet rs) throws SQLException {
    	//Retrieve meta data from ResultSet
    	ResultSetMetaData metaData = rs.getMetaData();
    	//Get number of columns from meta data
    	int columnCount = metaData.getColumnCount();
    	//Create array of Objects with size of column count from meta data
    	Object[] rows = new Object[columnCount];
    	//Object[][] rows1 = new Object[columnCount][rs.getFetchSize()];
    	
    	
    	//Scroll through result set
    	while (rs.next()) {
    	//Get object from column with specific index
    	//of result set to array of objects
    		System.out.println("checkDB"+rs.getString("user_name"));
    	for (int i = 0; i < columnCount; i++) {
    	int columnIndex = i + 1;
    	//column 7 ("user_file") has blobs
    	//So let's deal with it a little bit differently
    	if (columnIndex == 7) {
    	//Retrieve the binary stream from column 7
    	//using try-with-resources so that the stream
    	//gets closed automatically
    	try (InputStream inputStream = rs.getBinaryStream(columnIndex)) {
    	//rows[i] = ImageIO.read(inputStream);
    		
    		rows[i] = inputStream != null ? ImageIO.read(inputStream): null;
    	
    		//rows1[i][0]=rows;
    	
    	} catch (IOException ex) {
    	Logger.getLogger(PopulateTable.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	} else {
    	rows[i] = rs.getObject(columnIndex);
    	}
    	}
    	}

    	return rows;
    	}



	public DefaultTableModel getRowsFromResultSet(ResultSet rs, DefaultTableModel tableModel2) throws SQLException {
		// TODO Auto-generated method stub
		//method added by adnan
		//Retrieve meta data from ResultSet
    	ResultSetMetaData metaData = rs.getMetaData();
    	//Get number of columns from meta data
    	int columnCount = metaData.getColumnCount();
    	//Create array of Objects with size of column count from meta data
    	Object[] rows = new Object[columnCount];
    	//Object[][] rows1 = new Object[columnCount][rs.getFetchSize()];
    	
    	
    	//Scroll through result set
    	while (rs.next()) {
    	//Get object from column with specific index
    	//of result set to array of objects
    		System.out.println("checkDB"+rs.getString("user_name"));
    	for (int i = 0; i < columnCount; i++) {
    	int columnIndex = i + 1;
    	//column 7 ("user_file") has blobs
    	//So let's deal with it a little bit differently
    	if (columnIndex == 7) {
    	//Retrieve the binary stream from column 7
    	//using try-with-resources so that the stream
    	//gets closed automatically
    	try (InputStream inputStream = rs.getBinaryStream(columnIndex)) {
    	//rows[i] = ImageIO.read(inputStream);
    		
    		rows[i] = inputStream != null ? ImageIO.read(inputStream): null;
    	
    		//rows1[i][0]=rows;
    	
    	} catch (IOException ex) {
    	Logger.getLogger(PopulateTable.class.getName()).log(Level.SEVERE, null, ex);
    	}
    	} else {
    	rows[i] = rs.getObject(columnIndex);
    	}
    	}
    	tableModel2.addRow(rows);
    	}

		return tableModel2;
	}

}
