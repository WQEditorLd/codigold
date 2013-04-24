package org.sakaiproject.authoring.table;

import java.util.List;

import javax.swing.JTable;

import org.imsglobal.jaxb.ld.Item;


public class ItemsTable extends JTable {
	
	private static final long serialVersionUID = -4388597126075678951L;
	
	private ItemTableModel tableModel = new ItemTableModel();

	public ItemsTable(){
		setModel(tableModel);
	}

	public void add(Item item) {
		tableModel.add(item);
		tableModel.fireTableDataChanged();
	}		
	
	public void setItems(List<Item> items){
		tableModel.removeAll();
		for(Item item : items){
			tableModel.add(item);
		}
		tableModel.fireTableDataChanged();
	}

	public Item getSelectedItem() {
		Item ret = null;
		if ( getSelectedRow() >= 0 ) {
			ret = tableModel.getItemAt(getSelectedRow());
		}
		return ret;
	}

	public void remove(Item item) {
		tableModel.remove(item);
		tableModel.fireTableDataChanged();
	}
}
