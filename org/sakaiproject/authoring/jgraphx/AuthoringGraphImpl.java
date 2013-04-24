package org.sakaiproject.authoring.jgraphx;

import java.awt.event.MouseListener; // import java.awt.print.PageFormat;
// import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;

import org.imsglobal.jaxb.ld.Act;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.utils.LearningDesignUtils;
import org.sakaiproject.style.AuthoringStyle;
import org.w3c.dom.Node;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.view.mxGraph;

public class AuthoringGraphImpl extends mxGraph implements Graph {

	private mxGraphComponent graphComponent;

	public AuthoringGraphImpl() {
		graphComponent = new mxGraphComponent(this);

		// Paper paper = new Paper();
		// paper.setSize(600, 400);
		// PageFormat pageFormat = new PageFormat();
		// pageFormat.setPaper(paper);
		// graphComponent.setPageFormat(pageFormat);
		graphComponent.setPageVisible(true);

		new mxKeyboardHandler(graphComponent);
		new mxRubberband(graphComponent);

		setCellsEditable(false);

	}

	public String convertValueToString(Object cell) {
		if (cell instanceof mxCell && ((mxCell) cell).isVertex()) {
			Object value = ((mxCell) cell).getValue();

			try {
				return (String) value.getClass().getMethod("getTitle").invoke(
						value);
			} catch (Exception e) {
				// TODO: error handling - checar printStackTrace
				e.printStackTrace();
			}

		}

		return super.convertValueToString(cell);
	}

	public void cellLabelChanged(Object cell, Object newValue, boolean autoSize) {
		if (cell instanceof mxCell && newValue != null
				&& ((mxCell) cell).isVertex()) {
			Object value = ((mxCell) cell).getValue();

			try {

				value.getClass().getMethod("setTitle", String.class).invoke(
						value, newValue.toString());

				return;

			} catch (Exception e) {
				// TODO: error handling - checar printStackTrace
				e.printStackTrace();
			}
		}

		super.cellLabelChanged(cell, newValue, autoSize);
	}

	public void swapBounds(Object cell, boolean willCollapse) {

		if (willCollapse) {
			((mxCell) cell)
					.setStyle(AuthoringStyle
							.getDefaultCellStyle(((mxCell) cell).getValue()
									.getClass()));
		} else {
			((mxCell) cell).setStyle("");
		}

		super.swapBounds(cell, willCollapse);
	}

	public JComponent getSwingComponent() {
		return graphComponent;
	}

	public void addMouseListener(MouseListener mouseListener) {
		graphComponent.getGraphControl().addMouseListener(mouseListener);
	}

	public void removeCell(Cell cell) {
		removeCells(new mxCell[] { (mxCell) cell });
	}

	public Cell[] getCellsWithObject(Object object) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		Object[] cells = ((mxGraphModel) getModel()).getCells().values()
				.toArray();
		for (Object cell : cells) {
			if (cell instanceof Cell
					&& ((Cell) cell).getObject().equals(object)) {
				list.add((Cell) cell);
			}
		}
		return list.toArray(new Cell[0]);
	}

	public Cell[] getSelectedCells() {
		// DEBUG:
		System.out.println("getSelectedCells");

		List<AuthoringCell> cells = new ArrayList<AuthoringCell>();

		for (Object object : getSelectionCells()) {
			// DEBUG:
			System.out.println("Cell?" + object.getClass() + "Value:" + object);

			if (object instanceof AuthoringCell) {
				cells.add((AuthoringCell) object);

				System.out.println("Cell:" + object.getClass() + " id="
						+ ((AuthoringCell) object).getId());
			}
		}
		return cells.toArray(new Cell[] {});
	}

	public void removeCells(Cell[] cells) {
		super.removeCells(cells);
	}

	public void createGroupForSelectedCells(Cell cell) {
		groupCells(cell, 60);
		foldCells(true, false, new Object[] { cell });
	}

	public Object addEdge(Object edge, Object parent, Object source,
			Object target, Integer index) {

		mxCell sourceCell = (mxCell) source;
		mxCell targetCell = (mxCell) target;

		if (sourceCell == null || targetCell == null) {
			return null;
		}

		mxCell edgeCell = (mxCell) edge;
		edgeCell.setStyle("shape=connector;endArrow=classic");

		Object sourceObject = sourceCell.getValue();
		Object targetObject = targetCell.getValue();

		if ((LearningDesignUtils.isActivity(sourceObject) && LearningDesignUtils
				.isRole(targetObject))
				|| (LearningDesignUtils.isActivity(targetObject) && LearningDesignUtils
						.isRole(sourceObject))
				|| (sourceObject instanceof Act && targetObject instanceof Act)) {
			return super.addEdge(edge, parent, source, target, index);
		}

		return null;
	}

	public Cell[] getRoutedCells(Cell[] cells) {
		Set<Cell> routedCells = new HashSet<Cell>();

		for (Cell cell : cells) {
			routedCells.addAll(Arrays.asList(getRoutedCells(cell)));
		}
		return routedCells.toArray(new Cell[] {});
	}

	public Cell[] getRoutedCells(Cell cell) {

		Set<AuthoringCell> routedCells = new HashSet<AuthoringCell>();

		addOppositeCells(routedCells, cell);

		return routedCells.toArray(new Cell[] {});
	}

	private void addOppositeCells(Set<AuthoringCell> routedCells, Cell cell) {

		if (routedCells.contains(cell)) {
			return;
		}

		routedCells.add((AuthoringCell) cell);
		Object[] oppositeCells = this.getOpposites(this.getEdges(cell), cell);
		for (Object cellObject : oppositeCells) {
			addOppositeCells(routedCells, (Cell) cellObject);
		}
	}

	public Cell[] getNextCells(Cell cell) {
		List<AuthoringCell> cells = new ArrayList<AuthoringCell>();
		for (Object object : getOpposites(getEdges(cell), cell)) {
			if (object instanceof AuthoringCell) {
				cells.add((AuthoringCell) object);
			}
		}
		return cells.toArray(new Cell[] {});
	}

	public Object[] getEdgesSelected() {
		List<Object> list = new ArrayList<Object>();
		for (Object cell : getSelectionCells()) {
			if (((mxCell) cell).isEdge()) {
				list.add(cell);
			}
		}
		return list.toArray();
	}

	public Cell getSourceVertex(Object edge) {
		return (AuthoringCell) ((mxCell) edge).getSource();
	}

	public Cell getTargetVertex(Object edge) {
		return (AuthoringCell) ((mxCell) edge).getTarget();
	}

	public Cell getCellAtPoint(int x, int y) {
		Object cell = graphComponent.getCellAt(x, y);
		if (cell instanceof AuthoringCell) {
			return (AuthoringCell) cell;
		}
		return null;
	}

	public Cell[] getAntecessors(Cell cell) {
		// DEBUG:
		System.out.println("*** getAntecessors - INICIO = (mxCell): id = "
				+ ((mxCell) cell).getId());

		List<Cell> cells = new ArrayList<Cell>();
		Object[] edges = getIncomingEdges(cell);
		for (Object edge : edges) {
			cells.add((Cell) ((mxCell) edge).getSource());

			// DEBUG:
			System.out
					.println(" mxCell - antecessor/incoming edge found: id = "
							+ ((mxCell) edge).getId());
		}

		// DEBUG:
		System.out.println("*** getAntecessors - TERMINO");

		return cells.toArray(new Cell[] {});
	}

	public Cell[] getSuccessors(Cell cell) {
		// DEBUG:
		System.out.println("*** getSucessors - INICIO ");

		List<Cell> cells = new ArrayList<Cell>();
		Object[] edges = getOutgoingEdges(cell);
		for (Object edge : edges) {
			cells.add((Cell) ((mxCell) edge).getTarget());
		}
		// DEBUG:
		System.out.println("*** getSucessors - TERMINO = #sucessors ="
				+ edges.length);

		return cells.toArray(new Cell[] {});
	}

	public Cell getStartCellPath(Cell[] cells) {
		// DEBUG:
		System.out.println("*** getStartCellPath - INICIO ");

		Cell startCell = null;

		for (Cell cell : cells) {
			Cell[] antecessors = getAntecessors(cell);
			// DEBUG:
			System.out.println(" cell = " + cell.getObject() + "class="
					+ cell.getObject().getClass());

			if (antecessors.length > 1) {
				// more than one predecessor is not a path

				// DEBUG:
				System.out
						.println("*** getStartCellPath - TERMINO - more than one antecessor is not a path");

				return null;
			} else if (antecessors.length == 0) {
				startCell = cell;
			} else {
				// DEBUG:
				System.out.println("*** antecessor length < 0");
			}
		}

		// DEBUG:
		System.out.println("*** getStartCellPath - TERMINO - " + startCell);

		return startCell;
	}

	public Cell[] getCells() {
		List<Cell> cells = new ArrayList<Cell>();

		for (Object object : ((mxGraphModel) getModel()).getCells().values()) {
			if (object instanceof Cell) {
				cells.add((Cell) object);
			}
		}
		return cells.toArray(new Cell[] {});

	}

	public Object getXML() {
		return new mxCodec().encode(getModel());
	}

	public void setXML(Object xml) {
		if (!(xml instanceof Node)) {
			return;
		}
		new mxCodec().decode((Node) xml, getModel());
	}

	public Object connectCells(Cell lastCell, Cell cell) {

		return null;
	}

	public void clear() {
		((mxGraphModel) getModel()).clear();
	}

	public void center() {
		((mxGraphComponent) getSwingComponent()).zoomAndCenter();
	}
}
