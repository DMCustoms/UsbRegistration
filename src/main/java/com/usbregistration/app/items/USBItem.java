package com.usbregistration.app.items;

public record USBItem(String serialNumber, String productName, String vendorID, String productID) {

	public USBItem(String serialNumber, String productName, String vendorID, String productID) {
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
