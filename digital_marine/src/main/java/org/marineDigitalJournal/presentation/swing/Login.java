package org.marineDigitalJournal.presentation.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

import org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository.ConnectionDB;

/**
 * Class to manipulate credentials to access database.
 */
public class Login extends JFrame implements ActionListener{

	JButton blogin = new JButton("Login");
	private Container c;
	private JLabel name, pwd, res;
	JTextField txuser = new JTextField(15);
	JPasswordField pass = new JPasswordField(15);
	
	public Login(){
		super("Authentication");
		setSize(350,250);
		setLocation(500,280);
		c = getContentPane(); 
        c.setLayout(null); 
        setResizable(false); 

        int x=30, y=30;
        name = new JLabel("Username"); 
        name.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        name.setSize(100, 20); 
        name.setLocation(x, y); 
        c.add(name); 
  
        txuser = new JTextField(); 
        txuser.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        txuser.setSize(190, 20); 
        txuser.setLocation(x + 80, y); 
        c.add(txuser); 
        
        y += 50;
        pwd = new JLabel("Password"); 
        pwd.setFont(new Font("Arial", Font.PLAIN, 14)); 
        pwd.setSize(100, 20); 
        pwd.setLocation(x, y); 
        c.add(pwd); 
  
        pass.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        pass.setSize(190, 20); 
        pass.setLocation(x + 80, y); 
        c.add(pass); 
        
        y += 50;
        blogin.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        blogin.setSize(100, 23); 
        blogin.setLocation(x + 80, y);
        blogin.addActionListener(this);

        y += 40;
        res = new JLabel(""); 
        res.setFont(new Font("Tahoma", Font.PLAIN, 14)); 
        res.setForeground(Color.RED);
        res.setSize(300, 25); 
        res.setLocation(x + 50, y); 
        c.add(res);
        
		c.add(blogin);
		c.add(txuser);
		c.add(pass);
		c.add(res);

		setVisible(true);
	}
	
	private boolean authenticate(String user, String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
		    byte[] digest = md.digest();
		    String myHash = DatatypeConverter
		      .printHexBinary(digest).toUpperCase();
			ConnectionDB connectionDB = new ConnectionDB();
			return connectionDB.authenticate(user, myHash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	// method actionPerformed() 
    // to get the action performed 
    // by the user and act accordingly 
    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == blogin) {
        	String user_name = txuser.getText();
        	String user_pass = pass.getText();
        	
        	if(authenticate(user_name, user_pass)) {
        		res.setText("Success. Logging...");
        		Database db= new Database();
        		setVisible(false);
        		return;
        	}
        	else {
        		res.setText("Invalid Username or Password!");
        		return;
        	}
        }
    }
}

