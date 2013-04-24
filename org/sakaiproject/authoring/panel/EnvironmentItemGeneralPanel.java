package org.sakaiproject.authoring.panel;

import java.awt.Color;
import java.awt.TextField;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.sakaiproject.authoring.utils.Bundle;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class EnvironmentItemGeneralPanel extends JPanel {

	private static final long serialVersionUID = -4138018240062415600L;
	
	private TextField textField = new TextField();
//	private JLabel typeLabel;
//	private JTextField typeField;
	private JCheckBox visibleCheckbox = new JCheckBox();
	
//	private JLabel classLabel;
//	private JTextField classField;
//	private JLabel parametersLabel;
//	private JTextField parametersField;
	
	public EnvironmentItemGeneralPanel(){
		FormLayout layout = new FormLayout("left:p, 3dlu, 150dlu, 5dlu, p, 3dlu, p", "p");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		JLabel labelName = new JLabel(Bundle.getString("label.name"));
		labelName.setForeground(Color.BLUE);
		builder.add(labelName, (CellConstraints) cc.xy(1, 1).clone(), textField, cc.xy(3, 1));
		
		JLabel labelVisible = new JLabel(Bundle.getString("label.visible"));
		labelVisible.setForeground(Color.BLUE);
		visibleCheckbox.setSelected(true);
		builder.add(labelVisible, (CellConstraints) cc.xy(5, 1).clone(), visibleCheckbox, cc.xy(7, 1));
		
		
		add(builder.getPanel());
	}
	
	public void setTitle(String title){
		textField.setText(title);
	}
	
	public String getTitle(){
		return textField.getText();
	}
	
	public void setIsVisible(boolean visible){
		visibleCheckbox.setSelected(visible);
	}
	
	public boolean getIsVisible(){
		return visibleCheckbox.isSelected();
	}
}
