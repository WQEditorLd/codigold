package org.sakaiproject.authoring.draw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.imsglobal.jaxb.ld.ActivityStructure;
import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.LearningActivity;
import org.imsglobal.jaxb.ld.Staff;
import org.imsglobal.jaxb.ld.SupportActivity;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.Port;
import org.jgraph.graph.PortView;

public class DrawActMarqueeHandler extends BasicMarqueeHandler {

	private Point2D start;
	private Point2D current;
	private PortView port;
	private PortView firstPort;
	
	private DrawActPanel panel;
	
	public DrawActMarqueeHandler(DrawActPanel panel){
		this.panel = panel;
	}

	public boolean isForceMarqueeEvent(MouseEvent e) {
		if (e.isShiftDown())
			return false;
		
		port = getSourcePortAt(e.getPoint());
	
		if (port != null && panel.getGraph().isPortsVisible())
			return true;

		return super.isForceMarqueeEvent(e);
	}

	public void mousePressed(final MouseEvent e) {
		 
		if (port != null && panel.getGraph().isPortsVisible()) {
			start = panel.getGraph().toScreen(port.getLocation());
			firstPort = port;
		} else {
			super.mousePressed(e);
		}
	}

	public void mouseDragged(MouseEvent e) {
	
		if (start != null) {
			
			Graphics g = panel.getGraph().getGraphics();
			PortView newPort = getTargetPortAt(e.getPoint());
			if (newPort == null || newPort != port) {
				paintConnector(Color.black, panel.getGraph().getBackground(), g);
				port = newPort;
				if (port != null)
					current = panel.getGraph().toScreen(port.getLocation());
				else
					current = panel.getGraph().snap(e.getPoint());
				paintConnector(panel.getGraph().getBackground(), Color.black, g);
			}
		}
		super.mouseDragged(e);
	}

	public PortView getSourcePortAt(Point2D point) {
		
		panel.getGraph().setJumpToDefaultPort(false);
		PortView result;
		try {
			result = panel.getGraph().getPortViewAt(point.getX(), point.getY());
		} finally {
			panel.getGraph().setJumpToDefaultPort(true);
		}
		return result;
	}

	protected PortView getTargetPortAt(Point2D point) {
		return panel.getGraph().getPortViewAt(point.getX(), point.getY());
	}

	public void mouseReleased(MouseEvent e) {
		
		if (e != null && port != null && firstPort != null
				&& firstPort != port) {
			connect((Port) firstPort.getCell(), (Port) port.getCell());
			e.consume();
		} else
			panel.getGraph().repaint();
		
		firstPort = port = null;
		start = current = null;
		
		super.mouseReleased(e);
	}

	
	public void mouseMoved(MouseEvent e) {
		
		if (e != null && getSourcePortAt(e.getPoint()) != null
				&& panel.getGraph().isPortsVisible()) {
			
			panel.getGraph().setCursor(new Cursor(Cursor.HAND_CURSOR));
			e.consume();
		} else
			super.mouseMoved(e);
	}


	protected void paintConnector(Color fg, Color bg, Graphics g) {
	
		g.setColor(fg);
		g.setXORMode(bg);
		paintPort(panel.getGraph().getGraphics());
		if (firstPort != null && start != null && current != null)
			g.drawLine((int) start.getX(), (int) start.getY(),
					(int) current.getX(), (int) current.getY());
	}

	protected void paintPort(Graphics g) {
		
		if (port != null) {
			boolean o = (GraphConstants.getOffset(port.getAllAttributes()) != null);
			Rectangle2D r = (o) ? port.getBounds() : port.getParentView()
					.getBounds();
			r = panel.getGraph().toScreen((Rectangle2D) r.clone());
			r.setFrame(r.getX() - 3, r.getY() - 3, r.getWidth() + 6, r
					.getHeight() + 6);
			panel.getGraph().getUI().paintCell(g, port, r, true);
		}
	}
	
	public void connect(Port source, Port target) {
	
		DefaultEdge edge = new DefaultEdge();
		if (panel.getGraph().getModel().acceptsSource(edge, source)
				&& panel.getGraph().getModel().acceptsTarget(edge, target)) {
			
			DefaultGraphCell sourceCell = (DefaultGraphCell)((DefaultPort) source).getParent();
			DefaultGraphCell targetCell = (DefaultGraphCell)((DefaultPort) target).getParent();
			
			if(canConnect(sourceCell, targetCell)){
				DefaultEdge newEdge = createAppropriateEdge(sourceCell, targetCell);
				panel.getGraph().getGraphLayoutCache().insertEdge(newEdge, source, target);
			}
			panel.getGraph().repaint();
			
		}
	}

	private DefaultEdge createAppropriateEdge(DefaultGraphCell sourceCell,
			DefaultGraphCell targetCell) {
		DefaultEdge edge = new DefaultEdge();
		
		if(panel.getActsCellMap().containsKey(sourceCell) && panel.getActsCellMap().containsKey(targetCell)){
			GraphConstants.setLineEnd(edge.getAttributes(), GraphConstants.ARROW_CLASSIC);
		}
		return edge;
	}

	private boolean canConnect(DefaultGraphCell sourceCell,
			DefaultGraphCell targetCell) {
		Object sourceComponent = panel.getComponentsCellMap().get(sourceCell) ;
		Object targetComponent = panel.getComponentsCellMap().get(targetCell) ;
		
		if(sourceComponent != null && targetComponent != null){
			boolean sourceIsActivity = (sourceComponent instanceof LearningActivity) || 
				(sourceComponent instanceof SupportActivity) ||
				(sourceComponent instanceof ActivityStructure);
			
			boolean targetIsActivity = (targetComponent instanceof LearningActivity) || 
			(targetComponent instanceof SupportActivity) ||
			(targetComponent instanceof ActivityStructure);
			
			boolean sourceIsRole = (sourceComponent instanceof Learner) || 
			(sourceComponent instanceof Staff);
			
			boolean targetIsRole = (targetComponent instanceof Learner) || 
			(targetComponent instanceof Staff);
			
			return ((sourceIsRole) && (targetIsActivity)) || ((sourceIsActivity) && (targetIsRole));
		}
		

		Object sourceAct = panel.getActsCellMap().get(sourceCell);
		Object targetAct = panel.getActsCellMap().get(targetCell);
		if(sourceAct != null && targetAct != null){
			return true;
		}
		
		return false;
	}

}
