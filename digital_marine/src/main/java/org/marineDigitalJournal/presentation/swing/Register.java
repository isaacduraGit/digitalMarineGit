package org.marineDigitalJournal.presentation.swing;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository.ConnectionDB;
import java.awt.*; 
import java.awt.event.*; 
import java.io.File;
  
/**
 * Class to populate Swing frame for registration.
 */
public class Register 
    extends JFrame 
    implements ActionListener { 
  
    // Components of the Form 
    private Container c; 
    private JLabel title; 
    private JLabel name, sname, lag, lat; 
    private JTextField tname, tsname, temail, tlag, tlat; 
    private JLabel mno; 
    private JTextField tmno; 
    private JLabel email, file;
    private JCheckBox term; 
    private JButton sub, con, attach; 
    private JButton reset;  
    private JLabel res;
    final JFileChooser fileChooser;
  
    // constructor, to initialize the components 
    // with default values. 
    public Register() 
    { 
    	int x=100, y =100;
        setTitle("Data Collection Form"); 
        setBounds(300, 50, 550, 680); 
        setResizable(false); 
  
        c = getContentPane(); 
        c.setLayout(null); 
  
        title = new JLabel("Collect Data"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 40); 
        title.setLocation(100, 30); 
        c.add(title); 
  
        name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20)); 
        name.setSize(100, 20); 
        name.setLocation(x, y); 
        c.add(name); 
  
        tname = new JTextField(); 
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 20); 
        tname.setLocation(x + 100, y); 
        c.add(tname); 
        
        y += 50;

        sname = new JLabel("Surname"); 
        sname.setFont(new Font("Arial", Font.PLAIN, 20)); 
        sname.setSize(100, 20); 
        sname.setLocation(x, y); 
        c.add(sname); 
  
        tsname = new JTextField(); 
        tsname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tsname.setSize(190, 20); 
        tsname.setLocation(x + 100, y); 
        c.add(tsname);
    
        y += 50;
        mno = new JLabel("Phone"); 
        mno.setFont(new Font("Arial", Font.PLAIN, 20)); 
        mno.setSize(100, 20); 
        mno.setLocation(x, y); 
        c.add(mno); 
  
        tmno = new JTextField(); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tmno.setSize(190, 20); 
        tmno.setLocation(x + 100, y); 
        c.add(tmno); 
  
        y += 50;
        email = new JLabel("Email *"); 
        email.setFont(new Font("Arial", Font.PLAIN, 20)); 
        email.setSize(100, 20); 
        email.setLocation(x, y); 
        c.add(email); 
  
        temail = new JTextField(); 
        temail.setFont(new Font("Arial", Font.PLAIN, 15)); 
        temail.setSize(190, 20); 
        temail.setLocation(x + 100, y); 
        c.add(temail);

        y += 50;
        lag = new JLabel("Longitude"); 
        lag.setFont(new Font("Arial", Font.PLAIN, 20)); 
        lag.setSize(100, 20); 
        lag.setLocation(x, y); 
        c.add(lag); 
  
        tlag = new JTextField(); 
        tlag.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tlag.setSize(190, 20); 
        tlag.setLocation(x + 100, y); 
        c.add(tlag);

        y += 50;
        lat = new JLabel("Latitude"); 
        lat.setFont(new Font("Arial", Font.PLAIN, 20)); 
        lat.setSize(100, 20); 
        lat.setLocation(x, y); 
        c.add(lat); 
  
        tlat = new JTextField(); 
        tlat.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tlat.setSize(190, 20); 
        tlat.setLocation(x + 100, y); 
        c.add(tlat);
        
        y += 50;
        file = new JLabel("Photo"); 
        file.setFont(new Font("Arial", Font.PLAIN, 20)); 
        file.setSize(100, 20); 
        file.setLocation(x, y); 
        c.add(file);
        
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        attach = new JButton("Attach"); 
        attach.setFont(new Font("Arial", Font.PLAIN, 15)); 
        attach.setSize(100, 23); 
        attach.setLocation(x + 100, y); 
        attach.addActionListener(new ActionListener() {

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
        c.add(attach);
        
        y += 50;
        term = new JCheckBox("Personal Data Processing Consent"); 
        term.setFont(new Font("Arial", Font.PLAIN, 15)); 
        term.setSize(350, 20); 
        term.setLocation(x, y); 
        c.add(term); 
        
        y += 50;
        con = new JButton("Read Data Processing Terms"); 
        con.setFont(new Font("Arial", Font.PLAIN, 15)); 
        con.setSize(300, 23); 
        con.setLocation(x, y); 
        con.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {

				if (ev.getActionCommand().equals("Read Data Processing Terms")) {

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
        c.add(con);
  
        y += 50;
        sub = new JButton("Submit"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 23); 
        sub.setLocation(x + 50, y); 
        sub.addActionListener(this); 
        c.add(sub); 
  
        reset = new JButton("Reset"); 
        reset.setFont(new Font("Arial", Font.PLAIN, 15)); 
        reset.setSize(100, 23); 
        reset.setLocation(270, y); 
        reset.addActionListener(this); 
        c.add(reset);
  
        y += 50;
        res = new JLabel(""); 
        res.setFont(new Font("Arial", Font.PLAIN, 20)); 
        res.setForeground(Color.RED);
        res.setSize(500, 25); 
        res.setLocation(x - 50, y); 
        c.add(res);
  
        setVisible(true); 
    } 
  
    // method actionPerformed() 
    // to get the action performed 
    // by the user and act accordingly 
    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == sub) { 
            if (term.isSelected()) {
            	String user_name = tname.getText();
            	String user_surname = tsname.getText();
            	String user_email = temail.getText();
            	String user_phone = tmno.getText();
            	String user_comments = "";
            	String user_latitude = tlat.getText();
            	String user_langitude = tlag.getText();
            	
            	File user_file = fileChooser.getSelectedFile();
            	if (user_file!= null)
            	if(!user_file.exists()) {
            		System.out.print("File doesn't exist");
            		user_file = null;
            	}
            	
            	if (user_email.isEmpty() )
            	{
            		res.setText("Email is mandatory!");
            		return;
            	}
            	
            	ConnectionDB connectionDB = new ConnectionDB();
            	
            	boolean result = connectionDB.conectionInsert(user_name, user_surname, user_email,
					user_phone, user_comments, user_file);
            	
            	if(result) {
            		JOptionPane.showMessageDialog(c, "Registration Successful.",
						"Notification", JOptionPane.PLAIN_MESSAGE);
            		resetForm();
            	}else {
            		JOptionPane.showMessageDialog(c, "Error in Registration!",
						"Notification", JOptionPane.PLAIN_MESSAGE);
            		res.setText("Please correct error"
                            + " & try again.");
            	}} 
            	else {
            		res.setText("Please accept the"
                            + " Personal Data Processing Consent"); 
            } 
        } 
  
        else if (e.getSource() == reset) { 
        	resetForm();
        } 
    }
    
    private void resetForm() {
    	String def = ""; 
        tname.setText(def); 
        temail.setText(def); 
        tsname.setText(def); 
        res.setText(def);
        term.setSelected(false);
        tmno.setText(def);
        tlag.setText(def);
        tlat.setText(def);
    }
} 