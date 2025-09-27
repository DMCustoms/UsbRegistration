package com.usbregistration.app.items;

public record RegisteredItem(String serialNumber, String productName, String vid, String pid, String ownerSurname, String ownerName, String ownerLastname, String ownerDepartament, String protectedLabel, String date) {

	public RegisteredItem(String serialNumber, String productName, String vid, String pid, String ownerSurname, String ownerName, String ownerLastname, String ownerDepartament, String protectedLabel, String date) {
		this.serialNumber = serialNumber;
		this.productName = productName;
		this.vid = vid;
		this.pid = pid;
		this.ownerSurname = ownerSurname;
		this.ownerName = ownerName;
		this.ownerLastname = ownerLastname;
		this.ownerDepartament = ownerDepartament;
		this.protectedLabel = protectedLabel;
		this.date = date;
	}
	
	@Override
	public String toString() {
		return this.serialNumber + " " + this.productName + " " + this.vid + " " + this.pid + " " + this.ownerSurname + " " + this.ownerName + " " + this.ownerLastname + " " + this.ownerDepartament + " " + this.protectedLabel + " " + this.date;
	}
	
}
