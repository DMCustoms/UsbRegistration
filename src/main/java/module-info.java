module UsbRegistration {
	exports com.usbregistration.app;
	exports com.usbregistration.app.usb;
	exports com.usbregistration.app.graphics;
	exports com.usbregistration.app.dbutils;

	requires transitive java.desktop;
	requires transitive net.codecrete.usb;
	requires transitive org.jetbrains.annotations;
	requires transitive java.sql;
}