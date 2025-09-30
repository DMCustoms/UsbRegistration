package com.usbregistration.app.handlers;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.usbregistration.app.dbutils.DBUtils;
import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.interfaces.ApproveHandler;
import com.usbregistration.app.types.DialogMessages;

public class ApproveSaveHandler implements ApproveHandler {
	
	private JFileChooser fileChooser;
	private MainFrame context;
	
	public ApproveSaveHandler(JFileChooser fileChooser, JFrame context) {
		this.fileChooser = fileChooser;
		this.context = (MainFrame) context;
	}

	@Override
	public void approve() {
		String path = fileChooser.getSelectedFile().getAbsolutePath() + ".db";
		File database = new File(path);
		try {
			if(database.createNewFile()) {
				if (DBUtils.createDB(path, context)) {
					new ModalDialog(context, DialogMessages.DATABASE_CREATE);
				}
				else {
					new ModalDialog(context, DialogMessages.DB_CREATION_ERROR);
				}
			}
		} catch (IOException e1) {
			new ModalDialog(context, DialogMessages.CONNECTION_ERROR);
		}
	}

}
