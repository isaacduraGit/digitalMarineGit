package org.marineDigitalJournal.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository.ConnectionDB;

public class RetrieveEmail {

	public static void main(String[] args) {

		RetrieveEmail email = new RetrieveEmail();
		email.retrieveEmail();

	}

	public ArrayList retrieveEmail() {

		Connection connection = null;
		PreparedStatement st = null;
		ArrayList<String> email = new ArrayList <String>();
		int i=0;

		ConnectionDB connectionDB = new ConnectionDB();

		try {
			ResultSet rs = connectionDB.conectionUpdate(connection, st);

			while (rs.next()) {

				if (rs.getString("user_email").contains("@")&&(rs.getString("user_email")!=null)) {

					email.add(rs.getString("user_email"));
					i=i+1;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return email;

	}

}
