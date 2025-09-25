package com.usbregistration.app.utils;

public enum DialogTypes {
	
	DATABASE_CREATE("База данных успешно создана"),
	DB_CREATION_ERROR("Ошибка создания файла БД"),
	DATABASE_CONNECTED("База данных подключена"),
	CONNECTION_ERROR("Ошибка соединения с БД"),
	CF_CREATION_ERROR("Ошибка создания конф. файла");
	
	public String value;
	
	private DialogTypes(String value) {
		this.value = value;
	}
	
}
