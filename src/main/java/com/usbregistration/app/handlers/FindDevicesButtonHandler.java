package com.usbregistration.app.handlers;

import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JFrame;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.interfaces.ButtonHandler;
import com.usbregistration.app.items.USBItemsList;

import net.codecrete.usb.Usb;
import net.codecrete.usb.UsbDevice;

public class FindDevicesButtonHandler implements ButtonHandler {
	
	private MainFrame context;
	private JCheckBox filtration;
	
	public FindDevicesButtonHandler(JFrame context, JCheckBox filtration) {
		this.context = (MainFrame) context;
		this.filtration = filtration;
	}

	@Override
	public void handle() {
		@NotNull @Unmodifiable Collection<UsbDevice> devices = Usb.getDevices();
		USBItemsList.INSTANCE.setUSBDataList(devices);
		if(filtration.isSelected()) context.setUSBList(USBItemsList.INSTANCE.getUSBDataList(true));
		else context.setUSBList(USBItemsList.INSTANCE.getUSBDataList(false));	
	}

}
