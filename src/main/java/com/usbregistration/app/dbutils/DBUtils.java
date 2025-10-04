package com.usbregistration.app.dbutils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.types.DBStates;
import com.usbregistration.app.types.DialogMessages;
import com.usbregistration.app.utils.FileUtils;

public class DBUtils {
	
	private DBUtils() {
	}
	
	public static boolean createDB(String path, MainFrame context) {
		String creatingQuery = "CREATE TABLE Registration (id INTEGER PRIMARY KEY AUTOINCREMENT, Serial_number TEXT UNIQUE, Product_name TEXT,"
				+ " vid TEXT, pid TEXT, Owner_surname TEXT, Owner_name TEXT, Owner_lastname TEXT, Owner_departament TEXT, Protected_label TEXT, Date TEXT);";
		try {
			DBConnection.INSTANCE.openConnection(path);
			DBConnection.INSTANCE.getStatement().executeUpdate(creatingQuery);
			FileUtils.createConfigureFile(path, context);
			context.setDBState(DBStates.DATABASE_IS_CONNECTED);
			return true;
		} catch (SQLException e) {
			new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
			return false;
		}
	}
	
	public static boolean connectDB(String path, MainFrame context) {
		String[] values = new String[] { "", "id", "Serial_number", "Product_name", "vid", "pid", "Owner_surname", "Owner_name", "Owner_lastname", "Owner_departament", "Protected_label", "Date" };
		String testQuery = "SELECT * FROM Registration;";
		try {
			DBConnection.INSTANCE.openConnection(path);
			ResultSet resultSet = DBConnection.INSTANCE.getStatement().executeQuery(testQuery);
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			for (int i = 1; i <= 9; i++) {
				if (!rsMetaData.getColumnName(i).equals(values[i])) {
					new ModalDialog(context, DialogMessages.DATABASE_INCORRECT);
					context.setDBState(DBStates.DATABASE_IS_DISCONNECTED);
					return false;
				}
			}
			FileUtils.createConfigureFile(path, context);
			context.setDBState(DBStates.DATABASE_IS_CONNECTED);
			return true;
		} catch (SQLException e) {
			new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
			return false;
		}
	}
}





