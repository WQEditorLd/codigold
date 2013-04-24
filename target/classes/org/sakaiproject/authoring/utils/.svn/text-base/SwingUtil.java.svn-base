package org.sakaiproject.authoring.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class SwingUtil {

	public static Point getCenterPosition(Component component){
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (int)size.getWidth()/2 - (int) component.getWidth()/2;
		int yPos = (int)size.getHeight()/2 - (int) component.getHeight()/2;
		
		return new Point(xPos, yPos);
	}
}
