package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.sakaiproject.authoring.dialog.ObjectDialog;
import org.sakaiproject.authoring.event.EventController;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.listener.GraphRepaintListener;
import org.sakaiproject.authoring.listener.ObjectRemovedGraphListener;
import org.sakaiproject.authoring.model.ActivitiesModel;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.model.LearningDesignModel;
import org.sakaiproject.authoring.model.RolesModel;


public class GraphPanel extends JPanel {
	
	private static final long serialVersionUID = -203971974977061258L;

	private Graph graph;
	
	public GraphPanel(LearningDesignModel ldModel) {
		
		graph = ldModel.getGraph();
		
		setLayout(new BorderLayout());
		add(graph.getSwingComponent(), BorderLayout.CENTER);	
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		
		GraphRepaintListener repaintListener = new GraphRepaintListener(graph);
		EventController.getInstance().addListener(ObjectDialog.OBJECT_CHANGE_EVENT, repaintListener);
		
		ObjectRemovedGraphListener removeListener = new ObjectRemovedGraphListener(graph);
		EventController.getInstance().addListener(RolesModel.ROLE_REMOVED_EVENT, removeListener);
		EventController.getInstance().addListener(ActivitiesModel.ACTIVITY_REMOVED_EVENT, removeListener);
		EventController.getInstance().addListener(EnvironmentsModel.ENVIRONMENT_REMOVED_EVENT, removeListener);
		
	}


	public Graph getGraph() {
		return graph;
	}
	
}
