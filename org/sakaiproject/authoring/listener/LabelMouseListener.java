package org.sakaiproject.authoring.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.sakaiproject.authoring.label.Label;
import org.sakaiproject.authoring.panel.LabelsGridPanel;

public class LabelMouseListener extends MouseAdapter {
	
	private Label label;
	private LabelsGridPanel labelsGridPanel;
	
	public LabelMouseListener(Label label, LabelsGridPanel labelsGridPanel){
		this.label = label;
		this.labelsGridPanel = labelsGridPanel;
	}
	
	
	public void mousePressed(MouseEvent e)
	{
		if(e.getClickCount() == 1){
			labelsGridPanel.setSelectedLabel(label);
		}
		if(e.getClickCount() == 2){
			labelsGridPanel.showDialogFromLabel(label);
		}
		
	}
}
