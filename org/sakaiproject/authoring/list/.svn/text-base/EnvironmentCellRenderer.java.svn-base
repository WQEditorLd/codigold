package org.sakaiproject.authoring.list;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import org.imsglobal.jaxb.ld.Conference;
import org.imsglobal.jaxb.ld.GameService;
import org.imsglobal.jaxb.ld.IndexSearch;
import org.imsglobal.jaxb.ld.LearningObject;
import org.imsglobal.jaxb.ld.SendMail;
import org.sakaiproject.authoring.utils.Bundle;

public class EnvironmentCellRenderer extends DefaultListCellRenderer {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2304100480626055106L;

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		String prefix = "";
		
		if(value instanceof LearningObject){
			prefix = Bundle.getString("title.learningobject") + ": ";
		}
		if(value instanceof SendMail){
			prefix = Bundle.getString("title.sendmail") + ": ";
		}
		if(value instanceof Conference){
			prefix = Bundle.getString("title.conference") + ": ";
		}
		if(value instanceof IndexSearch){
			prefix = Bundle.getString("title.indexsearch") + ": ";
		}
		if(value instanceof GameService){
			prefix = Bundle.getString("title.game") + ": ";
		}
		
		String title = "";
		try {
			title = (String) value.getClass().getMethod("getTitle").invoke(value);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		label.setText(prefix + title);
		
		return label;
	}

}
