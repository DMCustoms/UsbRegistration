package com.usbregistration.app.usbnative;

import com.usbregistration.app.items.USBItem;

public class NativeUsb {

	static {
		StringBuilder pathToLibrary = new StringBuilder();
		pathToLibrary.append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("libnativeusb.so");
		System.load(pathToLibrary.toString());
	}
	
	public static native USBItem[] enumerateUsbDevices();
	
}
