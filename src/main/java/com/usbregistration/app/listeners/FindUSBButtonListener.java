package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.usb.USBItemsList;

import net.codecrete.usb.Usb;
import net.codecrete.usb.UsbDevice;

public class FindUSBButtonListener implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			@NotNull @Unmodifiable Collection<UsbDevice> devices = Usb.getDevices();
			USBItemsList.setUSBDataList(devices);
			MainFrame.setUSBList(USBItemsList.getUSBDataList());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
	}

}
