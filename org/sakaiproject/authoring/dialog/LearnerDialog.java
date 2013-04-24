package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import org.imsglobal.jaxb.ld.Learner;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.panel.RoleGeneralPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class LearnerDialog extends JDialog implements ObjectDialog<Learner>{

	private static final long serialVersionUID = -6186101235667979250L;
		
	private RoleGeneralPanel generalPanel;
	private ItemModelPanel informationPanel;

	private JTabbedPane tabbedPane;
	
	private Learner learner;
	
	public LearnerDialog() {
		super(Authoring.getInstance(), true);
		
		tabbedPane = new JTabbedPane();
		
		generalPanel = new RoleGeneralPanel(); 
		tabbedPane.add(Bundle.getString("panel.general"), generalPanel);
		
		informationPanel = new ItemModelPanel(Bundle.getString("panel.information"));
		tabbedPane.add(Bundle.getString("panel.informations"), informationPanel);	
		
		
		add(tabbedPane, BorderLayout.CENTER);
		add(new ObjectDialogButtonBarPanel(this), BorderLayout.SOUTH);
		
		setTitle(Bundle.getString("title.learner"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	
	public void populateObject() {
		generalPanel.populateLearner(learner);
		learner.setInformation(informationPanel.getItemModel());
	}
	
	
	public void setObject(Learner learner){
		this.learner = learner;
		generalPanel.populatePanel(learner);
		if(learner.getInformation() != null){
			informationPanel.setItemModel(learner.getInformation());
		}
	}

	public Learner getObject() {
		return learner;
	}

}
