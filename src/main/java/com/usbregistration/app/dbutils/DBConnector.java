package com.usbregistration.app.dbutils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.utils.DialogTypes;

public enum DBConnector {
	
	INSTANCE;
	
	private String[] values = new String[] { "", "Serial_number", "Product_name", "vid", "pid", "Owner_surname", "Owner_name", "Owner_lastname", "Owner_departament", "Protected_label" };
	
	public boolean createDB(String path, MainFrame context) {
		String creatingQuery = "CREATE TABLE Registration (Serial_number TEXT, Product_name TEXT,"
				+ " vid TEXT, pid TEXT, Owner_surname TEXT, Owner_name TEXT, Owner_lastname TEXT, Owner_departament TEXT, Protected_label INT);";
		try {
			DBConnection.INSTANCE.openConnection(path);
			DBConnection.INSTANCE.getStatement().executeUpdate(creatingQuery);
			return true;
		} catch (SQLException e) {
			new ModalDialog(context, DialogTypes.CONNECTION_ERROR);
			return false;
		}
	}
	
	public boolean connectDB(String path, MainFrame context) {
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
			new ModalDialog(context, DialogTypes.CONNECTION_ERROR);
			return false;
		}
	}
}





