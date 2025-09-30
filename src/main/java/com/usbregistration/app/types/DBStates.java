package com.usbregistration.app.types;

public enum DBStates {
	
	DATABASE_IS_CONNECTED("База данных подключена"),
	DATABASE_IS_DISCONNECTED("База данных отключена");
	
	public String state;
	
	private DBStates(String state) {
		this.state = state;
	}
}
