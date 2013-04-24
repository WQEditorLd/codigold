package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.sakaiproject.authoring.model.LearningDesignModel;


public class LearningDesignPanel extends JPanel {
	
	private static final long serialVersionUID = -3930436396640088212L;
	
	//subviews
	private ComponentsPanel componentsPanel;
	private GraphPanel graphPanel;
	
	//model
	// private LearningDesignModel learningDesignModel;
		
	public LearningDesignPanel(LearningDesignModel learningDesignModel){
		
		// this.learningDesignModel = learningDesignModel;
		componentsPanel = new ComponentsPanel(learningDesignModel.getComponentsModel());
		graphPanel = new GraphPanel(learningDesignModel);

		setLayout(new BorderLayout());	
		add(componentsPanel, BorderLayout.WEST);
		add(graphPanel, BorderLayout.CENTER);
		
	}

//	public LearningDesignModel getLearningDesignModel() {
//		return learningDesignModel;
//	}
//
//	public void setLearningDesignModel(LearningDesignModel learningDesignModel) {
//		this.learningDesignModel = learningDesignModel;
//	}

	
}

