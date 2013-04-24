package org.sakaiproject.authoring.listener;

import org.sakaiproject.authoring.event.EventListener;
import org.sakaiproject.authoring.panel.LabelsGridPanel;

public class ObjectChangedLabelsGridListener implements EventListener {

	private LabelsGridPanel labelsGridPanel;

	public ObjectChangedLabelsGridListener(LabelsGridPanel labelsGridPanel) {
		this.labelsGridPanel = labelsGridPanel;
	}

	 
	public void invoke(Object... args) {
		labelsGridPanel.revalidate();
		labelsGridPanel.repaint();
	}

}
