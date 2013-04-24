package org.sakaiproject.authoring.listener;

import org.sakaiproject.authoring.event.EventListener;
import org.sakaiproject.authoring.label.Label;
import org.sakaiproject.authoring.panel.LabelsGridPanel;

public class ObjectRemovedLabelsGridListener implements EventListener {
	
	private LabelsGridPanel labelsGridPanel;

	public ObjectRemovedLabelsGridListener(LabelsGridPanel labelsGridPanel) {
		this.labelsGridPanel = labelsGridPanel;
	}

	 
	public void invoke(Object... args) {
		Label label = labelsGridPanel.getLabelFromObject(args[0]);
		labelsGridPanel.remove(label);
		labelsGridPanel.revalidate();
		labelsGridPanel.repaint();
	}

}
