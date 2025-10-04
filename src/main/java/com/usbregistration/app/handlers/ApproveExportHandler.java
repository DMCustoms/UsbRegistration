package com.usbregistration.app.handlers;

import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.interfaces.ApproveHandler;
import com.usbregistration.app.types.DialogMessages;
import com.usbregistration.app.utils.FileUtils;

public class ApproveExportHandler implements ApproveHandler {
	
	private JFileChooser fileChooser;
	private MainFrame context;
	
	public ApproveExportHandler(JFileChooser fileChooser, JFrame context) {
		this.fileChooser = fileChooser;
		this.context = (MainFrame) context;
	}

	@Override
	public void approve() {
		try {
			String[] serialNumbers = Queries.selectSerialNumbersQuery(DBConnection.INSTANCE.getStatement());
			if (serialNumbers.length == 0) {
				new ModalDialog(context, DialogMessages.DATABASE_IS_EMPTY);
			} else {
				String path = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
				FileUtils.createExportFile(path, serialNumbers, context);
			}
		} catch (SQLException e) {
			new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
		}
	}
	
}
