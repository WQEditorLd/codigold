package org.sakaiproject.authoring.jgraphx;

import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.style.AuthoringStyle;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxRectangle;

public class AuthoringCellFactory {

	public static Cell buildAuthoringCell(Object value) {
		AuthoringCell cell = new AuthoringCell();
		cell.setValue(value);
		cell.setVertex(true);
		cell.setGeometry(new mxGeometry(0, 0, AuthoringStyle.CELL_SIZE,
				AuthoringStyle.CELL_SIZE));
		cell.setStyle(AuthoringStyle.getDefaultCellStyle(
				cell.getValue().getClass()));

		// DEBUG
//		System.out.println("buildAuthoringCell"
//				+ cell.getValue().getClass().toString());

		return cell;
	}

	public static Cell buildAuthoringGroupCell(Object value) {

		AuthoringCell cell = new AuthoringCell();
		cell.setValue(value);
		cell.setVertex(true);

		// DEBUG
//		System.out.println("buildAuthoringGroupCell"
//				+ cell.getValue().getClass().toString());

		mxGeometry geometry = new mxGeometry();
		geometry.setAlternateBounds(new mxRectangle(0, 0,
				AuthoringStyle.CELL_SIZE + 4, AuthoringStyle.CELL_SIZE + 5));
		cell.setGeometry(geometry);
		cell.setStyle(AuthoringStyle.getDefaultCellStyle(
						cell.getValue().getClass()));
						
		return cell;
	}

}
