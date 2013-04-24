package org.sakaiproject.authoring.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

public class DrawRectangle extends JComponent {
	
	private static final long serialVersionUID = 5569815761335890899L;
	
	private Point firstPoint;
	private Point secondPoint;
	
	private Color color = new Color(0,0,255,100);
	private Color selectionColor = Color.YELLOW;
	
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public DrawRectangle(Point startPoint){
		this.firstPoint = startPoint;
		this.secondPoint = startPoint;
	}

	public void setSecondPoint(Point point) {
		this.secondPoint = point;
	}
	
	 
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(color);
		
		g.fillRect(firstPoint.x > secondPoint.x ? secondPoint.x : firstPoint.x, 
				firstPoint.y > secondPoint.y ? secondPoint.y : firstPoint.y, 
				Math.abs(secondPoint.x - firstPoint.x), 
				Math.abs(secondPoint.y - firstPoint.y));
		
		if(selected){
			g.setColor(selectionColor);
			g.drawRect(firstPoint.x > secondPoint.x ? secondPoint.x : firstPoint.x, 
					firstPoint.y > secondPoint.y ? secondPoint.y : firstPoint.y, 
							Math.abs(secondPoint.x - firstPoint.x), 
							Math.abs(secondPoint.y - firstPoint.y));
		}
	}

	public boolean containsPoint(Point point) {
		boolean insideX = firstPoint.x > secondPoint.x ? 
				(point.x < firstPoint.x && point.x > secondPoint.x) :
				(point.x > firstPoint.x && point.x < secondPoint.x);
		boolean insideY = firstPoint.y > secondPoint.y ? 
				(point.y < firstPoint.y && point.y > secondPoint.y) :
				(point.y > firstPoint.y && point.y < secondPoint.y);
		return insideX && insideY;
	}
	
	


}
