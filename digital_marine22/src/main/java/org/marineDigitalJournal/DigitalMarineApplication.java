package org.marineDigitalJournal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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

public class DigitalMarineApplication extends javax.swing.JFrame {

	public static String limit_CHL = "";

	static JFrame f = new JFrame("Blue Maritime Digital Journal App ");

	public DigitalMarineApplication() {

		try {
			initComponents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public static void main(String[] args) throws IOException, MessagingException {

		JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		String forecast = "";
		// System.out.print("cdcadca" + limit_CHL + "erfvwewewevwevwe");

		new DigitalMarineApplication().setVisible(true);

		JMenu menu;

		JMenuItem predictions_high_CHL_locations, chl_predictions_next_day, collect, database, about, myAccount,
				funding, exit, trainModel, download;

		f.getContentPane().setBackground(Color.WHITE);

		JMenuBar mb = new JMenuBar();

		menu = new JMenu("Home");

		JLabel jLabel = new JLabel("Blue Maritime Digital Journal App");

		f.add(jLabel);

		predictions_high_CHL_locations = new JMenuItem("Predictions_high_CHL_locations-high-biomass_blooms");
		chl_predictions_next_day = new JMenuItem("CHL_predictions_next_day-high-biomass_blooms");
		collect = new JMenuItem("Collect");
		database = new JMenuItem("Database");

		about = new JMenuItem("About");
		funding = new JMenuItem("Funding");
		myAccount = new JMenuItem("MyAccount");
		exit = new JMenuItem("Exit");

		menu.add(predictions_high_CHL_locations).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("Predictions_high_CHL_locations-high-biomass_blooms")) {

					NextPage page = new NextPage();
					try {

						BackgroundImagePredictions_high_CHL_locations backgroundImage = new BackgroundImagePredictions_high_CHL_locations();

						page.add(backgroundImage, BorderLayout.CENTER);

						JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File(
								"/marineDigitalJournal/presentation/swing/predictions_high_CHL_locations.png"))));

						background.setLayout(new BorderLayout());

						page.setContentPane(background);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					page.setTitle("Predictions_high_CHL_locations-high-biomass blooms");
					page.getContentPane().setBackground(Color.WHITE);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					page.setSize(screenSize.width, screenSize.height);

					// page.setBounds(100, 500, 800, 800);

					page.setVisible(true);

				}

			}
		});
		;

		menu.add(chl_predictions_next_day).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("CHL_predictions_next_day-high-biomass_blooms")) {

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

					page.setTitle("CHL_predictions_next_day");
					page.getContentPane().setBackground(Color.WHITE);

					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					page.setSize(screenSize.width, screenSize.height);

					// page.setBounds(800, 800, 800, 800);

					page.setVisible(true);

				}

			}
		});
		;

		menu.add("Maps").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("Maps")) {

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

					page.setTitle("Predictions_high_CHL_locations");
					page.getContentPane().setBackground(Color.WHITE);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					page.setSize(screenSize.width, screenSize.height);

					page.setVisible(true);

				}

			}
		});

		menu.add(collect).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("Collect")) {

					NextPage page = new NextPage();

					JLabel jLabel = new JLabel();

					jLabel.setText("");

					page.add(jLabel);

					page.setTitle("Collect");
					page.getContentPane().setBackground(Color.lightGray);

					page.setSize(700, 700);

					page.setBounds(100, 500, 800, 800);

					JLabel name, surname, email, phone, comments, file;
					JTextField nameText, surnameText, emailText = new JTextField(), phoneText;
					JTextArea commentsArea;

					name = new JLabel("Name:");
					name.setBounds(50, 70, 100, 30);

					nameText = new JTextField();
					nameText.setBounds(120, 70, 100, 30);

					page.add(name);
					page.add(nameText);

					surname = new JLabel("Surname:");
					surname.setBounds(50, 110, 100, 30);

					surnameText = new JTextField();
					surnameText.setBounds(120, 110, 100, 30);

					page.add(surname);
					page.add(surnameText);

					email = new JLabel("Email:");
					email.setBounds(50, 150, 100, 30);

					page.add(email);

					page.add(emailText);

					emailText.setBounds(120, 150, 100, 30);

					phone = new JLabel("Phone:");
					phone.setBounds(50, 190, 100, 30);

					phoneText = new JTextField();
					phoneText.setBounds(120, 190, 100, 30);

					page.add(phone);
					page.add(phoneText);

					//comments = new JLabel("Comments:");
					//comments.setBounds(50, 230, 100, 30);

					Border border = BorderFactory.createLineBorder(Color.BLACK);

					commentsArea = new JTextArea();
					commentsArea.setBorder(BorderFactory.createCompoundBorder(border,
							BorderFactory.createEmptyBorder(10, 10, 10, 10)));

					//page.add(comments);
					page.add(commentsArea);

					// JCheckBox jCheckBox = new JCheckBox("Personal Data Processing Consent");

					JButton button = new JButton("Personal Data Processing Consent");

					button.setBounds(50, 450, 500, 30);

					button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent ev) {

							if (ev.getActionCommand().equals("Personal Data Processing Consent")) {

								NextPage page = new NextPage();

								page.setTitle("Privacy Data terms");
								JLabel jLabel = new JLabel();

								String test = "<HTML><BODY style='marging-left:100 marging-right:100; padding:100'> <font color=black> OFFIDOCS ONLINE PRIVACY POLICY\n" + " \n"
										+ "\n"
										+ "This privacy policy describes how we treat personal information and data when you use the OffiDocs online web applications.\n"
										+ "\n"
										+ "OffiDocs is committed to protecting your privacy when you visit our web-site and use our online or other services (\"Services\"). In this policy we explain how and why we collect your information, what we do with it and what controls you have over our use of it.\n"
										+ "\n"
										+ "In this privacy policy we describe what kind of data we may collect of you in connection with your use of our  services and how we may use such data. Below we call these collectively “Services”. By using our Services, you agree to the processing of your data in accordance with this privacy policy.\n"
										+ "\n"
										+ "We may amend this privacy policy from time to time by posting a new version online so please review it frequently. Your continued use of our Services after the posting of a new version is deemed as your acceptance of the modified terms.\n"
										+ "\n" + " \n" + "\n" + "Personal Information\n"
										+ "We do not collect personal data. When you use OffiDocs applications, no personal information is requested, so there's nothing to send to us. When you use OffiDocs applications integrated with Google Drive no information available from the account(s) you log into is sent to us at any point.\n"
										+ "\n"
										+ "We don't call Google APIs to obtain personal information. Moreover, when installing OffiDocs applications on a personal Google account we are provided with no information regarding who has completed the installation.\n"
										+ "\n"
										+ "This privacy policy contains no section on sharing of personal information, because we have none to share.\n"
										+ "\n" + " \n" + "\n" + "Data\n"
										+ "OffiDocs applications uses a remote storage created in our OffiDocs servers for each user. When the Google Drive integration is used, the data travels through our OffiDocs and Google Drive servers when saving and loading. The user can clear the data from our servers using the OffiDocs file manager, which is available as a menu link in all the OffiDocs application.\n"
										+ "\n" + " \n" + "\n" + "Cookies\n"
										+ "We may send one or more cookies to your computer or other device. We may use cookies to improve the quality of our service, including for storing user preferences, improving search results and ad selection, and tracking user trends, such as how people search. Most browsers are initially set up to accept cookies, but you can reset your browser to refuse all cookies or to indicate when a cookie is being sent. However, some features and services may not function properly if your cookies are disabled.\n"
										+ "\n" + " \n" + "Analytics data\n"
										+ "We use Google analytics because it tells us how many users we have and what they use more. You would be advised to refer to the privacy policy of Google to see what they do with the hits they receive from you to their domains. \n"
										+ "\n" + " \n" + "Ad serving\n"
										+ "We use the Google Adsense technology to provide advertisements of interest to you within our Services. Refer to the Google Adsense policy.\n"
										+ "\n" + " \n" + "\n" + "External links\n" + "We do not use external links.\n"
										+ "\n" + " \n" + "\n" + "Security\n"
										+ "We take appropriate security measures to protect against unauthorized access to or unauthorized alteration, disclosure or destruction of data. These include internal reviews of our data collection, storage and processing practices and security measures, including appropriate encryption and physical security measures to guard against unauthorized access to systems where we store personal data.\n"
										+ "\n"
										+ "If your userid does not open a session during 10 days we consider that you do not want to use more our OffiDocs services, and your data is removed from our servers. This is a security measure. \n"
										+ "\n" + " \n" + "\n" + " \n" + "\n"
										+ "Should you have any questions regarding this privacy policy or the processing of your personal data, please contact us at isaacdura@heraspace.com</font></BODY></HTML>";

								jLabel.setText(test);

								page.setBounds(300, 200, 800, 500);

								page.add(jLabel);

								page.getContentPane().setBackground(Color.lightGray);

								page.setSize(700, 700);

								page.setBounds(100, 500, 800, 800);

								page.setVisible(true);

							}

						}
					});

					// jCheckBox.setBounds(50, 280, 500, 30);

					page.add(button);

					// page.add(jCheckBox);

					file = new JLabel("File:");
					file.setBounds(50, 300, 100, 30);

					JButton jButton = new JButton("Attach");

					jButton.setBounds(50, 350, 100, 30);

					jButton.setLabel("Attach");

					jButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							if (e.getActionCommand().equals("Attach")) {

								int returnValue = fileChooser.showOpenDialog(null);

								if (returnValue == JFileChooser.APPROVE_OPTION) {

									File selectedFile = fileChooser.getSelectedFile();

									System.out.print(selectedFile.getAbsolutePath());

								}

							}

						}

					});

					JButton jButton1 = new JButton("Collect");

					jButton1.setBounds(50, 400, 100, 30);

					jButton1.setLabel("Collect");

					jButton1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							if (e.getActionCommand().equals("Collect")) {

								String user_name = nameText.getText();

								String user_surname = surnameText.getText();

								String user_email = emailText.getText();

								String user_phone = phoneText.getText();
								// Bug textarea is not visible
								String user_comments = "";

								File user_file = fileChooser.getSelectedFile();

								Connection connection = null;
								PreparedStatement st = null;

								ConnectionDB connectionDB = new ConnectionDB();

								connectionDB.conectionInsert(connection, st, user_name, user_surname, user_email,
										user_phone, user_comments, user_file);

							}
						}

					});

					page.add(jButton);

					page.add(jButton1);

					page.setSize(700, 700);

					page.setBounds(30, 30, 700, 800);

					page.setLayout(null);

					page.setVisible(true);

				}

			}
		});
		;

		menu.add(database).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("Database")) {

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

						// Object[] rows = populateTable.getRowsFromResultSet(rs); // chnaged adnan
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

			}
		});

		menu.add(about).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("About")) {

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

			}
		});

		menu.add(funding).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("Funding")) {

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

			}
		});
		;

		class CloseListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}

		}

		exit.addActionListener(new CloseListener());

		menu.add(exit);

		mb.add(menu);

		f.setJMenuBar(mb);

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
