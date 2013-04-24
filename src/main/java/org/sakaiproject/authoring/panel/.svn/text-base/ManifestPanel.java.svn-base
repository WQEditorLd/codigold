package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.imsglobal.jaxb.content.Manifest;
import org.sakaiproject.authoring.menu.ManifestMenuBar;
import org.sakaiproject.authoring.model.ManifestModel;

public class ManifestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ManifestMenuBar menuBar;
	
	//model
	private ManifestModel manifestModel = new ManifestModel();
	
	//subviews
	private LearningDesignPanel learningDesignPanel;
	
	public ManifestPanel() {
		
		setLayout(new BorderLayout());
		
		menuBar = new ManifestMenuBar(manifestModel);
		add(menuBar, BorderLayout.NORTH);
		
	}

	public void setManifest(Manifest manifest) {
				
		if(learningDesignPanel != null){
			remove(learningDesignPanel);
		}
		learningDesignPanel = new LearningDesignPanel(manifestModel.getLearningDesignModel());
		add(learningDesignPanel, BorderLayout.CENTER);
		
		manifestModel.setManifest(manifest);
		
		revalidate();
		repaint();
	}

	

}
