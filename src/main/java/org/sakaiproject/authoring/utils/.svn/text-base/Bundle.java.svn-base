package org.sakaiproject.authoring.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Bundle {
	
	private static Locale locale 		= new Locale("pt","BR");
	private static ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
	
	public static String getString(String key){
		String _key;
		
		try {
			_key = bundle.getString(key); 
		} catch (MissingResourceException mre) {
			_key = key;
		}
		return _key; 
	}

	public static String getStringWithDefault(String key, String defaultKey) {
		String _key = getString(key);
		return ( (key == null || (key != null && key.equals(_key))) ? defaultKey : _key );
	}
}
