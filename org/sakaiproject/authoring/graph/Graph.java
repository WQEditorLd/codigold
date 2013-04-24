package org.sakaiproject.authoring.graph;

import java.awt.event.MouseListener;

import javax.swing.JComponent;

public interface Graph {
	
	public JComponent getSwingComponent();
	
	public void addMouseListener(MouseListener mouseListener);
	
	public void removeCells(Cell[] cells);

	public Cell[] getCellsWithObject(Object object);
	
	public Cell[] getSelectedCells();

	public void createGroupForSelectedCells(Cell cell);

	public Cell[] getRoutedCells(Cell[] cells);
	
	public Cell[] getRoutedCells(Cell cell);
	
	public Cell[] getNextCells(Cell cell);
	
	public Object[] getEdgesSelected();
	
	public Cell getSourceVertex(Object edge);
	
	public Cell getTargetVertex(Object edge);

	public Cell getCellAtPoint(int x, int y);

	public Cell[] getAntecessors(Cell cell);

	public Cell[] getSuccessors(Cell currentCell);
	
	public Cell getStartCellPath(Cell[] cells);

	public Cell[] getCells();

	public Object getXML();

	public void setXML(Object xml);

	public Object connectCells(Cell lastCell, Cell cell);

	public void clear();

	public void center();
}
