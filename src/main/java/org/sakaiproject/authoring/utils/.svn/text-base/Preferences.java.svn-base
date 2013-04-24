package org.sakaiproject.authoring.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Preferences {
	
	private static final String PROPERTIES_FILE = "authoring.properties";
	private static Properties properties;

	public static void loadPreferences() {
		properties = new Properties();
		try {
			File file = new File(PROPERTIES_FILE);
			if(file.exists()){
				properties.load(new FileInputStream(new File(PROPERTIES_FILE)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void savePreferences() {
		try {
			properties.store(new FileOutputStream(new File(PROPERTIES_FILE)), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Properties getProperties() {
		if (properties == null) Preferences.loadPreferences();
		return properties;
	}

	public static void setProperties(Properties properties) {
		if (properties == null) Preferences.loadPreferences();
		Preferences.properties = properties;
	}
	
	public static String getProperty(String propertyKey) {
		if (properties == null) Preferences.loadPreferences();
		return getProperties().getProperty(propertyKey);
	}

}
