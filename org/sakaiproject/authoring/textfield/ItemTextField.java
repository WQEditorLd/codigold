package org.sakaiproject.authoring.textfield;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JTextField;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.Item;
import org.imsglobal.ld.parser.ItemFileAdapter;
import org.sakaiproject.authoring.utils.FileUtil;

public class ItemTextField extends JTextField {

	private static final long serialVersionUID = 2608873931970850043L;

	private Item item;

	public ItemTextField() {

		super(40);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				File file = FileUtil.getFromFileChooser();
				if (file != null) {
					item = new ItemFileAdapter(file);
					item.setTitle(file.getName());
					setText(file.getName());
				}

			}
		});
	}

	public void setItem(Item item) {
		this.item = item;
		if (item instanceof ItemFileAdapter) {
			setText(((ItemFileAdapter) item).getFileContent().getName());
		} else {
			setText(((Resource) item.getIdentifierref()).getHref());
		}
	}

	public Item getItem() {
		return item;
	}

}
