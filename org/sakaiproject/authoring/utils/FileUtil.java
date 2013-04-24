package org.sakaiproject.authoring.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class FileUtil {
	
	public static File getFileByKey(String key){
		return new File(FileUtil.class.getClassLoader().getResource(Bundle.getString(key)).getFile());
	}
	
	public static URL getFileURL(String key){
		return FileUtil.class.getClassLoader().getResource(Bundle.getString(key));
	}
	
	public static ImageIcon getImageIcon(File file){
		try {
			return new ImageIcon(file.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static ImageIcon getImageIcon(String key){
		// ImageIcon cannot receive null URL as parameter, String constructor returns voided....
		URL fileURL = getFileURL(key);
		if ( fileURL == null) 
			return null; // imitates URL(String) constructor.
		else
			return new ImageIcon(fileURL);
	}
	
	
	public static File getFromFileChooser(final String[] validSuffix){
		
		//create file chooser using preferred directory
		JFileChooser fileChooser;
		String directory = (String) Preferences.getProperties().get(
				"preferences.diretory");
		if (directory != null && !directory.equals("")) {
			fileChooser = new JFileChooser(new File(directory));
		} else {
			fileChooser = new JFileChooser();
		}
		
		if (validSuffix.length > 0) {
			//create filter using valid suffix
			FileFilter filter = new FileFilter() {
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					}
					for (String suffix : validSuffix) {
						if (f.getName().toLowerCase().endsWith(
								suffix.toLowerCase())) {
							return true;
						}
					}
					return false;
				}

				public String getDescription() {
					return "";
				}
			};
			fileChooser.setFileFilter(filter);
		}
		
		//verify selected file
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION
				&& fileChooser.getSelectedFile() != null) {
			Preferences.getProperties().setProperty("preferences.diretory",
					fileChooser.getSelectedFile().getParent());
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	public static File getFromFileChooser(){
		return getFromFileChooser(new String[]{});
	}

}
