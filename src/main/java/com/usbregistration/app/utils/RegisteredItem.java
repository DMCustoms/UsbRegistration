package com.usbregistration.app.utils;

public record RegisteredItem(String serialNumber, String productName, String vid, String pid, String ownerSurname, String ownerName, String ownerLastname, String ownerDepartament, String protectedLabel) {

	public RegisteredItem(String serialNumber, String productName, String vid, String pid, String ownerSurname, String ownerName, String ownerLastname, String ownerDepartament, String protectedLabel) {
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.vid = vid;
		this.pid = pid;
		this.ownerSurname = ownerSurname;
		this.ownerName = ownerName;
		this.ownerLastname = ownerLastname;
		this.ownerDepartament = ownerDepartament;
		this.protectedLabel = protectedLabel;
	}
	
}
