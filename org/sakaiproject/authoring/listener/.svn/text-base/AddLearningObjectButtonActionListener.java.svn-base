package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.dialog.EnvironmentDialog;
import org.sakaiproject.authoring.dialog.LearningObjectDialog;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class AddLearningObjectButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	private EnvironmentDialog environmentDialog;

	public AddLearningObjectButtonActionListener(EnvironmentDialog dialog) {
		
		super(Bundle.getString("button.newlearningobject"), FileUtil.getImageIcon("image.learningobjecticon"));
		
		this.environmentDialog = dialog;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		LearningObjectDialog dialog = new LearningObjectDialog(environmentDialog);
		dialog.setVisible(true);
		
		if (dialog.getObject() != null) {
			environmentDialog.addEnvironmentItem(dialog.getObject());
		}
	}
}
