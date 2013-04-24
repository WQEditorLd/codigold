package org.sakaiproject.authoring.parser;

import org.imsglobal.jaxb.ld.Act;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.jgraphx.AuthoringCellFactory;

public class ActParser {

	public Cell getCell(Act act, Graph graph) {
		// TODO: verificar cell.... chamada estava duplicada ?!
		// Cell cell = AuthoringCellFactory.buildAuthoringCell(act);
		//graph.insertCell(cell);
		return AuthoringCellFactory.buildAuthoringCell(act);
	}

}
