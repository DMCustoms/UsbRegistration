package com.usbregistration.app.graphics;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.usbregistration.app.handlers.ApproveOpenHandler;
import com.usbregistration.app.handlers.ApproveSaveHandler;
import com.usbregistration.app.interfaces.ApproveHandler;
import com.usbregistration.app.types.DialogMessages;

public class DBFileChooser extends JFileChooser {
	
	private static final long serialVersionUID = 1L;
	private ApproveHandler handler;

	public DBFileChooser(JFrame context, int dialogType, DialogMessages title) {
		setDialogType(dialogType);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы баз данных (.db)", "db");
		setFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		addActionListener((ae) -> {
			if (ae.getActionCommand().equals("ApproveSelection")) {
				if (dialogType == JFileChooser.OPEN_DIALOG) handler = new ApproveOpenHandler(this, context);
				else if (dialogType == JFileChooser.SAVE_DIALOG) handler = new ApproveSaveHandler(this, context);
				handler.approve();
			}
		});
		setDialogTitle(title.value);
		showDialog(context, "OK");
	}
	
}
