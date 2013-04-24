package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.imsglobal.jaxb.ld.Environment;
import org.sakaiproject.authoring.dialog.EnvironmentDialog;
import org.sakaiproject.authoring.dialog.ObjectDialog;
import org.sakaiproject.authoring.event.EventController;
import org.sakaiproject.authoring.listener.AddEnvironmentButtonActionListener;
import org.sakaiproject.authoring.listener.ObjectAddedLabelsGridListener;
import org.sakaiproject.authoring.listener.ObjectChangedLabelsGridListener;
import org.sakaiproject.authoring.listener.ObjectRemovedLabelsGridListener;
import org.sakaiproject.authoring.listener.RemoveEnvironmentButtonActionListener;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.utils.Bundle;

public class EnvironmentsPanel extends JPanel {
	
	private static final long serialVersionUID = 1044365692543857400L;

	public static final String TITLE = Bundle.getString("title.environments");
	
	private LabelsGridPanel labelsGridPanel;
	
	public EnvironmentsPanel(EnvironmentsModel environmentsModel, RolesModel rolesModel){
		
		Map<Class<?>, ObjectDialog> dialogs = new HashMap<Class<?>, ObjectDialog>();
		dialogs.put(Environment.class, new EnvironmentDialog(rolesModel));	
		labelsGridPanel = new LabelsGridPanel(dialogs);
		
		setLayout(new BorderLayout());
		add(labelsGridPanel, BorderLayout.CENTER);
		
		EventController.getInstance().addListener(EnvironmentsModel.ENVIRONMENT_ADDED_EVENT, 
				new ObjectAddedLabelsGridListener(labelsGridPanel));
		EventController.getInstance().addListener(EnvironmentsModel.ENVIRONMENT_CHANGED_EVENT, 
				new ObjectChangedLabelsGridListener(labelsGridPanel));
		EventController.getInstance().addListener(EnvironmentsModel.ENVIRONMENT_REMOVED_EVENT, 
				new ObjectRemovedLabelsGridListener(labelsGridPanel));
		
		labelsGridPanel.getToolBar().add(new AddEnvironmentButtonActionListener(environmentsModel));
		labelsGridPanel.getToolBar().add(new RemoveEnvironmentButtonActionListener(environmentsModel, labelsGridPanel));
	}
	
}
