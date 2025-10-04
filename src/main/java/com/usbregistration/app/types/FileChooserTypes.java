package com.usbregistration.app.types;

import javax.swing.JFileChooser;

public enum FileChooserTypes {

	CONNECT_DB("Подключить файл Б/Д", JFileChooser.OPEN_DIALOG, "Файлы баз данных (.db)", "db"),
	CREATE_DB("Создать файл Б/Д", JFileChooser.SAVE_DIALOG, "Файлы баз данных (.db)", "db"),
	EXPORT("Экспорт", JFileChooser.SAVE_DIALOG, "Текстовые файлы (.txt)", "txt");
	
	public String title;
	public int dialogType;
	public String filterDescription;
	public String filterExtension;
	
	private FileChooserTypes(String title, int dialogType, String filterDescription, String filterExtension) {
		this.title = title;
		this.dialogType = dialogType;
		this.filterDescription = filterDescription;
		this.filterExtension = filterExtension;
	}
	
}
