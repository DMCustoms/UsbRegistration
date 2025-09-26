package com.usbregistration.app.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.usbregistration.app.utils.RegisteredItem;

public class Queries {

	private Queries() {
	}
	
	public static void insertQuery(Statement statement, RegisteredItem item) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Registration (Serial_number, Product_name, vid, pid, Owner_surname, Owner_name, Owner_lastname, Owner_departament, Protected_label) VALUES ")
			.append("('")
			.append(item.serialNumber())
			.append("', '")
			.append(item.productName())
			.append("', '")
			.append(item.vid())
			.append("', '")
			.append(item.pid())
			.append("', '")
			.append(item.ownerSurname())
			.append("', '")
			.append(item.ownerName())
			.append("', '")
			.append(item.ownerLastname())
			.append("', '")
			.append(item.ownerDepartament())
			.append("', '")
			.append(item.protectedLabel())
			.append("');");
		statement.executeUpdate(query.toString());
	}
	
	public static RegisteredItem checkQuery(Statement statement, String serialNumber) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM Registration WHERE Serial_number = '")
			.append(serialNumber)
			.append("';");
		ResultSet rs = statement.executeQuery(query.toString());
		if (rs.getString("Serial_number") == null) throw new SQLException();
		return new RegisteredItem(rs.getString("Serial_number"), rs.getString("Product_name"), rs.getString("vid"), rs.getString("pid"), rs.getString("Owner_surname"), rs.getString("Owner_name"), rs.getString("Owner_lastname"), rs.getString("Owner_departament"), rs.getString("Protected_label"));
	}
}







