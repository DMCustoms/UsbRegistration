package com.usbregistration.app.types;

public enum DialogMessages {
	
	DATABASE_CREATE("База данных успешно создана"),
	DB_CREATION_ERROR("Ошибка создания файла БД"),
	DATABASE_INCORRECT("Неверный файл БД"),
	DATABASE_CONNECTED("База данных подключена"),
	CONNECTION_ERROR("Ошибка соединения с БД"),
	CF_CREATION_ERROR("Ошибка создания конф. файла"),
	CF_READING_ERROR("Ошибка чтения конф. файла"),
	INSERT_COMPLETE("Запись произведена"),
	INSERT_FAIL("Запись не удалась"),
	NOT_UNIQUE_VALUE("Данный S/N уже записан в БД"),
	DEVICE_NOT_REGISTERED("Устройство не зарегистрировано"),
	BLANK_FIELDS("Заполните все поля"),
	REMOVE_COMPLETE("Удалено успешно"),
	REMOVE_FAIL("Ошибка удаления записи"),
	CONFIRM_REMOVAL("Вы действительно хотите удалить запись?"),
	CONFIRM_UPDATE("Вы действительно хотите изменить запись?"),
	SAVE_CHOOSER_TITLE("Создать файл БД"),
	OPEN_CHOOSER_TITLE("Подключить файл БД"),
	QUERY_ERROR("Ошибка выполнения запроса"),
	UPDATE_COMPLETE("Изменения внесены"),
	UPDATE_FAIL("Ошибка внесения изменений");
	
	public String value;
	
	private DialogMessages(String value) {
		this.value = value;
	}
	
}
