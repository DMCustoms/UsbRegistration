package com.usbregistration.app.types;

public enum DialogMessages {
	
	DATABASE_CREATE("База данных успешно создана"),
	DB_CREATION_ERROR("Ошибка создания файла БД"),
	DATABASE_INCORRECT("Неверный файл БД"),
	DATABASE_CONNECTED("База данных подключена"),
	DATABASE_IS_EMPTY("База данных пуста"),
	CONNECTION_ERROR("Ошибка соединения с БД"),
	CF_CREATION_ERROR("Ошибка создания конф. файла"),
	CF_READING_ERROR("Ошибка чтения конф. файла"),
	EXPORT_CREATION_ERROR("Ошибка создания файла экспорта"),
	EXPORT_COMPLETE("Экспорт произведен"),
	INSERT_COMPLETE("Запись произведена"),
	INSERT_FAIL("Запись не удалась"),
	NOT_UNIQUE_VALUE("Данный S/N уже записан в БД"),
	DEVICE_NOT_REGISTERED("Устройство не зарегистрировано"),
	BLANK_FIELDS("Заполните все поля"),
	REMOVE_COMPLETE("Удалено успешно"),
	REMOVE_FAIL("Ошибка удаления записи"),
	CONFIRM_REMOVAL("Вы действительно хотите удалить запись?"),
	CONFIRM_UPDATE("Вы действительно хотите изменить запись?"),
	QUERY_ERROR("Ошибка выполнения запроса"),
	UPDATE_COMPLETE("Изменения внесены"),
	IO_EXCEPTION("Ошибка ввода/вывода"),
	UPDATE_FAIL("Ошибка внесения изменений"),
	LIBRARY_NOT_FOUND("Отсутствует libnativeusb.so");
	
	public String value;
	
	private DialogMessages(String value) {
		this.value = value;
	}
	
}
