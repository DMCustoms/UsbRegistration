package com.usbregistration.app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JCheckBox;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.usb.USBItem;
import com.usbregistration.app.usb.USBItemsList;

import net.codecrete.usb.Usb;
import net.codecrete.usb.UsbDevice;

public class FindUSBButtonListener implements ActionListener{
	
	private MainFrame context;
	private JCheckBox filtration;
	
	public FindUSBButtonListener(MainFrame context, JCheckBox filtration) {
		this.context = context;
		this.filtration = filtration;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			@NotNull @Unmodifiable Collection<UsbDevice> devices = Usb.getDevices();
			USBItemsList.INSTANCE.setUSBDataList(devices);
			if(filtration.isSelected()) context.setUSBList(USBItemsList.INSTANCE.getUSBDataList(true));
			else context.setUSBList(USBItemsList.INSTANCE.getUSBDataList(false));
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	}

}
