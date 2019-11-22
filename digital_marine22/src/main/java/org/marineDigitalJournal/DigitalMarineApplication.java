package org.marineDigitalJournal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository.ConnectionDB;

import org.marineDigitalJournal.neuralnet.DisplayData;
import org.marineDigitalJournal.neuralnet.DisplayData_Thread;
import org.marineDigitalJournal.neuralnet.Display_Product_Thread;
import org.marineDigitalJournal.neuralnet.Downloadind_Sat_NRT_Data;
import org.marineDigitalJournal.neuralnet.TrainModel;
import org.marineDigitalJournal.neuralnet.Trainning_Model;
import org.marineDigitalJournal.presentation.swing.BackgroundImage;
import org.marineDigitalJournal.presentation.swing.BackgroundImage_Product;
import org.marineDigitalJournal.presentation.swing.BackgroundImagePredictions_high_CHL_locations;
import org.marineDigitalJournal.presentation.swing.BackgroundImageCHL_predictions_next_day;
import org.marineDigitalJournal.presentation.swing.NextPage;
import org.marineDigitalJournal.presentation.swing.Register;


public class DigitalMarineApplication extends JFrame implements ActionListener{

	public static String limit_CHL = "";

	static JFrame f = new JFrame("Blue Maritime Digital Journal App ");
	
	static private JButton predictions_high_CHL_locations_button, chl_predictions_next_day_button;
	static private JButton database_button, register_button;
	static private JButton funding_button, about_button;
	static private JButton maps_button, exit_button;
	
	static private JMenuItem predictions_high_CHL_locations, chl_predictions_next_day;
	static private JMenuItem  collect, database, about, myAccount;
	static private JMenuItem funding, exit, trainModel, download;

	public DigitalMarineApplication() {

		try {
			initComponents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addMenu();
		addButtons();

		BackgroundImage backgroundImage = new BackgroundImage();

		f.add(backgroundImage, BorderLayout.CENTER);

	}

	private void initComponents() throws IOException {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		setTitle("File");

		pack();

		Display_Product_Thread displayProduct_Thread = new Display_Product_Thread();
		displayProduct_Thread.launch();

		Downloadind_Sat_NRT_Data downloadind_Sat_NRT_Data = new Downloadind_Sat_NRT_Data();

		downloadind_Sat_NRT_Data.launch();

		DisplayData_Thread displayData_Thread = new DisplayData_Thread();
		 displayData_Thread.launch();

		
		/*
		 * try { DisplayData_Thread.sleep(2000); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

		Trainning_Model trainning_Model = new Trainning_Model();

		trainning_Model.launch();
	}
	
	private void addButtons() {
		/* Add buttons */
		int x = 50, y = 110, thick = 25;
		Color font_color= Color.BLUE;
		predictions_high_CHL_locations_button = new JButton("Predictions high CHL locations high biomass blooms"); 
		predictions_high_CHL_locations_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		predictions_high_CHL_locations_button.setForeground(font_color);
		predictions_high_CHL_locations_button.setSize(400, thick); 
		predictions_high_CHL_locations_button.setLocation(x , y);
		predictions_high_CHL_locations_button.addActionListener(this);
		f.add(predictions_high_CHL_locations_button);
		
		y += 50;
		chl_predictions_next_day_button = new JButton("CHL predictions next day high biomass blooms"); 
		chl_predictions_next_day_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		chl_predictions_next_day_button.setForeground(font_color);
		chl_predictions_next_day_button.setSize(400, thick); 
		chl_predictions_next_day_button.setLocation(x , y); 
		chl_predictions_next_day_button.addActionListener(this);
		f.add(chl_predictions_next_day_button);
		
		
		y += 50;
		maps_button = new JButton("Maps"); 
		maps_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		maps_button.setForeground(font_color);
		maps_button.setSize(400, thick); 
		maps_button.setLocation(x , y);
		maps_button.addActionListener(this);
		f.add(maps_button);
		
		y += 50;
		register_button = new JButton("Collect"); 
		register_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		register_button.setForeground(font_color);
		register_button.setSize(400, thick); 
		register_button.setLocation(x , y); 
		register_button.addActionListener(this);
		f.add(register_button);
		
		y += 50;
		database_button = new JButton("Database"); 
		database_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		database_button.setForeground(font_color);
		database_button.setSize(400, thick); 
		database_button.setLocation(x , y); 
		database_button.addActionListener(this);
		f.add(database_button);
		
		y += 50;
		funding_button = new JButton("Funding"); 
		funding_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		funding_button.setForeground(font_color);
		funding_button.setSize(400, thick); 
		funding_button.setLocation(x , y); 
		funding_button.addActionListener(this);
		f.add(funding_button);
		
		y += 50;
		about_button = new JButton("About"); 
		about_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		about_button.setForeground(font_color);
		about_button.setSize(400, thick); 
		about_button.setLocation(x , y); 
		about_button.addActionListener(this);
		f.add(about_button);
		
		y += 50;
		exit_button = new JButton("Exit"); 
		exit_button.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		exit_button.setForeground(font_color);
		exit_button.setSize(400, thick); 
		exit_button.setLocation(x , y); 
		exit_button.addActionListener(this);
		f.add(exit_button);
	}
	
	private void addMenu() {
		JMenu menu;
		
		f.getContentPane().setBackground(Color.WHITE);

		JMenuBar mb = new JMenuBar();

		menu = new JMenu("Home");

		JLabel jLabel = new JLabel("Blue Maritime Digital Journal App");

		f.add(jLabel);

		predictions_high_CHL_locations = new JMenuItem("Predictions high CHL locations high biomass blooms");
		chl_predictions_next_day = new JMenuItem("CHL predictions next day high biomass blooms");
		collect = new JMenuItem("Registration");
		database = new JMenuItem("Database");

		about = new JMenuItem("About");
		funding = new JMenuItem("Funding");
		myAccount = new JMenuItem("MyAccount");
		exit = new JMenuItem("Exit");

		menu.add(predictions_high_CHL_locations).addActionListener(this);

		menu.add(chl_predictions_next_day).addActionListener(this);

		menu.add("Maps").addActionListener(this);
		
		menu.add(collect).addActionListener(this);

		menu.add(database).addActionListener(this);
		
		menu.add(about).addActionListener(this);
		
		menu.add(funding).addActionListener(this);

		exit.addActionListener(this);

		menu.add(exit);

		mb.add(menu);

		f.setJMenuBar(mb);
		
		
	}

    // method actionPerformed() 
    // to get the action performed 
    // by the user and act accordingly 
    public void actionPerformed(ActionEvent ev) 
    { 
    	//System.out.println(ev.getSource());
    	if (ev.getActionCommand().equals("Exit")){
    		System.exit(0);
    	}else if (ev.getActionCommand().equals("Funding")) {

				NextPage page = new NextPage();

				page.setTitle("Funding");

				JLabel jLabel = new JLabel();

				String text = "<HTML><BODY style='marging-left:100 marging-right:100; padding:100'><font size = 5 color=black>This application has been developed within the EOVALUE project, \n"
						+ "which has received funding from the European Union’s Horizon 2020 research and innovation programme.\n"
						+ "The JRC, or as the case may be the European Commission, shall not be held liable for any direct or indirect,\n"
						+ "incidental, consequential or other damages, including but not limited to the loss of data, loss of profits, \n"
						+ "or any other financial loss arising from the use of this application, or inability to use it, even if the JRC \n"
						+ "is notified of the possibility of such damage. <BR></font></BODY></HTML>";

				jLabel.setText(text);

				page.setBounds(300, 200, 800, 500);

				page.add(jLabel);

				page.getContentPane().setBackground(Color.lightGray);

				page.setSize(700, 700);

				page.setBounds(100, 500, 800, 800);

				page.setVisible(true);

			}
    	else if (ev.getActionCommand().equals("About")) {

				NextPage page = new NextPage();

				page.setTitle("About");
				JLabel jLabel = new JLabel();

				String test = "<HTML><BODY style='marging-left:100 marging-right:100; padding:100'><font size= 4 color=black>Marine earth observation satellites continuously observe the dynamics of the marine ecosystems variables \n"
						+ "like surface temperature, salinity, anomalies in the surface of the ocean, Chlorophyll-a or plankton concentration. This data is delivered to users \n"
						+ "providing a source of valuable information to environmental experts, scientists and citizens.\n"
						+ "CMEMS and Copernicus Satellite data on maritime change are freely available on global level and are integrated in the open data initiative of the \n"
						+ "European Commission and the Group on Earth Observation (GEO).\n"
						+ "The Blue Marine Digital Journal App is part of the EOVALUE project on EO based innovative apps in environmental and social domains and addresses \n"
						+ "the identification and prediction of maritime hazards and automated alerts to inform journalists and general users on near-real-time, adding \n"
						+ "informative transparency to the general population.\n"
						+ "The proposed system uses artificial intelligence algorithms feeded by Copernicus Marine Environmental Monitoring System data which has been validated as optimal by CMEMS.\n"
						+ "Users can monitor near-real-time maritime dynamics like concentration of Chlorophyll at an European dimension supporting the early identification of Harmful Algal Blooms.\n"
						+ "The data used is retrieved from Sentinel 3 A|B, other satellites data is provided b the Copernicus Marine Environmental Monitoring System \n"
						+ "Blue Marine Digital Journal app also integrates crowdsourcing functionalities for maritime hazards observations by the \n"
						+ "individual user."
						+ "Users of Blue Marine Digital Journal app make use and support the maritime observation services of the European Union’s Earth monitoring programme Copernicus.  \n"
						+ "The application allows the use machine learning for early detection of strong concentrations of Chlorophyll-a/high-biomass blooms and potentially HABs for predicting \n"
						+ "and monitoring its occurrence.\n"
						+ " With the app users can participate in building and establishing an in-situ database for maritime hazards information and compare their individual observations with \n"
						+ "satellite measurements. The user can explore marine maps of European marine dynamics.\n"
						+ "The alpha version of the application was designed and developed by Isaac Dura founder of the award-winning European Space Agency Business Incubator Center Darmstadt startup HeraSpace. \n"
						+ "The satellite data provided by the Copernicus Marine Environmental monitoring system (CMEMS) containing merged sources from ESA and NASA.</font></BODY></HTML>";

				jLabel.setText(test);

				page.setBounds(300, 200, 800, 500);

				page.add(jLabel);

				page.getContentPane().setBackground(Color.lightGray);

				page.setSize(700, 700);

				page.setBounds(100, 500, 800, 800);

				page.setVisible(true);

			} 
    	else if (ev.getActionCommand().equals("Database")) {

				try {

					Connection connection = null;
					PreparedStatement st = null;

					ConnectionDB connectionDB = new ConnectionDB();

					ResultSet rs = connectionDB.conectionUpdate(connection, st);

					// Retrieve meta data from ResultSet
					ResultSetMetaData metaData = rs.getMetaData();

					// Get number of columns from meta data
					int columnCount = metaData.getColumnCount();

					DefaultTableModel tableModel = new DefaultTableModel();

					// Get all column names from meta data and add columns to table model
					for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						tableModel.addColumn(metaData.getColumnLabel(columnIndex));
					}

					PopulateTable populateTable = new PopulateTable();

					// for(int s=0;s<rs.getFetchSize();s++) {

					// Object[] rows = populateTable.getRowsFromResultSet(rs); // changed adnan
					tableModel = populateTable.getRowsFromResultSet(rs, tableModel);
					// }

					// tableModel.addRow(rows);

					JScrollPane jScrollPane = new javax.swing.JScrollPane();
					JTable table = new JTable();

					table.setModel(tableModel);

					table.setRowHeight(48);

					// Ensure that "user_file" column gets its custom image renderer
					TableColumn userFileColumn = table.getColumnModel().getColumn(6);
					userFileColumn.setCellRenderer(new ImageRenderer());

					jScrollPane.setViewportView(table);

					final JFrame frame = new JFrame();

					frame.getContentPane().add(jScrollPane, BorderLayout.CENTER);

					frame.pack();

					frame.setSize(900, 900);

					EventQueue.invokeLater(() -> {
						frame.setVisible(true);
					});

				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println(e.getStackTrace());
				}

			}
    	else if (ev.getActionCommand().equals("Predictions high CHL locations high biomass blooms")) {

			NextPage page = new NextPage();
			try {

				BackgroundImagePredictions_high_CHL_locations backgroundImage = new BackgroundImagePredictions_high_CHL_locations();

				page.add(backgroundImage, BorderLayout.CENTER);

				JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(
						"/marineDigitalJournal/presentation/swing/predictions_high_CHL_locations.png"))));

				background.setLayout(new BorderLayout());

				page.setContentPane(background);

			} catch (IOException E) {
				// TODO Auto-generated catch block
				E.printStackTrace();
			}

			page.setTitle("Predictions high CHL locations high biomass blooms");
			page.getContentPane().setBackground(Color.WHITE);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			page.setSize(screenSize.width, screenSize.height);

			// page.setBounds(100, 500, 800, 800);

			page.setVisible(true);

		} else if (ev.getActionCommand().equals("CHL predictions next day high biomass blooms")) {

			NextPage page = new NextPage();
			try {

				BackgroundImageCHL_predictions_next_day backgroundImage = new BackgroundImageCHL_predictions_next_day();

				page.add(backgroundImage, BorderLayout.CENTER);

				JLabel background = new JLabel(new ImageIcon(ImageIO.read(
						new File("/marineDigitalJournal/presentation/swing/CHL_predictions_next_day.png"))));

				background.setLayout(new BorderLayout());

				page.setContentPane(background);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			page.setTitle("CHL predictions next day");
			page.getContentPane().setBackground(Color.WHITE);

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			page.setSize(screenSize.width, screenSize.height);

			// page.setBounds(800, 800, 800, 800);

			page.setVisible(true);

		} else if (ev.getActionCommand().equals("Maps")) {

				NextPage page = new NextPage();
				try {

					BackgroundImage_Product backgroundImage = new BackgroundImage_Product();

					page.add(backgroundImage, BorderLayout.CENTER);

					JLabel background = new JLabel(new ImageIcon(
							ImageIO.read(new File("/marineDigitalJournal/presentation/swing/swing/results.png"))));

					background.setLayout(new BorderLayout());

					page.setContentPane(background);

					/*
					 * page.setContentPane(new JLabel(new ImageIcon( ImageIO.read(new
					 * File("//marineDigitalJournal//presentation//swing//results.png")))));
					 */
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*
				 * JLabel jLabel = new JLabel();
				 * 
				 * jLabel.setText("CMEMS");
				 * 
				 * page.add(jLabel);
				 */

				page.setTitle("Maps");
				page.getContentPane().setBackground(Color.WHITE);

				page.setTitle("Predictions high CHL locations");
				page.getContentPane().setBackground(Color.WHITE);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				page.setSize(screenSize.width, screenSize.height);

				page.setVisible(true);

			}
    	else if (ev.getActionCommand().equals("Collect")) {
        	Register f = new Register();
        }else if(ev.getActionCommand().equals("Registration")) {
        	Register f = new Register();
        }
    }
    
	public static void main(String[] args) throws IOException, MessagingException {

		final JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		String forecast = "";
		// System.out.print("cdcadca" + limit_CHL + "erfvwewewevwevwe");

		new DigitalMarineApplication().setVisible(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		f.setSize(screenSize.width, screenSize.height);

		f.setLayout(null);

		f.setVisible(true);

		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		DigitalMarineApplication gui = new DigitalMarineApplication();

		try {

			JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("marineDigitalJournal/mainImage.jpg"))));

			background.setLayout(new BorderLayout());

			gui.setContentPane(background);

			gui.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("marineDigitalJournal/mainImage.jpg")))));

		} catch (IOException e) {
			System.out.println(e.getCause());

		}

	}

}
