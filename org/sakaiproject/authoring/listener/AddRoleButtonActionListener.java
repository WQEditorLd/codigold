package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.dialog.RoleMiniDialog;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class AddRoleButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = -5111187807220030909L;
	
	private RolesModel rolesModel;
	
	public AddRoleButtonActionListener(RolesModel rolesModel){
		// TODO: check this bundle ? -staff only ? and learners ??
		super(Bundle.getString("button.newstaffrole"), FileUtil.getImageIcon("image.plusicon"));
		
		this.rolesModel = rolesModel;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		RoleMiniDialog dialog = new RoleMiniDialog();
		dialog.setVisible(true);
		
		if (dialog.getRole() != null) {
			rolesModel.addRole(dialog.getRole());
		}
	}
	
}
