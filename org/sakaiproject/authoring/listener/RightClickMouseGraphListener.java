package org.sakaiproject.authoring.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.menu.GraphPopupMenu;
import org.sakaiproject.authoring.model.LearningDesignModel;

public class RightClickMouseGraphListener extends MouseAdapter {
	
	private GraphPopupMenu popup;
	
	public RightClickMouseGraphListener(Graph graph, LearningDesignModel ldModel) {
		popup = new GraphPopupMenu(graph, ldModel);
	}
	
	public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }

	
	

}
