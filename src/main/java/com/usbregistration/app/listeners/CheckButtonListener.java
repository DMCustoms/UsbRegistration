package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JList;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.DialogTypes;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.graphics.RegistrationCheckDialog;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.items.USBItem;

public class CheckButtonListener implements ActionListener {

	private JFrame context;
	private JList<USBItem> list;

	public CheckButtonListener(JFrame context, JList<USBItem> list) {
		this.context = context;
		this.list = list;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			RegisteredItem item = Queries.checkQuery(DBConnection.INSTANCE.getStatement(), list.getSelectedValue().serialNumber());
			new RegistrationCheckDialog(context, item);
		} catch (SQLException e1) {
			new ModalDialog(context, DialogTypes.DEVICE_NOT_REGISTERED);
		} 
	}

}
