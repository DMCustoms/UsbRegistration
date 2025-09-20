package com.usbregistration.app.usb;

public record USBItem(String serialNumber, String productName, int vendorID, int productID) {

	public USBItem(String serialNumber, String productName, int vendorID, int productID) {
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.vendorID = vendorID;
		this.productID = productID;
	}
	
	@Override
	public String toString() {
		return "Serial number: " + serialNumber + ", Product name: " + productName + ", Vendor ID: " + vendorID + ", ProductID: " + productID;
	}
	
}
