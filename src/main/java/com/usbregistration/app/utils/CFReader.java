package com.usbregistration.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public enum CFReader {

	INSTANCE;
	
	public void createConfigureFile(String path) {
		StringBuilder configureFileName = new StringBuilder();
		configureFileName.append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("config.txt");
		try (FileWriter fw = new FileWriter(configureFileName.toString())) {
			fw.write(path);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public String readConfigureFile() {
		StringBuilder configureFileName = new StringBuilder();
		configureFileName.append(System.getProperty("user.dir")).append(System.getProperty("file.separator")).append("config.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(configureFileName.toString()))) {
			String result = "";
			while (br.ready()) {
				result = br.readLine();
			}
			File file = new File(result);
			if (file.exists()) return result;
			return "";
		} catch (IOException e) {
			return "";
		}
	}
}

