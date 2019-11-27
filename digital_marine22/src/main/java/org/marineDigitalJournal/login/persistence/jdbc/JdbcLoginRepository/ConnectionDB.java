package org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jasypt.util.text.BasicTextEncryptor;

public class ConnectionDB {

	static String prop;
	static final String DB_SERVER = "ec2-18-217-120-134.us-east-2.compute.amazonaws.com"; 
	static final int DB_PORT = 3306;
	static final String DB_USER = "admin_db";
	static final String DB_PASS = "AbMEXzM75u3qGfnNBthzfcyxmBhmYL4U";

	public boolean authenticate(String user, String pwd) {
		try {
			Connection connection = this.connect();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT user, pwd FROM users");
			while (rs.next()) {
				String u = rs.getString("user");
				String p = rs.getString("pwd");
				//System.out.println(u + " " + p);
				if (user.equals(u)) {
					if(pwd.equals(p)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean updateRecord(String column, Object data, Object id) {
		String columnName = column;
        String dataVal = data.toString();
        String rowId = id.toString();
		int rs=0;
        try {
        	Connection connection = this.connect();
        	PreparedStatement st = (PreparedStatement) connection.prepareStatement(
					"update userUnit set "+columnName+" = "+dataVal+" where user_id = "+rowId);
			rs = st.executeUpdate();
			
        }catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	private String decrypt(String encodedText) {
		String decodedText;
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("digital_marine");
		
		//String myEncryptedText = textEncryptor.encrypt(DB_PASS);
		
		decodedText = textEncryptor.decrypt(encodedText);
		//System.out.println(decodedText);
		return decodedText;
	}
	/**
	   * Connect to the database
	   *
	   * @return the Connection object
	   */
	  private Connection connect() throws SQLException{
	      // Mysql connection string
	      String url = "jdbc:mysql://"+DB_SERVER+":"+ DB_PORT + "/user_collect?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	      Connection conn= null;
	      
	      String pass = decrypt(DB_PASS);
	      conn = DriverManager.getConnection(url, DB_USER, pass);
	      //conn.setAutoCommit(false);
	      return conn;
	  }
	  
	public boolean conectionInsert(String user_name, String user_surname,
			String user_email, String user_phone, String user_comments, String user_phone2, String user_comments2, File user_file) {
		Connection connection = null;
		PreparedStatement st = null;
		int rs=0;

		try {

			connection = this.connect();
			st = (PreparedStatement) connection.prepareStatement(
					"INSERT INTO userUnit (user_name,user_surname,user_email,user_phone, user_comments, user_file) VALUES (?,?,?,?,?,?)");

			st.setString(1, user_name);

			st.setString(2, user_surname);

			st.setString(3, user_email);

			st.setString(4, user_phone);

			st.setString(5, user_comments);

			
			try {
				st.setBlob(6, new FileInputStream(user_file));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			rs = st.executeUpdate();

		} catch (SQLException e1) {

			System.out.println("SQLException:" + e1);

		} finally {

			try {

				connection.close();
				st.close();

			} catch (SQLException sqlException) {
				System.out.println("SQLException Finally:" + sqlException);

			}
		}
		if (rs == 1) {
			return true;
		}

		return false;

	}

	public ResultSet conectionUpdate(Connection connection, PreparedStatement st) throws SQLException {

	
	
	
		 connection = this.connect();
		 st = (PreparedStatement) connection
				.prepareStatement("SELECT * FROM userUnit");

		ResultSet rs = st.executeQuery();
	
	
	return rs;
	
	
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {

		try {

			if (null != pstmt) {

				pstmt.close();
				pstmt = null;

			}

		} catch (SQLException e) {

		}

	}

	public static void closeStatement(Statement stmt) {

		try {

			if (null != stmt) {

				stmt.close();
				stmt = null;

			}

		} catch (SQLException e) {

		}

	}

	

}
