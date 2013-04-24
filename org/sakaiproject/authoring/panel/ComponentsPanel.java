package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.sakaiproject.authoring.accordion.JAccordion;
import org.sakaiproject.authoring.model.ComponentsModel;

public class ComponentsPanel extends JPanel {

	private static final long serialVersionUID = -7206006251783274808L;
	
	//subviews
	private ActivitiesPanel activitiesPanel;
	private RolesPanel rolesPanel;
	private EnvironmentsPanel environmentsPanel;
	
	public ComponentsPanel(ComponentsModel componentsModel){
	
		activitiesPanel = new ActivitiesPanel(componentsModel.getActivitiesModel(), 
				componentsModel.getEnvironmentsModel());
		rolesPanel = new RolesPanel(componentsModel.getRolesModel());
		environmentsPanel = new EnvironmentsPanel(componentsModel.getEnvironmentsModel(),
				componentsModel.getRolesModel());
		
		JAccordion accordion = new JAccordion();
		accordion.setPreferredSize(new Dimension(200,100));
		
		accordion.addBar(RolesPanel.TITLE, rolesPanel);
		accordion.addBar(ActivitiesPanel.TITLE, activitiesPanel);
		accordion.addBar(EnvironmentsPanel.TITLE, environmentsPanel);
		
		setLayout(new BorderLayout());
		add(accordion, BorderLayout.CENTER);
		
	}
	
	

}
