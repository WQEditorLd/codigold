package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.dialog.LearningDesignObjectivesDialog;
import org.sakaiproject.authoring.model.LearningDesignModel;
import org.sakaiproject.authoring.utils.Bundle;

public class EditObjectivesAction extends AbstractAction {
	
	private static final long serialVersionUID = -7394692632572808314L;
	
	private LearningDesignModel learningDesignModel;
	
	public EditObjectivesAction(LearningDesignModel learningDesignModel){
		super(Bundle.getString("menu.objectives"));
		this.learningDesignModel = learningDesignModel;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(learningDesignModel.getLearningDesign() != null){
			LearningDesignObjectivesDialog dialog = new LearningDesignObjectivesDialog();
			dialog.setObject(learningDesignModel.getLearningDesign());
			dialog.setVisible(true);
		}
	}	
}
