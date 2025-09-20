package com.usbregistration.app;

import javax.swing.SwingUtilities;

import com.usbregistration.app.graphics.MainFrame;

public class App 
{
    public static void main( String[] args ) {
    	SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
