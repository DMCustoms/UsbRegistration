package com.usbregistration.app.graphics;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.usbregistration.app.listeners.JFileChooserListener;

public class ConnectDBFileChooser extends JFileChooser {

	public ConnectDBFileChooser(MainFrame context) {
		setDialogType(JFileChooser.OPEN_DIALOG);
		setDialogTitle("Подключить файл БД");
		addActionListener(new JFileChooserListener(this, context));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы баз данных (.db)", "db");
		setFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		showOpenDialog(context);
	}
	
}
