package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import org.imsglobal.jaxb.ld.ActivityStructure;
import org.imsglobal.jaxb.ld.Environment;
import org.imsglobal.jaxb.ld.EnvironmentRef;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.panel.ActivityGeneralPanel;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class ActivityStructureDialog extends JDialog implements ObjectDialog<ActivityStructure> {

	private static final long serialVersionUID = -764080218656525608L;

	private static final String TITLE = Bundle.getString("title.activitystructure");
	
	private ActivityGeneralPanel generalPanel;
	private ItemModelPanel informationPanel;
	
	private ActivityStructure activity;

	public ActivityStructureDialog(EnvironmentsModel environmentModel){
		super(Authoring.getInstance(), true);
		
		setLayout(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		generalPanel = new ActivityGeneralPanel(environmentModel); 
		tabbedPane.add(Bundle.getString("panel.general"), generalPanel);
		
		informationPanel = new ItemModelPanel(Bundle.getString("panel.information"));
		tabbedPane.add(Bundle.getString("panel.information"), informationPanel);
		
		add(tabbedPane, BorderLayout.CENTER);
		add(new ObjectDialogButtonBarPanel(this), BorderLayout.SOUTH);
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	
	public ActivityStructure getObject() {
		return activity;
	}

	public void setObject(ActivityStructure activity) {
		this.activity = activity;
		
		generalPanel.setTitle(activity.getTitle());
		
		List<Environment> environments = new ArrayList<Environment>();
		for(EnvironmentRef ref : activity.getEnvironmentRef()){
			environments.add((Environment)ref.getRef());
		}
		generalPanel.setEnvironmentsSelected(environments);
		
		if(activity.getInformation() != null){
			informationPanel.setItemModel(activity.getInformation());
		}
	}
	
	public void populateObject() {
		activity.setTitle(generalPanel.getTitle());
		activity.setInformation(informationPanel.getItemModel());
		
		List<EnvironmentRef> refs = new ArrayList<EnvironmentRef>();
		for(Environment environment : generalPanel.getEnvironmentsSelected()){
			EnvironmentRef ref = new EnvironmentRef();
			ref.setRef(environment);
			refs.add(ref);
		}
		activity.getEnvironmentRef().clear();
		activity.getEnvironmentRef().addAll(refs);
	}
	
	
	
}
