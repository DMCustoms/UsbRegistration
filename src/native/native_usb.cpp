#include "com_usbregistration_app_usbnative_NativeUsb.h"
#include <libusb-1.0/libusb.h>

JNIEXPORT jobjectArray JNICALL Java_com_usbregistration_app_usbnative_NativeUsb_enumerateUsbDevices(JNIEnv *env, jobject) {
		
	jclass usbItemClass = env->FindClass("com/usbregistration/app/items/USBItem");
	jmethodID constructor = env->GetMethodID(usbItemClass, "<init>", "(Ljava/lang/String;Ljava/lang/String;II)V");
	
	libusb_device **devs;
    libusb_context *ctx = nullptr;
    int r;
    ssize_t cnt;
    
    r = libusb_init(&ctx);
    
    libusb_set_debug(ctx, 3);
    
    cnt = libusb_get_device_list(ctx, &devs);
    
    jobjectArray objectArray = env->NewObjectArray(cnt, usbItemClass, NULL);
    
    libusb_device_descriptor desc{};
    
    for (int i = 0; i < cnt; i++) {
		
        libusb_device_handle *handle;
        
        unsigned char *serial = new unsigned char[33]();
        unsigned char *product = new unsigned char[65]();
        
        int vendorID;
        int productID;
        
        r = libusb_get_device_descriptor(devs[i], &desc);
        
        vendorID = desc.idVendor;
        productID = desc.idProduct;
        
        try {
            libusb_open(devs[i], &handle);
            if (handle != nullptr) {
                if (libusb_get_string_descriptor_ascii(handle, desc.iSerialNumber, serial, 31) >= 0) {
                    serial[32] = '\0';
                }
                if (libusb_get_string_descriptor_ascii(handle, desc.iProduct, product, 63) >= 0) {
                    product[64] = '\0';
                }
            }
            
            jobject usbObject = env->NewObject(usbItemClass, constructor, env->NewStringUTF((char*)serial), env->NewStringUTF((char*)product), vendorID, productID);
            
            env->SetObjectArrayElement(objectArray, i, usbObject);
            
            libusb_close(handle);

        } catch (libusb_error &e) {
        }
		
    }
    
    libusb_free_device_list(devs, 1);
    
    libusb_exit(ctx);
    
    return objectArray;
}