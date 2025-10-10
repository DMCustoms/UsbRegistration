package com.usbregistration.app.handlers;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.interfaces.ButtonHandler;
import com.usbregistration.app.items.USBItem;
import com.usbregistration.app.items.USBItemsList;
import com.usbregistration.app.types.DialogMessages;
import com.usbregistration.app.usbnative.NativeUsb;

public class FindDevicesButtonHandler implements ButtonHandler {
	
	private MainFrame context;
	private JCheckBox filtration;
	
	public FindDevicesButtonHandler(JFrame context, JCheckBox filtration) {
		this.context = (MainFrame) context;
		this.filtration = filtration;
	}

	@Override
	public void handle() {
		try {
			USBItem[] devices = NativeUsb.enumerateUsbDevices();
			USBItemsList.INSTANCE.setUSBDataList(devices);
			if(filtration.isSelected()) context.setUSBList(USBItemsList.INSTANCE.getUSBDataList(true));
			else context.setUSBList(USBItemsList.INSTANCE.getUSBDataList(false));	
		} catch (UnsatisfiedLinkError err) {
			new ModalDialog(context, DialogMessages.LIBRARY_NOT_FOUND);
			System.exit(-1);
		}
	}

}
