package org.sakaiproject.authoring.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.ActivityStructure;
import org.imsglobal.jaxb.ld.Environment;
import org.imsglobal.jaxb.ld.Item;
import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.LearningActivity;
import org.imsglobal.jaxb.ld.Staff;
import org.imsglobal.jaxb.ld.SupportActivity;
import org.imsglobal.ld.parser.ItemAdapter;

public class LearningDesignUtils {

	public static boolean isActivity(Object object) {
		return object instanceof LearningActivity || 
			object instanceof SupportActivity || 
			object instanceof ActivityStructure;
	}

	public static boolean isRole(Object object) {
		return object instanceof Learner || object instanceof Staff;
	}
	
	public static boolean isEnviromnent(Object object) {
		return object instanceof Environment;
	}
	
	public static boolean isComponent(Object object){
		return isRole(object) || isEnviromnent(object) || isActivity(object);
	}
	
	
	public static List<Resource> getResources(List<Item> itens, File directory){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for(Item item : itens){
			// Obtem o IDREF
			if(item.getIdentifierref() != null){
				resources.add((Resource) item.getIdentifierref());
			}
			else if(item instanceof ItemAdapter){
				resources.add(((ItemAdapter) item).getResource(directory));
			}
		}
		return resources;
	}
	
	public static String getIdentifier(Object object){
		try {
			return (String) object.getClass().getMethod("getIdentifier").invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
}
