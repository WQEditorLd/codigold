package org.sakaiproject.authoring.parser;

import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.ld.Act;
import org.imsglobal.jaxb.ld.Play;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;

public class PlayParser {

	public Cell getCell(Play play, Graph graph){
		Cell lastCell = null;
		ActParser actParser = new ActParser();
		
		List<Object> cells = new ArrayList<Object>();
		
		for(Act act : play.getActList()){
			Cell cell = actParser.getCell(act, graph);
			cells.add(cell);
			if(lastCell != null){
				Object edge = graph.connectCells(lastCell, cell);
				cells.add(edge);
			}
			lastCell = cell;
		}
		
		return null;
		
	}
}
