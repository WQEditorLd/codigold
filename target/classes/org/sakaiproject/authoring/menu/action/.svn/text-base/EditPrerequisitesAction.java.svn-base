package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.dialog.LearningDesignPrerequisitesDialog;
import org.sakaiproject.authoring.model.LearningDesignModel;
import org.sakaiproject.authoring.utils.Bundle;

public class EditPrerequisitesAction extends AbstractAction {
	
	private static final long serialVersionUID = -7394692632572808314L;
	
	private LearningDesignModel learningDesignModel;
	
	public EditPrerequisitesAction(LearningDesignModel learningDesignModel){
		super(Bundle.getString("menu.prerequisites"));
		this.learningDesignModel = learningDesignModel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(learningDesignModel.getLearningDesign() != null){
			LearningDesignPrerequisitesDialog dialog = new LearningDesignPrerequisitesDialog();
			dialog.setObject(learningDesignModel.getLearningDesign());
			dialog.setVisible(true);
		}
	}	
}
