package org.sakaiproject.authoring.listener;

import org.sakaiproject.authoring.event.EventListener;
import org.sakaiproject.authoring.label.Label;
import org.sakaiproject.authoring.panel.LabelsGridPanel;

public class ObjectAddedLabelsGridListener implements EventListener {
	
	private LabelsGridPanel labelsGridPanel;

	public ObjectAddedLabelsGridListener(LabelsGridPanel labelsGridPanel) {
		this.labelsGridPanel = labelsGridPanel;
	}

	 
	public void invoke(Object... args) {
		Label label = new Label(args[0], labelsGridPanel);
		labelsGridPanel.add(label);
	}

}
