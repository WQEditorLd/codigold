package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.sakaiproject.authoring.dialog.ObjectDialog;
import org.sakaiproject.authoring.event.EventController;
import org.sakaiproject.authoring.utils.Bundle;

import com.jgoodies.forms.factories.ButtonBarFactory;

public class ObjectDialogButtonBarPanel extends JPanel {
	
	private static final long serialVersionUID = -2988867508163468477L;

	public ObjectDialogButtonBarPanel(final ObjectDialog labelDialog){
		
		setLayout(new BorderLayout());
		
		JButton okButton = new JButton(Bundle.getString("button.ok"));
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				labelDialog.populateObject();
				EventController.getInstance().dispatchEvent(ObjectDialog.OBJECT_CHANGE_EVENT, 
						labelDialog.getObject());
				labelDialog.setVisible(false);
			}
		});
		
		JButton cancelButton = new JButton(Bundle.getString("button.cancel"));
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				labelDialog.setVisible(false);
			}
		});
		
		add(ButtonBarFactory.buildCenteredBar(okButton, cancelButton), BorderLayout.CENTER);
	}
	
}
