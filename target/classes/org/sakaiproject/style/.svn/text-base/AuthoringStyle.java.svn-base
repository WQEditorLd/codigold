package org.sakaiproject.style;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.imsglobal.jaxb.ld.Act;
import org.imsglobal.jaxb.ld.ActivityStructure;
import org.imsglobal.jaxb.ld.Environment;
import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.LearningActivity;
import org.imsglobal.jaxb.ld.Play;
import org.imsglobal.jaxb.ld.Staff;
import org.imsglobal.jaxb.ld.SupportActivity;
import org.sakaiproject.authoring.utils.Bundle;

public class AuthoringStyle {
	
	public static final double CELL_SIZE = 75;
	
	private static Map<Class, String> iconKeys = new HashMap<Class, String>();
	static{
		iconKeys.put(Learner.class, "image.learnericon");
		iconKeys.put(Staff.class, "image.stafficon");
		iconKeys.put(LearningActivity.class,"image.learningactivityicon");
		iconKeys.put(SupportActivity.class, "image.supportactivityicon");
		iconKeys.put(ActivityStructure.class, "image.activitystructureicon");
		iconKeys.put(Environment.class, "image.environmenticon");
		iconKeys.put(Act.class, "image.acticon");
		iconKeys.put(Play.class, "image.playicon");
	}
	
	public static String getIconKey(Class clazz){
		return iconKeys.get(clazz);
	}
	
	public final static Font cellFontBig = new Font("Comic Sans MS", Font.BOLD, 15);
	public final static Font cellFontMin = new Font("Comic Sans MS", Font.BOLD, 12);
	
	//TODO: check for "color" numberformatexception (só no Eclipse Europa?)
	public static String getDefaultCellStyle(Class clazz){
		return  "verticalLabelPosition=bottom;" +
				"perimeter=rhombusPerimeter;" +
				"fontSize=20;" +
				"fontColor=black;" +
				"shape=image;" +
				"image=/"+AuthoringStyle.getFilePath(clazz);
				
	}

	private static String getFilePath(Class clazz) {
		return Bundle.getString(iconKeys.get(clazz));
	}
	
}
