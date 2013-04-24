package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import org.imsglobal.jaxb.ld.ActivityStructure;
import org.imsglobal.jaxb.ld.LearningActivity;
import org.imsglobal.jaxb.ld.SupportActivity;
import org.sakaiproject.authoring.dialog.NameDialog;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.jgraphx.AuthoringCellFactory;
import org.sakaiproject.authoring.model.ActivitiesModel;
import org.sakaiproject.authoring.utils.Bundle;

public class AddActivityStructureAction implements ActionListener {

	private Graph graph;
	private ActivitiesModel activitiesModel;

	public AddActivityStructureAction(Graph graph, ActivitiesModel activitiesModel) {
		this.graph = graph;
		this.activitiesModel = activitiesModel;
	}

	public void actionPerformed(ActionEvent evt) {

		String name = new NameDialog(Bundle
				.getString("title.activitystructure"), "").getName();

		if (name == null || name.equals("")) {
			//DEBUG
			// System.out.println("AddActivityStructureAction - actionPeformed - name is null or blank");

			return;
		}
		
		Set<Object> activitiesSelected = getActivitiesSelected();

		ActivityStructure structure = activitiesModel
				.addStructureActivity(activitiesSelected, name);

		groupCellsForActivityStructure(structure);

	}

	public Set<Object> getActivitiesSelected() {
		Cell[] selectedCells = graph.getSelectedCells();
		Set<Object> set = new HashSet<Object>();
		for (Cell cell : selectedCells) {
			if (cell.getObject() instanceof LearningActivity
					|| cell.getObject() instanceof SupportActivity) {
				set.add(cell.getObject());
			}
		}
		return set;
	}
	
	public void groupCellsForActivityStructure(ActivityStructure structure){
		
		Cell cell = AuthoringCellFactory.buildAuthoringGroupCell(structure);
			
		graph.createGroupForSelectedCells(cell);
		
	}

}
