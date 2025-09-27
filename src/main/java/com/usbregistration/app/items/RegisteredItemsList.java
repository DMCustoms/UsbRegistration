package com.usbregistration.app.items;

import java.util.Collection;

public enum RegisteredItemsList {

	INSTANCE;
	
	private RegisteredItem[] registeredItems = null;
	
	public void setRegisteredItemsList(Collection<RegisteredItem> items) {
		registeredItems = new RegisteredItem[items.size()];
		int index = 0;
		for (RegisteredItem item : items) {
			registeredItems[index] = item;
			index++;
		}
	}
	
	public RegisteredItem[] getRegisteredItemsDataList() {
		return registeredItems;
	}
	
}
