package com.usbregistration.app.graphics;

public enum DialogTypes {
	
	DATABASE_CREATE("База данных успешно создана"),
	DB_CREATION_ERROR("Ошибка создания файла БД"),
	DATABASE_CONNECTED("База данных подключена"),
	CONNECTION_ERROR("Ошибка соединения с БД"),
	CF_CREATION_ERROR("Ошибка создания конф. файла"),
	INSERT_COMPLETE("Запись произведена"),
	INSERT_FAIL("Запись не удалась"),
	NOT_UNIQUE_VALUE("Данный S/N уже записан в БД"),
	DEVICE_NOT_REGISTERED("Устройство не зарегистрировано"),
	BLANK_FIELDS("Заполните все поля");
	
	public String value;
	
	private DialogTypes(String value) {
		this.value = value;
	}
	
}
