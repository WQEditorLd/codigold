package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.label.Label;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.panel.LabelsGridPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class RemoveRoleButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = -5111187807220030909L;
	
	private RolesModel rolesModel;
	
	private LabelsGridPanel labelsGridPanel;
	
	public RemoveRoleButtonActionListener(RolesModel rolesModel, 
			LabelsGridPanel labelsGridPanel){
		
		super(Bundle.getString("button.remove"), FileUtil.getImageIcon("image.removebutton"));
		this.labelsGridPanel = labelsGridPanel;
		this.rolesModel = rolesModel;
	}

	 
	public void actionPerformed(ActionEvent arg0) {
		
		Label label = labelsGridPanel.getSelectedLabel();
		rolesModel.removeRole(label.getObject());
	}
	
}
