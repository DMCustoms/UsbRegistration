package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.usbregistration.app.dbutils.DBConnector;
import com.usbregistration.app.dbutils.DBStates;
import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.utils.CFReader;

public class JFileChooserListener implements ActionListener {
	
	private JFileChooser context;
	private MainFrame mainFrameContext;
	
	public JFileChooserListener(JFileChooser context, MainFrame mainFrameContext) {
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
					if (DBConnector.INSTANCE.createDB(path)) {
						CFReader.INSTANCE.createConfigureFile(path);
						mainFrameContext.setDBState(DBStates.DATABASE_IS_CONNECTED);
						mainFrameContext.setDBButtonsDisabled();
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (context.getDialogType() == JFileChooser.OPEN_DIALOG && e.getActionCommand().equals("ApproveSelection")) {
			String path = context.getSelectedFile().getAbsolutePath();
			if (DBConnector.INSTANCE.connectDB(path)) {
				CFReader.INSTANCE.createConfigureFile(path);
				mainFrameContext.setDBState(DBStates.DATABASE_IS_CONNECTED);
				mainFrameContext.setDBButtonsDisabled();
			}
		}	
	}
}









