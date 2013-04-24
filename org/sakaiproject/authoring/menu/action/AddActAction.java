package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.imsglobal.jaxb.ld.Act;
import org.imsglobal.jaxb.ld.RolePart;
import org.sakaiproject.authoring.dialog.NameDialog;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.jgraphx.AuthoringCellFactory;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.RolePartUtil;

public class AddActAction implements ActionListener {

	private Graph graph;

	public AddActAction(Graph graph) {
		this.graph = graph;
	}

	public void actionPerformed(ActionEvent evt) {

		String name = new NameDialog(Bundle
				.getString("title.act"), "").getName();

		if (name == null || name.equals("")) {
			return;
		}

		List<RolePart> roleParts = RolePartUtil.getRolePartsSelected(graph);
		
		Act act = new Act();
		act.setIdentifier("Act"+Integer.toString((int)(Math.random()*100000)));
		act.setTitle(name);
		act.getRolePartList().addAll(roleParts);
		
		groupCellsForAct(act);

	}

		
	public void groupCellsForAct(Act act){
		Cell cell = AuthoringCellFactory.buildAuthoringGroupCell(act);
		graph.createGroupForSelectedCells(cell);
	}

}
