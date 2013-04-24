package org.sakaiproject.authoring.listener;

import org.sakaiproject.authoring.event.EventListener;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;

public class ObjectRemovedGraphListener implements EventListener {
	
	private Graph graph;

	public ObjectRemovedGraphListener(Graph graph) {
		this.graph = graph;
	}

	 
	public void invoke(Object... args) {
		Cell[] cells = graph.getCellsWithObject(args[0]);
		graph.removeCells(cells);
		
		graph.getSwingComponent().revalidate();
		graph.getSwingComponent().repaint();
	}

}
