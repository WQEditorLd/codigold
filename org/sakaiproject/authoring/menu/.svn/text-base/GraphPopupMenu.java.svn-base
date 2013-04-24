package org.sakaiproject.authoring.menu;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.imsglobal.jaxb.ld.Act;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.menu.action.AddActAction;
import org.sakaiproject.authoring.menu.action.AddActivityStructureAction;
import org.sakaiproject.authoring.menu.action.AddPlayAction;
import org.sakaiproject.authoring.model.LearningDesignModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.LearningDesignUtils;

public class GraphPopupMenu extends JPopupMenu {
	
	private static final long serialVersionUID = 5281015191871813748L;

	private Graph graph;
	
	private JMenuItem structureItem;
	private JMenuItem actItem;
	private JMenuItem playItem;
	
	public GraphPopupMenu(Graph graph, LearningDesignModel ldModel){
		this.graph = graph;
		
		structureItem = new JMenuItem(Bundle.getString("button.newactivitystructure"));
		structureItem.addActionListener(new AddActivityStructureAction(graph, 
				ldModel.getComponentsModel().getActivitiesModel()));
		add(structureItem);
		
		actItem = new JMenuItem(Bundle.getString("button.newact"));
		actItem.addActionListener(new AddActAction(graph));
		add(actItem);
		
		playItem = new JMenuItem(Bundle.getString("button.newplay"));
		playItem.addActionListener(new AddPlayAction(graph));
		add(playItem);
	}

	
	
	 
	public void show(Component invoker, int x, int y) {

		structureItem.setEnabled(structureCreateEnable());
		actItem.setEnabled(actCreateEnable());
		playItem.setEnabled(playCreateEnable());
		
		super.show(invoker, x, y);
	}
	
	private boolean playCreateEnable(){
		boolean enableIfPath = false;
		//DEBUG: 
		// System.out.println("playCreateEnable - INICIO");

		if(!hasOnlyActsSelected()){
			enableIfPath = false;
		}		
		else {
			enableIfPath = selectedCellsIsAPath();
		}
		
		//DEBUG: 
		// System.out.println("playCreateEnable - TERMINO = " + enableIfPath);

		return enableIfPath;
	}


	private boolean selectedCellsIsAPath(){
		boolean returnIfPath;
		//DEBUG: 
		// System.out.println("*** selectedCellsIsAPath - INICIO");

		Cell[] selectedCells = graph.getSelectedCells();
		
		Cell startCell = graph.getStartCellPath(selectedCells);
		
		if(startCell == null){
			//DEBUG: 
			// System.out.println("*** selectedCellsIsAPath - TERMINO - does not have startCell");

			//if there´s not a startCell, there´s not a path (cycle)
			return false;
		}
		
		returnIfPath = cellsHaveOnlyOneSuccessor(selectedCells, startCell);
		
		//DEBUG: 
		// System.out.println("*** selectedCellsIsAPath - TERMINO =" + returnIfPath);
		
		return returnIfPath; 
		
	}
	
//	private boolean cellsHaveOnlyOneAnteccessor(Cell[] selectedCells,
//			Cell startCell) {
//		
//		Cell currentCell = startCell;
//		
//		int i;
//		
//		for(i = 0; i < selectedCells.length; i++){
//			
//			Cell[] successors = graph.getSuccessors(currentCell);
//			
//			Cell[] antecessors = graph.getAntecessors(currentCell);
//			
//			if(antecessors.length > 1){
//				return false;
//			}
//			if(antecessors.length == 0 && currentCell != startCell){
//				return false;
//			}
//			if(successors.length == 0){
//				break;
//			}
//			currentCell = successors[0];
//		}
//		
//		return i == selectedCells.length;
//	}



	private boolean cellsHaveOnlyOneSuccessor(Cell[] selectedCells,
			Cell startCell) {
		//DEBUG: 
		// System.out.println("*** cellsHaveOnlyOneSucessor - INICIO : selectedCells.length = " + selectedCells.length);
		
		Cell currentCell = startCell;		
		int i;		

		for(i = 0; i < selectedCells.length; i++){
			
			Cell[] successors = graph.getSuccessors(currentCell);
			
			if(successors.length > 1){
				//DEBUG: 
				// System.out.println("sucessor.length >1");

				return false;
			}
			if(successors.length == 0){
				//DEBUG: 
				// System.out.println("sucessor.length == 0 (break)");
				break;
			}
			currentCell = successors[0];
			
			//DEBUG: 
			// System.out.println("sucessor = " + currentCell.getObject());
		}

		//DEBUG: 
		// System.out.println("*** cellsHaveOnlyOneSucessor - TERMINO = " + (i+1 == selectedCells.length));
		
		return i+1 == selectedCells.length;
	}



	

	private boolean hasOnlyActsSelected() {
		//DEBUG: 
		// System.out.println("*** hasOnlyActsSelected - INICIO ");
		
		boolean hasActsOnly = true;
		
		Cell[] selectedCells = graph.getSelectedCells();
		
		for(Cell cell : selectedCells){

			//DEBUG: 
			// System.out.println("Cell - " + cell.getObject().getClass());

			if(!(cell.getObject() instanceof Act)){
				hasActsOnly = false;
				break;
			}
		}

		//DEBUG: 
		// System.out.println("*** hasOnlyActsSelected - TERMINO = " + hasActsOnly);

		return hasActsOnly;
	}
	
	private boolean actCreateEnable() {
		
		if (!atLeastTwoCellsSelected()) {
			return false;
		}
		
		if(!canTraverseThroughSelectedCells()){
			return false;
		}
		
		return selectedCellsContainsRolePartSet();
	}

	private boolean selectedCellsContainsRolePartSet() {
		
		Cell[] selectedCells = graph.getSelectedCells();
		
		for(Cell cell : selectedCells){
			if(!verifyIfCellPairsAreRoleParts(cell)){
				return false;
			}
		}
		
		return true;
	}

	private boolean verifyIfCellPairsAreRoleParts(Cell cell) {
		
		Cell[] cells = graph.getNextCells(cell);
		
		if(cells.length == 0){
			return false;
		}
		
		for(Cell nextCell : cells){		
			
			if(!LearningDesignUtils.isActivity(cell.getObject()) &&
					!LearningDesignUtils.isRole(cell.getObject())){
				return false;
			}
			
			if(LearningDesignUtils.isActivity(cell.getObject()) && 
					!LearningDesignUtils.isRole(nextCell.getObject())){
				return false;
			}
			if(LearningDesignUtils.isRole(cell.getObject()) && 
					!LearningDesignUtils.isActivity(nextCell.getObject())){
				return false;
			}
		}
		return true;
	}

	private boolean canTraverseThroughSelectedCells() {
		
		Cell[] selectedCells = graph.getSelectedCells();	
		Cell[] routedCells = graph.getRoutedCells(selectedCells);
		
		List<Cell> cells = Arrays.asList(selectedCells);
		if(!cells.containsAll(Arrays.asList(routedCells))){
			return false;
		}
			
		return true;
	}

	public boolean structureCreateEnable() {

		if (!atLeastTwoCellsSelected()) {
			return false;
		}
		return hasOnlyActivitiesSelected();
	}

	private boolean hasOnlyActivitiesSelected() {
		//DEBUG: 
		// System.out.println("hasOnlyActivitiesSelected - ");
		
		Cell[] selectedCells = graph.getSelectedCells();
		for (Cell cell : selectedCells) {
			
			//DEBUG: 
			// System.out.println("" + cell.getObject().getClass());
			
			if (!LearningDesignUtils.isActivity(cell.getObject())) {
				return false;
			}
		}
		return true;
	}

	private boolean atLeastTwoCellsSelected() {
		Cell[] selectedCells = graph.getSelectedCells();
		if (selectedCells.length == 0 || selectedCells.length == 1) {
			return false;
		}
		return true;
	}
	
	
}
