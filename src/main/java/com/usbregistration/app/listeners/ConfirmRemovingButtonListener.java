package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.DialogTypes;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.graphics.SearchDialog;
import com.usbregistration.app.items.RegisteredItem;

public class ConfirmRemovingButtonListener implements ActionListener {
	
	private JFrame context;
	private RegisteredItem item;
	private JDialog confirmDialogContext;
	private SearchDialog searchDialogContext;
	
	public ConfirmRemovingButtonListener(JFrame context, JDialog dialogContext, SearchDialog searchDialogContext, RegisteredItem item) {
		this.context = context;
		this.confirmDialogContext = dialogContext;
		this.searchDialogContext = searchDialogContext;
		this.item = item;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Queries.removeQuery(DBConnection.INSTANCE.getStatement(), item);
			new ModalDialog(context, DialogTypes.REMOVE_COMPLETE);
			searchDialogContext.setRegisteredItemsList();
		} catch (SQLException e1) {
			new ModalDialog(context, DialogTypes.REMOVE_FAIL);
		} finally {
			confirmDialogContext.dispose();
		}
	}

}
