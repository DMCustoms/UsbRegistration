package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.usbregistration.app.dbutils.DBConnector;
import com.usbregistration.app.dbutils.DBStates;
import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.utils.CFUtils;
import com.usbregistration.app.utils.DialogTypes;

public class DBFileChooserListener implements ActionListener {
	
	private JFileChooser context;
	private MainFrame mainFrameContext;
	
	public DBFileChooserListener(JFileChooser context, MainFrame mainFrameContext) {
		this.context = context;
		this.mainFrameContext = mainFrameContext;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (context.getDialogType() == JFileChooser.SAVE_DIALOG && e.getActionCommand().equals("ApproveSelection")) {
			String path = context.getSelectedFile().getAbsolutePath() + ".db";
			File database = new File(path);
			try {
				if(database.createNewFile()) {
					if (DBConnector.createDB(path, mainFrameContext)) {
						CFUtils.createConfigureFile(path, mainFrameContext);
						mainFrameContext.setDBState(DBStates.DATABASE_IS_CONNECTED);
						new ModalDialog(mainFrameContext, DialogTypes.DATABASE_CREATE);
					}
				}
			} catch (IOException e1) {
				new ModalDialog(mainFrameContext, DialogTypes.DB_CREATION_ERROR);
			}
		} else if (context.getDialogType() == JFileChooser.OPEN_DIALOG && e.getActionCommand().equals("ApproveSelection")) {
			String path = context.getSelectedFile().getAbsolutePath();
			if (DBConnector.connectDB(path, mainFrameContext)) {
				CFUtils.createConfigureFile(path, mainFrameContext);
				mainFrameContext.setDBState(DBStates.DATABASE_IS_CONNECTED);
				new ModalDialog(mainFrameContext, DialogTypes.DATABASE_CONNECTED);
			}
		}	
	}
}









