package org.sakaiproject.authoring.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.imsglobal.jaxb.ld.Act;
import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphCell;

import org.sakaiproject.authoring.utils.Bundle;



public class DrawActPanel extends JPanel implements DropTargetListener, KeyListener, GraphSelectionListener {

	private static final long serialVersionUID = 7118896526601448737L;
	
	private JFrame parentFrame;
	
	private JGraph graph;
	private JPanel buttonsPanel;
	private JButton createActButton;
	private JButton removeActButton;
	
	private Map<GraphCell, Object> componentsCellMap = new HashMap<GraphCell, Object>();
	private Map<GraphCell, Act> actsCellMap = new HashMap<GraphCell, Act>();
	
	public DrawActPanel(JFrame parentFrame){
		init();
	}

	private void init() {
		setBackground(Color.WHITE);	
	
		setLayout(new BorderLayout());
		
		graph = AuthoringGraphFactory.createDefaultGraph(this, new DrawActMarqueeHandler(this), this);
		graph.setDropTarget(new DropTarget(this, this));
		//graph.setTransferHandler(new ComponentTransferHandler());
		
		add(new JScrollPane(graph), BorderLayout.CENTER);
		
		createButtonsPanel();
		add(buttonsPanel, BorderLayout.NORTH);
	}

	private void createButtonsPanel() {
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
//		createActButton = new JButton(Bundle.getString("button.newact"),
//				new ImageIcon(FileUtil.getFileByKey("image.addlearningbutton")));
		createActButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				addAct();
			}
		});
		createActButton.setEnabled(false);
		//buttonsPanel.add(createActButton);

		
//		removeActButton = new JButton(Bundle.getString("button.remove"),
//				new ImageIcon(FileUtil.getFileByKey("image.removebutton")));
		removeActButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeAct();
			}
		});
		removeActButton.setEnabled(false);
		buttonsPanel.add(removeActButton);
	}
	public void dragEnter(DropTargetDragEvent dtde) {}

	public void dragExit(DropTargetEvent dte) {}

	public void dragOver(DropTargetDragEvent dtde) {}

	public void drop(DropTargetDropEvent dtde) {
		try {
			Object data = dtde.getTransferable().getTransferData(DataFlavor.imageFlavor);
			
			GraphCell cell = AuthoringGraphFactory.createDefaultCell(data, dtde.getLocation());
			graph.getGraphLayoutCache().insert(cell);
			
			componentsCellMap.put(cell, data);
			repaint();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_DELETE){
			for(Object cell : graph.getSelectionCells()){
				if(componentsCellMap.containsKey(cell)){
					componentsCellMap.remove(cell);
				}
				if(actsCellMap.containsKey(cell)){
					actsCellMap.remove(cell);
				}
				((DefaultGraphCell) cell).removeAllChildren();
			}
			graph.getGraphLayoutCache().remove(graph.getSelectionCells());
		}	
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public JGraph getGraph() {
		return graph;
	}

	public void setGraph(JGraph graph) {
		this.graph = graph;
	}

	public void addAct() {
		
		String title = JOptionPane.showInputDialog(parentFrame, 
				Bundle.getString("title.title"), 
				Bundle.getString("title.act"), 
				JOptionPane.INFORMATION_MESSAGE);
		if (title != null) {
			Act act = new Act();
			act.setTitle(title);
			GraphCell group = AuthoringGraphFactory.createDefaultGroupCell(graph, graph.getSelectionCells(), act.getTitle());	
			actsCellMap.put(group, act);
			createActButton.setEnabled(false);
		}
		
	}
	
	public void removeAct() {
		actsCellMap.remove(graph.getSelectionCells()[0]);
		graph.getGraphLayoutCache().ungroup(graph.getSelectionCells());
		removeActButton.setEnabled(false);
	}

	public void valueChanged(GraphSelectionEvent arg0) {
		
		Object[] children = graph.getSelectionCells();
		boolean createAct = true;
		for(Object child : children){
			if(child instanceof DefaultGraphCell && !(((DefaultGraphCell) child).getLeafCount() == 1)){
				createAct = false;
			}
		}
		createActButton.setEnabled(graph.getSelectionCount() > 1 && createAct);
		
		removeActButton.setEnabled(graph.getSelectionCount()==1 && !(((DefaultGraphCell)graph.getSelectionCell()).getLeafCount() == 1));
	}

	public Map<GraphCell, Object> getComponentsCellMap() {
		return componentsCellMap;
	}

	public void setComponentsCellMap(Map<GraphCell, Object> componentsCellMap) {
		this.componentsCellMap = componentsCellMap;
	}

	public Map<GraphCell, Act> getActsCellMap() {
		return actsCellMap;
	}

	public void setActsCellMap(Map<GraphCell, Act> actsCellMap) {
		this.actsCellMap = actsCellMap;
	}
	
}

