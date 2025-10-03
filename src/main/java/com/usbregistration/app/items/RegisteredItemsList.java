package com.usbregistration.app.items;

import java.util.Collection;
import java.util.stream.Collectors;

public enum RegisteredItemsList {

	INSTANCE;
	
	private RegisteredItem[] registeredItems = null;
	
	public void setRegisteredItemsList(Collection<RegisteredItem> items) {
		registeredItems = items.stream().collect(Collectors.toList()).toArray(new RegisteredItem[0]);
	}
	
	public RegisteredItem[] getRegisteredItemsDataList() {
		return registeredItems;
	}
	
}
