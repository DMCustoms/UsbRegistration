package com.usbregistration.app.handlers;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JList;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.graphics.RegistrationCheckDialog;
import com.usbregistration.app.interfaces.ButtonHandler;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.items.USBItem;
import com.usbregistration.app.types.DialogMessages;

public class CheckButtonHandler implements ButtonHandler {
	
	private JFrame context;
	private JList<USBItem> list;
	
	public CheckButtonHandler(JFrame context, JList<USBItem> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public void handle() {
		try {
			String serialNumber = list.getSelectedValue().serialNumber();
			if (Queries.checkQuery(DBConnection.INSTANCE.getStatement(), serialNumber)) {
				RegisteredItem item = Queries.selectBySNQuery(DBConnection.INSTANCE.getStatement(), list.getSelectedValue().serialNumber());
				new RegistrationCheckDialog(context, item);
			} else {
				new ModalDialog(context, DialogMessages.DEVICE_NOT_REGISTERED);
			}
		} catch (SQLException e1) {
			new ModalDialog(context, DialogMessages.QUERY_ERROR);
		} 
	}

}
