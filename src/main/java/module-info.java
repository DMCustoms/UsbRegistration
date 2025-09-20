module UsbRegistration {
	exports com.usbregistration.app;
	exports com.usbregistration.app.usb;
	exports com.usbregistration.app.graphics;

	requires java.desktop;
	requires net.codecrete.usb;
	requires org.jetbrains.annotations;
}