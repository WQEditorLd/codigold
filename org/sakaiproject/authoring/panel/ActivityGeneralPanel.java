package org.sakaiproject.authoring.panel;

import java.awt.Color;
import java.awt.TextField;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.imsglobal.jaxb.ld.Environment;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.table.TableSelectionModel;
import org.sakaiproject.authoring.utils.Bundle;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ActivityGeneralPanel extends JPanel {

	private static final long serialVersionUID = -4138018240062415600L;
	
	private TextField textField = new TextField();
	private TextField parametersField = new TextField();
	private JCheckBox visibleCheckbox = new JCheckBox();
	private TableSelectionModel<Environment> environmentTableModel;
	private EnvironmentsModel environmentsModel;
	private JTable environmentsTable;
	
	public ActivityGeneralPanel(EnvironmentsModel environmentsModel){
		
		this.environmentsModel = environmentsModel;
		
		FormLayout layout = new FormLayout("left:p, 3dlu, 200dlu, 5dlu, p, 3dlu, p", "p, 3dlu, p, 7dlu, p, 3dlu, p, 7dlu, 100dlu, 5dlu");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		JLabel labelName = new JLabel(Bundle.getString("label.name"));
		labelName.setForeground(Color.BLUE);
		builder.add(labelName,  (CellConstraints) cc.xy(1, 1).clone(), textField, cc.xy(3, 1));
		
		JLabel labelVisible = new JLabel(Bundle.getString("label.visible"));
		labelVisible.setForeground(Color.BLUE);
		visibleCheckbox.setSelected(true);
		builder.add(labelVisible,  (CellConstraints) cc.xy(5, 1).clone(), visibleCheckbox, cc.xy(7, 1));
		
		JLabel labelParameters = new JLabel(Bundle.getString("label.parameters"));
		labelParameters.setForeground(Color.BLUE);
		builder.add(labelParameters,  (CellConstraints) cc.xy(1, 3).clone(), parametersField,  cc.xyw(3, 3, 5));
		
		builder.addSeparator(Bundle.getString("title.environments"), cc.xyw(1, 5, 7));
		
		environmentTableModel = new TableSelectionModel<Environment>(
				environmentsModel.getEnvironments().getEnvironmentList(), Bundle.getString("title.environments"));
		
		environmentsTable = new JTable(environmentTableModel);
		environmentsTable.getColumnModel().getColumn(0).setMaxWidth(25);
		builder.add(new JScrollPane(environmentsTable), cc.xyw(1, 9, 7));
		
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

	public void setParametersField(String parameters) {
		parametersField.setText(parameters);
	}

	public String getParametersField() {
		String text = parametersField.getText();
		return (text != null && !text.isEmpty() ? text : null); // Parameters cannot be "" at XML LD
	}

	public void setEnvironmentsSelected(List<Environment> environments) {
		if(environmentsModel.getEnvironments() != null){
			environmentTableModel.setObjectList(environmentsModel.getEnvironments().getEnvironmentList());
		}
		environmentTableModel.setObjectsSelected(environments);
	}
	
	public List<Environment> getEnvironmentsSelected(){
		return environmentTableModel.getObjectsSelected();
	}

}
