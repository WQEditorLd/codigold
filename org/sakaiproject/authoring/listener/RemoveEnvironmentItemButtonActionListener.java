package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.dialog.EnvironmentDialog;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class RemoveEnvironmentItemButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = -5111187807220030909L;
	
	private EnvironmentDialog environmentDialog;
	
	public RemoveEnvironmentItemButtonActionListener(EnvironmentDialog environmentDialog){
		
		super(Bundle.getString("button.remove"), FileUtil.getImageIcon("image.removebutton"));
		
		this.environmentDialog = environmentDialog;
		
	}

	 
	public void actionPerformed(ActionEvent arg0) {	
		environmentDialog.removeSelectedItem();
	}
	
}
