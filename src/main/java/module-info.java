module UsbRegistration {
	exports com.usbregistration.app;
	exports com.usbregistration.app.items;
	exports com.usbregistration.app.graphics;
	exports com.usbregistration.app.dbutils;
	exports com.usbregistration.app.utils;
	exports com.usbregistration.app.types;

	requires transitive java.desktop;
	requires transitive java.sql;
}