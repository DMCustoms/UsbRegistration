package com.usbregistration.app.dbutils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public enum DBConnector {
	
	INSTANCE;
	
	private String[] values = new String[] { "", "Serial_number", "Product_name", "vid", "pid", "Owner_surname", "Owner_name", "Owner_lastname", "Owner_departament", "Protected_label" };
	
	public boolean createDB(String path) {
		String creatingQuery = "CREATE TABLE Registration (Serial_number TEXT, Product_name TEXT,"
				+ " vid INTEGER, pid INTEGER, Owner_surname TEXT, Owner_name TEXT, Owner_lastname TEXT, Owner_departament TEXT, Protected_label INT);";
		try {
			DBConnection.INSTANCE.openConnection(path);
			DBConnection.INSTANCE.getStatement().executeUpdate(creatingQuery);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean connectDB(String path) {
		String testQuery = "SELECT * FROM Registration;";
		try {
			DBConnection.INSTANCE.openConnection(path);
			ResultSet resultSet = DBConnection.INSTANCE.getStatement().executeQuery(testQuery);
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			for (int i = 1; i <= 9; i++) {
				if (!rsMetaData.getColumnName(i).equals(values[i])) return false;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}





