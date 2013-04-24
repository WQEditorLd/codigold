package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.imsglobal.jaxb.content.Manifest;
import org.imsglobal.jaxb.ld.LearningDesign;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.dialog.NameDialog;
import org.sakaiproject.authoring.model.ManifestModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.ManifestUtil;

//TODO: PRINCIPAL! BUG: limpar o graph.xml e a área correspondente quando ocorrer NEWUOL.
public class NewUnitOfLearningAction extends AbstractAction {
	
	private static final long serialVersionUID = -8670536637309023556L;
	
	public NewUnitOfLearningAction(ManifestModel manifestModel){
		super(Bundle.getString("menu.new"));
	}
	
	public void actionPerformed(ActionEvent e) {
		Manifest manifest = new Manifest();
		LearningDesign ld = new LearningDesign();
		
		String name = new NameDialog(Bundle
				.getString("title.unitoflearning"), "").getName();

		if (name == null ) {
			return;
		}
		
		ld.setTitle(name);
		ManifestUtil.setLearningDesignInManifest(manifest, ld);
		
		Authoring.getInstance().setManifest(manifest);
		
	}	
}
