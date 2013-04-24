package org.sakaiproject.authoring.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.ld.Act;
import org.imsglobal.jaxb.ld.Play;
import org.sakaiproject.authoring.dialog.NameDialog;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.jgraphx.AuthoringCellFactory;
import org.sakaiproject.authoring.utils.Bundle;

public class AddPlayAction implements ActionListener {

	private Graph graph;

	public AddPlayAction(Graph graph) {
		this.graph = graph;
	}

	public void actionPerformed(ActionEvent evt) {

		String name = new NameDialog(Bundle
				.getString("title.play"), "").getName();

		if (name == null || name.equals("")) {
			return;
		}

		List<Act> acts = getActsSequence();
		
		Play play = new Play();
		play.setIdentifier("Play"+Integer.toString((int)(Math.random()*100000)));
		play.setTitle(name);
		play.getActList().addAll(acts);
		
		groupCellsForPlay(play);

	}

	
	private List<Act> getActsSequence() {
		List<Act> actList = new ArrayList<Act>();
		Cell currentCell = graph.getStartCellPath(graph.getSelectedCells());
		actList.add((Act)currentCell.getObject());
		for(int i = 0; i < graph.getSelectedCells().length; i++){
			Cell[] successors = graph.getSuccessors(currentCell);
			if(successors == null || successors.length == 0){
				break;
			}
			actList.add((Act)successors[0].getObject());
			currentCell = successors[0];
		}
		return actList;
	}

	

	public void groupCellsForPlay(Play play){
		Cell cell = AuthoringCellFactory.buildAuthoringGroupCell(play);
		graph.createGroupForSelectedCells(cell);
	}

}
