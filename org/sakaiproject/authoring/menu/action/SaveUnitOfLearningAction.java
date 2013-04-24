package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.model.ManifestModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.GraphUtil;
import org.sakaiproject.authoring.utils.ManifestUtil;
import org.w3c.dom.Node;

public class SaveUnitOfLearningAction extends AbstractAction {

	
	private static final long serialVersionUID = 7252716992011450986L;
	
	private ManifestModel manifestModel;

	public SaveUnitOfLearningAction(ManifestModel manifestModel) {
		super(Bundle.getString("menu.save"));
		this.manifestModel = manifestModel;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			
			if(manifestModel.getManifestFile() == null){
				new SaveAsUnitOfLearningAction(manifestModel).actionPerformed(e);
				return;
			}
			
			ManifestUtil.saveManifestInXML(manifestModel.getManifestFile(), 
					manifestModel.getManifest(), 
					manifestModel.getLearningDesignModel().createLearningDesign(true));
			GraphUtil.saveGraphInFile(manifestModel.getGraphFile(), 
					(Node)manifestModel.getLearningDesignModel().getGraph().getXML());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(Authoring.getInstance(), Bundle
					.getString("error.cannotsavefile"), Bundle.getString("error.error"),
					JOptionPane.ERROR_MESSAGE);
			
			e1.printStackTrace();
		}
	}

}
