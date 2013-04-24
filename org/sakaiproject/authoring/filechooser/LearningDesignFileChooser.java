package org.sakaiproject.authoring.filechooser;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.sakaiproject.authoring.utils.Bundle;

public class LearningDesignFileChooser extends JFileChooser {

	private static final long serialVersionUID = -3916201606825358420L;

	public LearningDesignFileChooser(File currentDirectory) throws NumberFormatException {
		super(currentDirectory);
		init();
	}
	
	public LearningDesignFileChooser(){
		init();
	}
	
	private void init() {
		setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileFilter filter = new FileFilter(){
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
			}
			public String getDescription() {
				return Bundle.getString("label.manifestfileordirectory");
			}
		};
		setFileFilter(filter);
	}

}
