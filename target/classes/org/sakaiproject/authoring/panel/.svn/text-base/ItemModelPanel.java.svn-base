package org.sakaiproject.authoring.panel;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import org.imsglobal.jaxb.ld.Item;
import org.imsglobal.jaxb.ld.ItemModel;
import org.imsglobal.ld.parser.ItemFileAdapter;
import org.imsglobal.ld.parser.ItemTextAdapter;
import org.sakaiproject.authoring.table.ItemsTable;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.FileUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ItemModelPanel extends JPanel {

	private static final long serialVersionUID = 2860842537319672200L;
	private static final File tempDirectory;
	protected static final String DESCRIPTION_FILE_TYPE = ".txt";

	static {
		tempDirectory = new File(System.getProperty("user.home")
				+ "/authoring/temp");
	}

	private String modelName;
	
	private JTextArea textArea = new JTextArea(3, 40);
	
	private ItemsTable itemTable;

	private ItemModel itemModel = new ItemModel();

	public ItemModel getItemModel() {
		if (itemModel.getItemList().isEmpty()) {
			return null;
		}
		if (itemModel.getTitle() == null) {
			itemModel.setTitle(modelName);
		}
		return itemModel;
	}

	public List<Item> getItemList() {
		return itemModel.getItemList();
	}

	public void setItemModel(ItemModel itemModel) {
		this.itemModel = itemModel;
		if (itemModel == null) {
			this.itemModel = new ItemModel();
		}
		if (this.itemModel.getItemList() != null) {
			itemTable.setItems(this.itemModel.getItemList());
		}
	}

	public void setItemList(List<Item> list) {
		if (itemModel == null) {
			itemModel = new ItemModel();
		}
		itemModel.getItemList().clear();
		itemModel.getItemList().addAll(list);
		itemTable.setItems(list);
	}

	public ItemModelPanel(String modelName) {

		this.modelName = modelName.trim().replaceAll(" ", "-");

		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setLineWrap(true);
		textArea.setBorder(BorderFactory.createEtchedBorder());
		
		FormLayout layout = new FormLayout("left:200dlu, 4dlu, p", "p, 2dlu, p, 7dlu, 100dlu");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();
		builder.addSeparator(modelName, cc.xyw(1, 1, 3));
		builder.add(scrollPane, cc.xy(1, 3));

		JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
		toolBar.setBorder(BorderFactory.createEtchedBorder());
		toolBar.setFloatable(false);
		toolBar.add(new AbstractAction(Bundle.getString("button.add"), 
				FileUtil.getImageIcon("image.plusicon")) {

			private static final long serialVersionUID = 5830837929951164970L;

			public void actionPerformed(ActionEvent arg0) {
				String currentTitle = getCurrentItemTitle();
				ItemTextAdapter item = new ItemTextAdapter(textArea.getText(),
						currentTitle, DESCRIPTION_FILE_TYPE,
						tempDirectory);
				item.setTitle(currentTitle);
				itemTable.add(item);
				itemModel.getItemList().add(item);
				textArea.setText("");
			}

		});
		toolBar.add(new AbstractAction(Bundle.getString("button.import"),
				FileUtil.getImageIcon("image.addlearningbutton")) {

			private static final long serialVersionUID = -7143487934914665187L;

			public void actionPerformed(ActionEvent e) {
				File file = FileUtil.getFromFileChooser();
				if (file != null) {
					String currentTitle = getCurrentItemTitle();
					ItemFileAdapter item = new ItemFileAdapter(file);
					item.setTitle(currentTitle);
					itemTable.add(item);
					itemModel.getItemList().add(item);
				}
			}

		});

		toolBar.add(new AbstractAction(Bundle.getString("button.remove"),
				FileUtil.getImageIcon("image.removebutton")) {

			private static final long serialVersionUID = 2876120218156072385L;

			public void actionPerformed(ActionEvent e) {
				Item item = itemTable.getSelectedItem();
				itemTable.remove(item);
				itemModel.getItemList().remove(item);
			}
		});

		builder.add(toolBar, cc.xy(3, 3));

		itemTable = new ItemsTable();
		builder.add(new JScrollPane(itemTable), cc.xyw(1, 5, 3));

		add(builder.getPanel());

	}
	
	private String getCurrentItemTitle() {
		int number = (int) (1000 * Math.random());
		return modelName + "-" + number;
	}

}
