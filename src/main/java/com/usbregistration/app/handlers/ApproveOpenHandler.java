package com.usbregistration.app.handlers;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.usbregistration.app.dbutils.DBUtils;
import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.interfaces.ApproveHandler;
import com.usbregistration.app.types.DialogMessages;

public class ApproveOpenHandler implements ApproveHandler {
	
	private JFileChooser fileChooser;
	private MainFrame context;
	
	public ApproveOpenHandler(JFileChooser fileChooser, JFrame context) {
		this.fileChooser = fileChooser;
		this.context = (MainFrame) context;
	}

	@Override
	public void approve() {
		String path = fileChooser.getSelectedFile().getAbsolutePath();
		if (DBUtils.connectDB(path, context))	new ModalDialog(context, DialogMessages.DATABASE_CONNECTED);
	}

}
