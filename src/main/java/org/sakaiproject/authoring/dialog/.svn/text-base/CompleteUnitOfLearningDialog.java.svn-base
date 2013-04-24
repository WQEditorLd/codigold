package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.LearningDesign;
import org.imsglobal.jaxb.ld.Method;
import org.imsglobal.jaxb.ld.Play;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.CompleteUnitOfLearningPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class CompleteUnitOfLearningDialog extends JDialog implements ObjectDialog<LearningDesign> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.learningdesignobjetives");
	
	private CompleteUnitOfLearningPanel completePanel;
	
	private LearningDesign ld;

	public CompleteUnitOfLearningDialog(List<Play> plays){
		
		super(Authoring.getInstance(), true);
		
		setLayout(new BorderLayout());
		
		completePanel = new CompleteUnitOfLearningPanel(plays);
		add(completePanel, BorderLayout.CENTER);
		
		
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
		
		if(ld.getMethod() != null && ld.getMethod().getCompleteUnitOfLearning() != null){
			completePanel.setCompleteUoL(ld.getMethod().getCompleteUnitOfLearning());
		}
	}
	
	public void populateObject() {
		if(ld.getMethod() == null){
			ld.setMethod(new Method());
		}
		ld.getMethod().setCompleteUnitOfLearning(completePanel.getCompleteUol());
	}

	
}
