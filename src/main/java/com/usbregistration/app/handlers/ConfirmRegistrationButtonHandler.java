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

public class ConfirmRegistrationButtonHandler implements ButtonHandler {
	
	private RegistrationDialog context;
	private RegisteredItem item;
	
	public ConfirmRegistrationButtonHandler(JDialog context) {
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
			if (Queries.insertQuery(DBConnection.INSTANCE.getStatement(), item)) {
				new ModalDialog(context, DialogMessages.INSERT_COMPLETE);
				context.dispose();
			} else {
				new ModalDialog(context, DialogMessages.INSERT_FAIL);
			}
		} catch (SQLException e1) {
			if (e1.getMessage().equals("[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: Registration.Serial_number)")) new ModalDialog(context, DialogMessages.NOT_UNIQUE_VALUE);
			else new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
		}
	}

}
