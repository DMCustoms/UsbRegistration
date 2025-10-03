package com.usbregistration.app.items;

import java.util.Collection;
import java.util.stream.Collectors;

import net.codecrete.usb.UsbDevice;

public enum USBItemsList {
	
	INSTANCE;

	private USBItem[] usbItems = null;
	private USBItem[] usbItemsFiltered = null;
	
	public void setUSBDataList(Collection<UsbDevice> devices) {
		usbItems = devices.stream().map(this::deviceToItemMapper).collect(Collectors.toList()).toArray(new USBItem[0]);
		usbItemsFiltered = devices.stream().map(this::deviceToItemMapper).filter(this::filterDevicesByName).collect(Collectors.toList()).toArray(new USBItem[0]);
	}
	
	public USBItem[] getUSBDataList(boolean isFiltered) {
		if (isFiltered) return usbItemsFiltered;
		return usbItems;
	}
	
	private USBItem deviceToItemMapper(UsbDevice device) {
		return new USBItem(device.getSerialNumber(), device.getProduct(), Integer.toString(device.getVendorId()), Integer.toString(device.getProductId()));
	}
	
	private boolean filterDevicesByName(USBItem item) {
		String regex = ".*(disk|Disk|DISK).*";
		return item.productName() == null ? false : item.productName().matches(regex);
	}
}

