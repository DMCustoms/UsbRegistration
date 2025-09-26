package com.usbregistration.app.graphics;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.usbregistration.app.listeners.DBFileChooserListener;

public class DBFileChooser extends JFileChooser {
	
	private static final long serialVersionUID = 1L;

	public DBFileChooser(MainFrame context, int dialogType) {
		setDialogType(dialogType);
		addActionListener(new DBFileChooserListener(this, context));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Файлы баз данных (.db)", "db");
		setFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		if (dialogType == JFileChooser.SAVE_DIALOG) {
			setDialogTitle("Создать файл БД");
			showSaveDialog(context);
		} else if (dialogType == JFileChooser.OPEN_DIALOG) {
			setDialogTitle("Подключить файл БД");
			showOpenDialog(context);
		}
	}
	
}
