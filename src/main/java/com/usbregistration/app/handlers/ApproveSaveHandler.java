package com.usbregistration.app.handlers;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.interfaces.ApproveHandler;
import com.usbregistration.app.utils.FileUtils;

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
		FileUtils.createDBFile(path, context);
	}

}
