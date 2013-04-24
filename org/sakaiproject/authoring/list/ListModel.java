package org.sakaiproject.authoring.list;

import javax.swing.DefaultListModel;

public class ListModel extends DefaultListModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6230880829325247119L;

	public void updateChange(int index){
		fireContentsChanged(this, index, index);
	}

}
