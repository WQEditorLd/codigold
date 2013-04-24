package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.LearningDesign;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class LearningDesignObjectivesDialog extends JDialog implements ObjectDialog<LearningDesign> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.learningdesignobjetives");
	
	private ItemModelPanel objectivesPanel;
	
	private LearningDesign ld;

	public LearningDesignObjectivesDialog(){
		
		super(Authoring.getInstance(), true);
		
		setLayout(new BorderLayout());
		
		
		objectivesPanel = new ItemModelPanel(Bundle.getString("panel.objective"));
		add(objectivesPanel, BorderLayout.CENTER);
		
		
		add(new ObjectDialogButtonBarPanel(this), BorderLayout.SOUTH);
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	
	public LearningDesign getObject() {
		return ld;
	}

	public void setObject(LearningDesign ld) {
		this.ld = ld;
		
		if(ld.getLearningObjectives() != null){
			objectivesPanel.setItemModel(ld.getLearningObjectives());
		}
	}
	
	public void populateObject() {
		ld.setLearningObjectives(objectivesPanel.getItemModel());
	}

	
}
