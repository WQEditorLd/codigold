package org.sakaiproject.authoring.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.mxgraph.view.mxGraph;

public class KeyDeleteGraphListener extends KeyAdapter {
	
	private mxGraph graph;
	
	public KeyDeleteGraphListener(mxGraph graph){
		this.graph = graph;
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_DELETE){
			//for(Object cell : graph.getSelectionCells()){
//				if(componentsCellMap.containsKey(cell)){
//					componentsCellMap.remove(cell);
//				}
//				if(actsCellMap.containsKey(cell)){
//					actsCellMap.remove(cell);
//				}
	//			((mxCell) cell).removeFromParent();
//			}
			graph.removeCells(graph.getSelectionCells());
		}	
	}
}
