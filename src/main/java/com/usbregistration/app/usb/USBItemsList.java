package com.usbregistration.app.usb;

import java.util.Collection;

import net.codecrete.usb.UsbDevice;

public class USBItemsList {

	private static USBItem[] usbItems = null;
	
	private USBItemsList() {};
	
	public static void setUSBDataList(Collection<UsbDevice> devices) {
		usbItems = new USBItem[devices.size()];
		int index = 0;
		for (UsbDevice device: devices) {
			usbItems[index] = new USBItem(device.getSerialNumber(), device.getProduct(), device.getVendorId(), device.getProductId());
			index++;
		}
	}
	
	public static USBItem[] getUSBDataList() {
		return usbItems;
	}
}
