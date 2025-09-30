package com.usbregistration.app.handlers;

import java.sql.SQLException;

import javax.swing.JDialog;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.ConfirmDialog;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.graphics.SearchDialog;
import com.usbregistration.app.interfaces.ConfirmHandler;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.types.DialogMessages;

public class ConfirmUpdateHandler implements ConfirmHandler {
	
	private RegisteredItem item;
	private SearchDialog context;
	private ConfirmDialog confirmDialogContext;

	public ConfirmUpdateHandler(JDialog context, JDialog confirmDialogContext, RegisteredItem item) {
		this.context = (SearchDialog) context;
		this.confirmDialogContext = (ConfirmDialog) confirmDialogContext; 
		this.item = item;
	}
	
	@Override
	public void confirm() {
		try {
			if (Queries.updateQuery(DBConnection.INSTANCE.getStatement(), item)) {
				confirmDialogContext.dispose();
				new ModalDialog(context, DialogMessages.UPDATE_COMPLETE);
				context.setRegisteredItemsList();
			} else {
				new ModalDialog(context, DialogMessages.UPDATE_FAIL);
			}
		} catch (SQLException e1) {
			new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
		}
	}

}
