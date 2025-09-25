package com.usbregistration.app.usb;

import java.util.ArrayList;
import java.util.Collection;

import net.codecrete.usb.UsbDevice;

public enum USBItemsList {
	
	INSTANCE;

	private USBItem[] usbItems = null;
	private USBItem[] usbItemsFiltered = null;
	
	public void setUSBDataList(Collection<UsbDevice> devices) {
		usbItems = new USBItem[devices.size()];
		ArrayList<USBItem> tmpList = new ArrayList<USBItem>();
		String regex = ".*(disk|Disk|DISK).*";
		int index = 0;
			for (UsbDevice device : devices) {
				USBItem tmpItem = new USBItem(device.getSerialNumber(), device.getProduct(), Integer.toString(device.getVendorId()), Integer.toString(device.getProductId()));
				usbItems[index] = tmpItem;
				if (device.getProduct() != null && device.getProduct().matches(regex)) tmpList.add(tmpItem);
				index++;
			}
		index = 0;
		usbItemsFiltered = new USBItem[tmpList.size()];
		for (USBItem item : tmpList) {
			usbItemsFiltered[index] = item;
			index++;
		}
	}
	
	public USBItem[] getUSBDataList(boolean isFiltered) {
		if (isFiltered) return usbItemsFiltered;
		return usbItems;
	}
}
