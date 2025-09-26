package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.graphics.RegistrationDialog;
import com.usbregistration.app.utils.DialogTypes;
import com.usbregistration.app.utils.RegisteredItem;

public class ConfirmRegistrationButtonListener implements ActionListener {
	
	private RegistrationDialog context;
	private RegisteredItem item;
	private JFrame mainFrameContext;

	public ConfirmRegistrationButtonListener(RegistrationDialog context, JFrame mainFrameContext) {
		this.context = context;
		this.mainFrameContext = mainFrameContext;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			item = context.getRegisteredItemInstance();
			Queries.insertQuery(DBConnection.INSTANCE.getStatement(), item);
			new ModalDialog(mainFrameContext, DialogTypes.INSERT_COMPLETE);
		} catch (SQLException e1) {
			if (e1.getMessage().equals("[SQLITE_CONSTRAINT_UNIQUE] A UNIQUE constraint failed (UNIQUE constraint failed: Registration.Serial_number)")) new ModalDialog(mainFrameContext, DialogTypes.NOT_UNIQUE_VALUE);
			else new ModalDialog(mainFrameContext, DialogTypes.INSERT_FAIL);
		} finally {
			context.dispose();
		}
	}

}
