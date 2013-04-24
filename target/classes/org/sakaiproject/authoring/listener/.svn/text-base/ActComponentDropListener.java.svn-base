package org.sakaiproject.authoring.listener;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import org.sakaiproject.authoring.draw.AuthoringGraphFactory;

import com.mxgraph.view.mxGraph;

public class ActComponentDropListener implements DropTargetListener {

	private mxGraph graph;
	
	public ActComponentDropListener(mxGraph graph) {
		this.graph = graph;
	}

	public void dragEnter(DropTargetDragEvent dtde) {}

	public void dragExit(DropTargetEvent dte) {}

	public void dragOver(DropTargetDragEvent dtde) {}

	public void drop(DropTargetDropEvent dtde) {
		try {
			Object data = dtde.getTransferable().getTransferData(DataFlavor.imageFlavor);
			
			
			
			// Object cell =
			AuthoringGraphFactory.insertCell(graph, data, dtde.getLocation());;
			
//			componentsCellMap.put((mxCell)cell, data);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {}

}
