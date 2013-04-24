package org.sakaiproject.authoring.table;

import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.ld.Play;
import org.sakaiproject.authoring.utils.Bundle;

public class PlaySelectionModel extends TableSelectionModel<Play> {

	private static final long serialVersionUID = -7663163955060642920L;
	
	private boolean selectable;

	public PlaySelectionModel(List<Play> playList) {
		super(playList, Bundle.getString("title.play"));
	}
	
	 
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			return super.getValueAt(rowIndex, columnIndex);
		} else {
			Play play = objectList.get(rowIndex);
			return play.getTitle();
		}
		
	}


	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	
	
	 
	public List<Play> getObjectsSelected() {
		if(!selectable){
			return new ArrayList<Play>();
		}
		return super.getObjectsSelected();
	}

}
