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

public class ConnectionDB {

	static String prop;

	public boolean conectionInsert(Connection connection, PreparedStatement st, String user_name, String user_surname,
			String user_email, String user_phone, String user_comments, File user_file) {

		try {

			connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/user_collect?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "e301Pepe*");
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
			int rs = st.executeUpdate();

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

		return false;

	}

	public ResultSet conectionUpdate(Connection connection, PreparedStatement st) throws SQLException {

	
	
	
		 connection = (Connection) DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/user_collect?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "e301Pepe*");
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
