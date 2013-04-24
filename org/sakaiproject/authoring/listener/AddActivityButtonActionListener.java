package org.sakaiproject.authoring.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.sakaiproject.authoring.dialog.ActivityMiniDialog;
import org.sakaiproject.authoring.model.ActivitiesModelInterface;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

public class AddActivityButtonActionListener extends AbstractAction {
	
	private static final long serialVersionUID = -5111187807220030909L;
	
	private ActivitiesModelInterface activitiesModel;
	
	public AddActivityButtonActionListener(ActivitiesModelInterface activitiesModel){
		// TODO: check this bundle ?
		super(Bundle.getString("button.newstaffrole"), FileUtil.getImageIcon("image.plusicon"));
		
		this.activitiesModel = activitiesModel;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		ActivityMiniDialog dialog = new ActivityMiniDialog();
		dialog.setVisible(true);
		
		if (dialog.getActivity() != null) {
			activitiesModel.addActivity(dialog.getActivity());
		}
	}
	
}
