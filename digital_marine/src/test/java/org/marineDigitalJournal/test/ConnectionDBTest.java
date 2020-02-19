package org.marineDigitalJournal.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.marineDigitalJournal.login.persistence.jdbc.JdbcLoginRepository.ConnectionDB;

/**
 * Class to implement furhter testing to the remote DB
 */

public class ConnectionDBTest {

	Connection connection;

	@Before
	public void before() {

	}

	@After
	public void after() {

		// ConnectionDB.

	}

	@Test
	public void closeStatement() throws SQLException {

		Statement statement = connection.createStatement();

		ConnectionDB.closeStatement(statement);

		assertTrue(statement.isClosed());

	}
	
	@Test
	public void closeStatementWithNull() {
		
		
		ConnectionDB.closeStatement(null);
		
	}
	

}
