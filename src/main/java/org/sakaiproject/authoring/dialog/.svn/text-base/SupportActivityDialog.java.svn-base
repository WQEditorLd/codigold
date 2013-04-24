package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import org.imsglobal.jaxb.ld.Environment;
import org.imsglobal.jaxb.ld.EnvironmentRef;
import org.imsglobal.jaxb.ld.SupportActivity;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.panel.ActivityGeneralPanel;
import org.sakaiproject.authoring.panel.CompleteActivityPanel;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class SupportActivityDialog extends JDialog implements ObjectDialog<SupportActivity> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.learningactivity");
	
	private ActivityGeneralPanel generalPanel;
	private ItemModelPanel descriptionPanel;
	private CompleteActivityPanel completeActivityPanel;
	
	private JTabbedPane tabbedPane;
	
	private SupportActivity activity;

	public SupportActivityDialog(EnvironmentsModel environmentModel){
		super(Authoring.getInstance(), true);
		
		setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane();
		
		generalPanel = new ActivityGeneralPanel(environmentModel); 
		tabbedPane.add(Bundle.getString("panel.general"), generalPanel);
		
		descriptionPanel = new ItemModelPanel(Bundle.getString("panel.description"));
		tabbedPane.add(Bundle.getString("panel.description"), descriptionPanel);
		
		completeActivityPanel = new CompleteActivityPanel();
		tabbedPane.add(Bundle.getString("panel.completeactivity"), completeActivityPanel);
	
		add(tabbedPane, BorderLayout.CENTER);
		add(new ObjectDialogButtonBarPanel(this), BorderLayout.SOUTH);
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}
	
	
	public SupportActivity getObject() {
		return activity;
	}

	public void setObject(SupportActivity activity) {
		
		this.activity = activity;
		
		generalPanel.setTitle(activity.getTitle());
		if(activity.getActivityDescription() != null){
			descriptionPanel.setItemModel(activity.getActivityDescription());
		}
		if(activity.getCompleteActivity() != null){
			completeActivityPanel.setCompleteActivity(activity.getCompleteActivity());
		}
		
		List<Environment> environments = new ArrayList<Environment>();
		for(EnvironmentRef ref : activity.getEnvironmentRefList()){
			environments.add((Environment)ref.getRef());
		}
		generalPanel.setEnvironmentsSelected(environments);
		
	}
	
	public void populateObject() {
		activity.setTitle(generalPanel.getTitle());
		activity.setActivityDescription(descriptionPanel.getItemModel());
		activity.setCompleteActivity(completeActivityPanel.getCompleteActivity());
		
		List<EnvironmentRef> refs = new ArrayList<EnvironmentRef>();
		for(Environment environment : generalPanel.getEnvironmentsSelected()){
			EnvironmentRef ref = new EnvironmentRef();
			ref.setRef(environment);
			refs.add(ref);
		}
		activity.getEnvironmentRefList().clear();
		activity.getEnvironmentRefList().addAll(refs);
	}
	

	
}
