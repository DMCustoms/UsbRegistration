package com.usbregistration.app.graphics;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.usbregistration.app.handlers.ApproveExportHandler;
import com.usbregistration.app.handlers.ApproveOpenHandler;
import com.usbregistration.app.handlers.ApproveSaveHandler;
import com.usbregistration.app.interfaces.ApproveHandler;
import com.usbregistration.app.types.DialogMessages;
import com.usbregistration.app.types.FileChooserTypes;

public class FileChooser extends JFileChooser {
	
	private static final long serialVersionUID = 1L;
	private ApproveHandler handler;

	public FileChooser(JFrame context, FileChooserTypes chooserType) {
		setDialogType(chooserType.dialogType);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(chooserType.filterDescription, chooserType.filterExtension);
		setFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		addActionListener((ae) -> {
			if (ae.getActionCommand().equals("ApproveSelection")) {
				if (chooserType == FileChooserTypes.CONNECT_DB) handler = new ApproveOpenHandler(this, context);
				else if (chooserType == FileChooserTypes.CREATE_DB) handler = new ApproveSaveHandler(this, context);
				else if (chooserType == FileChooserTypes.EXPORT) handler = new ApproveExportHandler(this, context);
				handler.approve();
			}
		});
		setDialogTitle(chooserType.title);
		showDialog(context, "OK");
	}
	
}
