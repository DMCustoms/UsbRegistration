package com.usbregistration.app.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum DBConnection {
	
	INSTANCE;
	
	private Statement statement = null;
	private Connection connection = null;
	
	public void openConnection(String path) throws SQLException {
		StringBuilder url = new StringBuilder();
		url.append("jdbc:sqlite:").append(path);
		connection = DriverManager.getConnection(url.toString());
		statement = connection.createStatement();
	}
	
	public Statement getStatement() throws SQLException {
		if (statement != null) return statement;
		else throw new SQLException();
	}
	
	public void closeConnection() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	}
	
}
