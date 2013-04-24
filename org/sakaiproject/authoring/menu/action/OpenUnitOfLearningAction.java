package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.filechooser.LearningDesignFileChooser;
import org.sakaiproject.authoring.model.ManifestModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.ManifestUtil;
import org.sakaiproject.authoring.utils.Preferences;

public class OpenUnitOfLearningAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	private ManifestModel manifestModel;

	public OpenUnitOfLearningAction(ManifestModel manifestModel) {
		super(Bundle.getString("menu.open"));
		this.manifestModel = manifestModel;
	}

	public void actionPerformed(ActionEvent e) {

		File manifestFile = null;
		
		try {
			manifestFile = getManifestFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if(manifestFile == null){
			return;
		}	
	
		setCurrentFile(manifestFile);
		Preferences.getProperties().setProperty("preferences.diretory",
					manifestFile.getParent());
		
	}

	private void setCurrentFile(File file) {

		try {
			
			manifestModel.setManifestFile(file);
			Authoring.getInstance().setManifest((ManifestUtil.openManifestInXML(file)));
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(Authoring.getInstance(), Bundle
					.getString("error.cannotopenfile")
					+ e.getMessage(), Bundle.getString("error.error"),
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private File getManifestFile() throws IOException {
		
		JFileChooser fileChooser;
		String directory = (String) Preferences.getProperties().get("preferences.diretory");
		
		if(directory != null && !directory.equals("")){
			fileChooser = new LearningDesignFileChooser(new File(directory));
		} else {
			fileChooser = new LearningDesignFileChooser();
		}
		
		int returnValue = fileChooser.showOpenDialog(Authoring.getInstance());
		
		if(returnValue != JFileChooser.APPROVE_OPTION || fileChooser.getSelectedFile() == null ){
			return null;
		}
		
		File file = fileChooser.getSelectedFile();
		
		File manifestFile = null;
		
		if(file.isDirectory()){
			manifestFile = new File(file, "imsmanifest.xml");
			if(!file.exists()){
				JOptionPane.showMessageDialog(Authoring.getInstance(), 
						Bundle.getString("error.chosecpdirectory"), 
						Bundle.getString("error.error"), JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} else {
			if(!file.getName().equalsIgnoreCase("imsmanifest.xml")){
				JOptionPane.showMessageDialog(Authoring.getInstance(), 
						Bundle.getString("error.choosemanifest"), 
						Bundle.getString("error.error"), JOptionPane.ERROR_MESSAGE);
				return null;
			}
			manifestFile = file;
		}
		return manifestFile;
	}
}
