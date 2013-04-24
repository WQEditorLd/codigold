package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.imsglobal.jaxb.ld.Conference;
import org.imsglobal.jaxb.ld.GameService;
import org.imsglobal.jaxb.ld.SendMail;
import org.sakaiproject.authoring.dialog.ConferenceDialog;
import org.sakaiproject.authoring.dialog.EnvironmentDialog;
import org.sakaiproject.authoring.dialog.GameServiceDialog;
import org.sakaiproject.authoring.dialog.ObjectDialog;
import org.sakaiproject.authoring.dialog.SendMailDialog;
import org.sakaiproject.authoring.dialog.ServiceOptionDialog;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class AddServiceButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	private EnvironmentDialog environmentDialog;
	
	public AddServiceButtonActionListener(EnvironmentDialog environmentDialog) {
		
		super(Bundle.getString("button.newservice"), FileUtil.getImageIcon("image.serviceicon"));
		
		this.environmentDialog = environmentDialog;
	}
	

	public void actionPerformed(ActionEvent arg0) {
		
		ServiceOptionDialog optionDialog = new ServiceOptionDialog(environmentDialog);
		optionDialog.setVisible(true);
		if(optionDialog.getService() != null){
			openServiceDialog(optionDialog.getService());
		}
		
		
	}
	
	private void openServiceDialog(Object service) {
		
		ObjectDialog dialog = null;
		if(service instanceof SendMail){
			dialog = new SendMailDialog(environmentDialog, environmentDialog.getRolesModel());
		}
		if(service instanceof Conference){
			dialog = new ConferenceDialog(environmentDialog, environmentDialog.getRolesModel());
		}
		if(service instanceof GameService){
			dialog = new GameServiceDialog(environmentDialog, environmentDialog.getRolesModel());
		}
		
		dialog.setVisible(true);
		if(dialog.getObject() != null){
			environmentDialog.addEnvironmentItem(dialog.getObject());
		}
		
	}
}
