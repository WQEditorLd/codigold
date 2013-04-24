package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.Staff;
import org.sakaiproject.authoring.dialog.ObjectDialog;
import org.sakaiproject.authoring.dialog.LearnerDialog;
import org.sakaiproject.authoring.dialog.StaffDialog;
import org.sakaiproject.authoring.event.EventController;
import org.sakaiproject.authoring.listener.AddRoleButtonActionListener;
import org.sakaiproject.authoring.listener.ObjectAddedLabelsGridListener;
import org.sakaiproject.authoring.listener.ObjectChangedLabelsGridListener;
import org.sakaiproject.authoring.listener.ObjectRemovedLabelsGridListener;
import org.sakaiproject.authoring.listener.RemoveRoleButtonActionListener;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.utils.Bundle;

public class RolesPanel extends JPanel {
	
	private static final long serialVersionUID = 1044365692543857400L;

	public static final String TITLE = Bundle.getString("title.roles");
	
	private LabelsGridPanel labelsGridPanel;
	
	public RolesPanel(RolesModel rolesModel){
		
		Map<Class<?>, ObjectDialog> dialogs = new HashMap<Class<?>, ObjectDialog>();
		dialogs.put(Learner.class, new LearnerDialog());
		dialogs.put(Staff.class, new StaffDialog());
		labelsGridPanel = new LabelsGridPanel(dialogs);
		
		setLayout(new BorderLayout());
		add(labelsGridPanel, BorderLayout.CENTER);
		
		EventController.getInstance().addListener(RolesModel.ROLE_ADDED_EVENT, 
				new ObjectAddedLabelsGridListener(labelsGridPanel));
		EventController.getInstance().addListener(RolesModel.ROLE_CHANGED_EVENT, 
				new ObjectChangedLabelsGridListener(labelsGridPanel));
		EventController.getInstance().addListener(RolesModel.ROLE_REMOVED_EVENT, 
				new ObjectRemovedLabelsGridListener(labelsGridPanel));
		
		labelsGridPanel.getToolBar().add(new AddRoleButtonActionListener(rolesModel));
		labelsGridPanel.getToolBar().add(new RemoveRoleButtonActionListener(rolesModel, labelsGridPanel));
	}
	
}
