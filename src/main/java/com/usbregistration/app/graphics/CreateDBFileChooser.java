package com.usbregistration.app.graphics;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.usbregistration.app.listeners.JFileChooserListener;

public class CreateDBFileChooser extends JFileChooser {
	
	public CreateDBFileChooser(MainFrame context) {
		setDialogType(JFileChooser.SAVE_DIALOG);
		setDialogTitle("Создать файл БД");
		addActionListener(new JFileChooserListener(this, context));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы баз данных (.db)", "db");
		setFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		showSaveDialog(context);
	}
	
}
