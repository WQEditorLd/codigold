package org.sakaiproject.authoring.label;

import java.awt.Color;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.jgraphx.AuthoringCellFactory;
import org.sakaiproject.authoring.listener.LabelMouseListener;
import org.sakaiproject.authoring.panel.LabelsGridPanel;
import org.sakaiproject.authoring.utils.FileUtil;
import org.sakaiproject.style.AuthoringStyle;

import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxRectangle;


public class Label extends JLabel {
	
	private static final long serialVersionUID = 3692446330724750868L;

	private Object object;
	
	public Label(Object object, LabelsGridPanel labelsGridPanel){
		
		super(FileUtil.getImageIcon(AuthoringStyle.getIconKey(object.getClass())));
		
		this.object = object;
		
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		
		addMouseListener(new LabelMouseListener(this, labelsGridPanel));
		
		addDragListener();
	}
	
	public Object getObject() {
		return object;
	}

	public void setSelected(boolean selected) {
		if(selected){
			setBorder(BorderFactory.createLineBorder(Color.BLUE));
		} else {
			setBorder(null);
		}
		
		revalidate();
		repaint();
	}

	private void addDragListener() {
		DragGestureListener dragGestureListener = new DragGestureListener()
		{
			public void dragGestureRecognized(DragGestureEvent e)
			{
				Cell cell = AuthoringCellFactory.buildAuthoringCell(object);
				
				try {
					mxGraphTransferable.dataFlavor = 
					new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + "; " +
							"class=com.mxgraph.swing.util.mxGraphTransferable"); 
				} 
				catch (ClassNotFoundException cnfe) { 
					System.out.println(cnfe); 
				} 
				
				mxGraphTransferable t = new mxGraphTransferable(
						new Object[] { cell }, new mxRectangle(Label.this.getBounds()));
				
				 
				
				e.startDrag(null, mxConstants.EMPTY_IMAGE, new Point(),
								t, null);
			}
		};

		DragSource dragSource = new DragSource();
		dragSource
				.createDefaultDragGestureRecognizer(this,
						DnDConstants.ACTION_REFERENCE, dragGestureListener);
	}

	
	 
	public String getText() {
		
		if(object == null) return null;
		
		String text = "";
		try {
			text = (String) object.getClass().getMethod("getTitle").invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

}
