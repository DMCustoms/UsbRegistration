package com.usbregistration.app.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.items.RegisteredItemsList;

public class Queries {

	private Queries() {
	}
	
	public static boolean insertQuery(Statement statement, RegisteredItem item) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO Registration (Serial_number, Product_name, vid, pid, Owner_surname, Owner_name, Owner_lastname, Owner_departament, Protected_label, Date) VALUES ")
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
			.append("', '")
			.append(item.date())
			.append("');");
		if (statement.executeUpdate(query.toString()) == 1) return true;
		return false;
	}
	
	public static boolean checkQuery(Statement statement, String serialNumber) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM Registration WHERE Serial_number = '")
			.append(serialNumber)
			.append("';");
		ResultSet rs = statement.executeQuery(query.toString());
		if (rs.getString("Serial_number") == null) return false;
		return true;
	}
	
	public static RegisteredItem selectBySNQuery(Statement statement, String serialNumber) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM Registration WHERE Serial_number = '")
			.append(serialNumber)
			.append("';");
		ResultSet rs = statement.executeQuery(query.toString());
		return new RegisteredItem(rs.getString("Serial_number"), rs.getString("Product_name"), rs.getString("vid"), rs.getString("pid"), rs.getString("Owner_surname"), rs.getString("Owner_name"), rs.getString("Owner_lastname"), rs.getString("Owner_departament"), rs.getString("Protected_label"), rs.getString("Date"));
	}
	
	public static RegisteredItem[] selectAllQuery(Statement statement, String serialNumber, String surName, String name, String lastName, String departament, String protectedLabel, String date) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM Registration WHERE Serial_number LIKE '")
			.append(serialNumber)
			.append("%' AND Owner_surname LIKE '")
			.append(surName)
			.append("%' AND Owner_name LIKE '")
			.append(name)
			.append("%' AND Owner_lastname LIKE '")
			.append(lastName)
			.append("%' AND Owner_departament LIKE '")
			.append(departament)
			.append("%' AND Protected_label LIKE '")
			.append(protectedLabel)
			.append("%' AND Date LIKE '")
			.append(date)
			.append("%';");
		ArrayList<RegisteredItem> list = new ArrayList<RegisteredItem>();
		ResultSet rs = statement.executeQuery(query.toString());
		while (rs.next()) {
			list.add(new RegisteredItem(rs.getString("Serial_number"), rs.getString("Product_name"), rs.getString("vid"), rs.getString("pid"), rs.getString("Owner_surname"), rs.getString("Owner_name"), rs.getString("Owner_lastname"), rs.getString("Owner_departament"), rs.getString("Protected_label"), rs.getString("Date")));        
		}
		RegisteredItemsList.INSTANCE.setRegisteredItemsList(list);
		return RegisteredItemsList.INSTANCE.getRegisteredItemsDataList();
	}
	
	public static boolean removeQuery(Statement statement, RegisteredItem removalItem) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM Registration WHERE Serial_number = '")
			.append(removalItem.serialNumber())
			.append("';");
		if (statement.executeUpdate(query.toString()) == 1) return true;
		return false;
	}
	
	public static boolean updateQuery(Statement statement, RegisteredItem updatedItem) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE Registration SET Owner_surname = '")
			.append(updatedItem.ownerSurname())
			.append("', Owner_name = '")
			.append(updatedItem.ownerName())
			.append("', Owner_lastname = '")
			.append(updatedItem.ownerLastname())
			.append("', Owner_departament = '")
			.append(updatedItem.ownerDepartament())
			.append("', Protected_label = '")
			.append(updatedItem.protectedLabel())
			.append("', Date = '")
			.append(updatedItem.date())
			.append("' WHERE Serial_number = '")
			.append(updatedItem.serialNumber())
			.append("';");
		if (statement.executeUpdate(query.toString()) == 1) return true;
		return false;
	}
	
	public static String[] selectSerialNumbersQuery(Statement statement) throws SQLException {
		String query = "SELECT Serial_number FROM Registration;";
		ResultSet rs = statement.executeQuery(query);
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString("Serial_number"));
		}
		return list.toArray(new String[0]);
	}
	
}







