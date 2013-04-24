package org.sakaiproject.authoring.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableSelectionModel<T> extends AbstractTableModel {
	
	
	private static final long serialVersionUID = -1484019258577186839L;
	private String[] columnNames;
	
	private List<T> objectsSelected = new ArrayList<T>();
	protected List<T> objectList = new ArrayList<T>();
	
	public TableSelectionModel(List<T> objectList, String objectName){
		
		this.objectList = objectList;
		
		columnNames = new String[]{"", objectName};
		
	}
	
	public void setObjectList(List<T> objectList){
		this.objectList = objectList;
	}
	
	
	 
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0){
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}
	
	 
	public String getColumnName(int col) {
        return columnNames[col];
    }
	
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return objectList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			T currentObject = objectList.get(rowIndex);
			String currentTitle = getTitle(currentObject);
			for(Object object : objectsSelected){		
				if(currentTitle.equals(getTitle(object))){
					return true;
				}
			}
			return false;
		}
		if(columnIndex == 1){
			return "  " + getTitle(objectList.get(rowIndex));
		}
		return null;
	}

	
	
	public List<T> getObjectsSelected() {
		return objectsSelected;
	}


	public void setObjectsSelected(List<T> objectsSelected) {
		this.objectsSelected = objectsSelected;
		fireTableDataChanged();
	}


	 
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0){
			return true;
		}
		return false;
	}
	
	 
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		
		Boolean selected = (Boolean) value;
		if(selected){
			objectsSelected.add(objectList.get(rowIndex));
		} else {
			objectsSelected.remove(objectList.get(rowIndex));
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	protected String getTitle(Object object){
		try {
			return "  " + object.getClass().getMethod("getTitle").invoke(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getValueAt11(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getValueAt1(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object gegetValueAt1At(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
