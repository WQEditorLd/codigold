package org.sakaiproject.authoring.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.Item;
import org.sakaiproject.authoring.utils.Bundle;

public class ItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -1484019258577186839L;
	private String[] columnNames = 
		new String[] { Bundle.getString("title.item") };

	private List<Item> itemList = new ArrayList<Item>();

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return itemList.size();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		 if(columnIndex == 0){
		 return true;
		 }
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {

		Item item = itemList.get(rowIndex);
		if (columnIndex == 0) {
			item.setTitle((String) value);
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj;
		Item item = itemList.get(rowIndex);
		Object resource = item.getIdentifierref();
		if (resource != null) {
			obj = ((Resource) resource).getHref();
		} else {
			obj = item.getTitle();
//			ItemAdapter itemAdapter = (ItemAdapter) item;
//			obj = (itemAdapter.getTextContent() == null ? "" : itemAdapter.getTextContent());
			
		}
		return obj;

	}

	public void add(Item item) {
		itemList.add(item);
		fireTableRowsInserted(itemList.size() - 1, itemList.size() - 1);
	}

	public void remove(Item item) {
		itemList.remove(item);
		fireTableDataChanged();
	}

	public void remove(int selectedRow) {
		itemList.remove(selectedRow);
		fireTableDataChanged();
	}

	public void removeAll() {
		itemList.clear();
	}

	public Item getItemAt(int selectedRow) {
		return itemList.get(selectedRow);
	}
}
