package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.LearningActivity;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.panel.LearningActivityPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class LearningActivityDialog extends JDialog implements ObjectDialog<LearningActivity> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.learningactivity");
	
	private LearningActivityPanel panel;
	
	public LearningActivityDialog(EnvironmentsModel environmentModel){
		super(Authoring.getInstance(), true);
		
		setLayout(new BorderLayout());
			
		panel = new LearningActivityPanel(environmentModel);		
		add(panel);
		add(new ObjectDialogButtonBarPanel(this), BorderLayout.SOUTH);
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	
	public LearningActivity getObject() {
		return panel.getObject();
	}

	public void setObject(LearningActivity activity) {
		panel.setObject(activity);	
	}
	
	public void populateObject() {
		panel.populateObject();
	}
	
}
