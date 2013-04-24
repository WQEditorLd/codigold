package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.LearningDesign;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class LearningDesignPrerequisitesDialog extends JDialog implements ObjectDialog<LearningDesign> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.learningdesignprerequisites");
	
	private ItemModelPanel prerequisitesPanel;
	
	private LearningDesign ld;

	public LearningDesignPrerequisitesDialog(){
		super(Authoring.getInstance(), true);
		
		setLayout(new BorderLayout());
		
		
		prerequisitesPanel = new ItemModelPanel(Bundle.getString("panel.prerequisites"));
		add(prerequisitesPanel, BorderLayout.CENTER);
		
		
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
		
		if(ld.getPrerequisites() != null){
			prerequisitesPanel.setItemModel(ld.getPrerequisites());
		}
	}
	
	public void populateObject() {
		ld.setPrerequisites(prerequisitesPanel.getItemModel());
	}

	
}
