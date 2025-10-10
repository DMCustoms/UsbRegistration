package com.usbregistration.app.items;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum USBItemsList {
	
	INSTANCE;

	private USBItem[] usbItems = null;
	private USBItem[] usbItemsFiltered = null;
	
	public void setUSBDataList(USBItem[] devices) {
		usbItems = devices;
		usbItemsFiltered = Stream.of(devices).filter(this::filterDevicesByName).collect(Collectors.toList()).toArray(new USBItem[0]);
	}
	
	public USBItem[] getUSBDataList(boolean isFiltered) {
		if (isFiltered) return usbItemsFiltered;
		return usbItems;
	}
	
	private boolean filterDevicesByName(USBItem item) {
		String regex = ".*(disk|Disk|DISK).*";
		return item.productName() == null ? false : item.productName().matches(regex);
	}
}

