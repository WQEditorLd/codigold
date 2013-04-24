package org.sakaiproject.authoring.listener;

import org.sakaiproject.authoring.event.EventListener;
import org.sakaiproject.authoring.graph.Graph;

public class GraphRepaintListener implements EventListener {
	
	private Graph graph;

	public GraphRepaintListener(Graph graph) {
		this.graph = graph;
	}

	 
	public void invoke(Object... args) {
		graph.getSwingComponent().repaint();
	}

}
