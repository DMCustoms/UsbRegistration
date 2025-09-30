package com.usbregistration.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.usbregistration.app.graphics.MainFrame;
import com.usbregistration.app.graphics.ModalDialog;
import com.usbregistration.app.types.DialogMessages;

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
			new ModalDialog(context, DialogMessages.CF_CREATION_ERROR);
		}
	}
	
	public static String readConfigureFile(MainFrame context) {
		String result = null;
		StringBuilder configureFileName = new StringBuilder();
		configureFileName.append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("config.txt");
		File configFile = new File(configureFileName.toString());
		if (!configFile.exists()) return null;
		try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
			while (br.ready()) {
				result = br.readLine();
				if (result.startsWith("#")) continue;
			}
			File dbFile = new File(result);
			if (!dbFile.exists()) return null;
		} catch (IOException e) {
			new ModalDialog(context, DialogMessages.CF_READING_ERROR);
			return null;
		}
		return result;
	}
}

