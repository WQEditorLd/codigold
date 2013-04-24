package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.label.Label;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.panel.LabelsGridPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class RemoveEnvironmentButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = -5111187807220030909L;
	
	private EnvironmentsModel environmentsModel;
	
	private LabelsGridPanel labelsGridPanel;
	
	public RemoveEnvironmentButtonActionListener(EnvironmentsModel environmentsModel, 
			LabelsGridPanel labelsGridPanel){
		
		super(Bundle.getString("button.remove"), FileUtil.getImageIcon("image.removebutton"));
		
		this.environmentsModel = environmentsModel;
		this.labelsGridPanel = labelsGridPanel;
	}

	 
	public void actionPerformed(ActionEvent arg0) {
		
		Label label = labelsGridPanel.getSelectedLabel();
		environmentsModel.removeEnvironment(label.getObject());
	}
	
}
