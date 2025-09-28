package com.usbregistration.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.usbregistration.app.graphics.DialogTypes;
import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;

public class CFUtils {

	private CFUtils() {
	}
	
	public static void createConfigureFile(String path, MainFrame context) {
		StringBuilder configureFileName = new StringBuilder();
		configureFileName.append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("config.txt");
		try (FileWriter fw = new FileWriter(configureFileName.toString())) {
			fw.write("# This file created by USBRegistration program and needed to search a database file\n");
			fw.write(path);
		} catch (IOException ex) {
			new ModalDialog(context, DialogTypes.CF_CREATION_ERROR);
		}
	}
	
	public static String readConfigureFile(MainFrame context) {
		String result = "";
		StringBuilder configureFileName = new StringBuilder();
		configureFileName.append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("config.txt");
		File configFile = new File(configureFileName.toString());
		if (!configFile.exists()) return "";
		try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
			while (br.ready()) {
				result = br.readLine();
				if (result.startsWith("#")) continue;
			}
			File dbFile = new File(result);
			if (!dbFile.exists()) return "";
		} catch (IOException e) {
			new ModalDialog(context, DialogTypes.CF_CREATION_ERROR);
		}
		return result;
	}
}

