package com.usbregistration.app.handlers;

import java.sql.SQLException;

import javax.swing.JDialog;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.graphics.RegistrationDialog;
import com.usbregistration.app.interfaces.ButtonHandler;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.types.DialogMessages;

public class ReRegistrationHandler implements ButtonHandler {
	
	private RegistrationDialog context;
	private RegisteredItem item;
	
	public ReRegistrationHandler(JDialog context) {
		this.context = (RegistrationDialog) context;
	}

	@Override
	public void handle() {
		try {
			item = context.getRegisteredItem();
			if (item == null) {
				new ModalDialog(context, DialogMessages.BLANK_FIELDS);
				return;
			}
			if (!Queries.checkQuery(DBConnection.INSTANCE.getStatement(), item.serialNumber())) {
				new ModalDialog(context, DialogMessages.DEVICE_NOT_REGISTERED);
				return;
			}
			if (Queries.updateQuery(DBConnection.INSTANCE.getStatement(), item)) {
				new ModalDialog(context, DialogMessages.UPDATE_COMPLETE);
				context.dispose();
			} else {
				new ModalDialog(context, DialogMessages.UPDATE_FAIL);
			}
		} catch (SQLException e1) {
			new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
		}
	}
}







