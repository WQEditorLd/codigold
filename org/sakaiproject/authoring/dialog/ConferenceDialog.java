package org.sakaiproject.authoring.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.imsglobal.jaxb.ld.Conference;
import org.imsglobal.jaxb.ld.ConferenceManager;
import org.imsglobal.jaxb.ld.Moderator;
import org.imsglobal.jaxb.ld.Observer;
import org.imsglobal.jaxb.ld.Participant;
import org.imsglobal.ld.constants.ConferenceTypeSelect;
import org.sakaiproject.authoring.combobox.ComboBoxModel;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.table.TableSelectionModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ConferenceDialog extends JDialog implements ObjectDialog<Conference> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.conference");

	private JTextField textField = new JTextField(40);
	
	private JComboBox typeCombo = new JComboBox(new Object[] { "",
			Bundle.getString("combo.synchronous"),
			Bundle.getString("combo.asynchronous"),
			Bundle.getString("combo.announcement") });
	
	private JTable participantsTable;
	private TableSelectionModel<Object> participantsTableModel;
	
	private JTable observersTable;
	private TableSelectionModel<Object> observersTableModel;
	
	private JComboBox managerCombo;
	private ComboBoxModel managerComboModel;
	
	private JComboBox moderatorCombo;
	private ComboBoxModel moderatorComboModel;

	private Conference service;
	
	public ConferenceDialog(JDialog parent, RolesModel rolesModel) {
		
		super(parent, true);
		
		FormLayout layout = new FormLayout("200dlu", "p, 2dlu, p, 7dlu, p, 2dlu, p, 7dlu, p, 2dlu, p, 7dlu, p, 2dlu, p, " +
				"7dlu, p, 2dlu, 100dlu, 7dlu, p, 2dlu, 100dlu, 7dlu, p");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xy(1, 1));
		builder.add(textField, cc.xy(1, 3));
		
		builder.addSeparator(Bundle.getString("label.type"), cc.xy(1, 5));
		typeCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");
		builder.add(typeCombo, cc.xy(1, 7));
		
		List<Object> roles = new ArrayList<Object>();
		roles.addAll(rolesModel.getRoles().getLearnerList());
		roles.addAll(rolesModel.getRoles().getStaffList());
		
		managerComboModel = new ComboBoxModel(roles);
		managerCombo = new JComboBox(managerComboModel);
		managerCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");
		builder.addSeparator(Bundle.getString("label.manager"), cc.xy(1, 9));
		builder.add(managerCombo, cc.xy(1, 11));
		
		moderatorComboModel = new ComboBoxModel(roles);
		moderatorCombo = new JComboBox(moderatorComboModel);
		moderatorCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXX");
		builder.addSeparator(Bundle.getString("label.moderator"), cc.xy(1, 13));
		builder.add(moderatorCombo, cc.xy(1, 15));
		
		participantsTableModel = new TableSelectionModel<Object>(roles, Bundle.getString("title.role"));
		participantsTable = new JTable(participantsTableModel);
		participantsTable.getColumnModel().getColumn(0).setMaxWidth(25);
		builder.addSeparator(Bundle.getString("label.participants"),cc.xy(1, 17));
		builder.add(new JScrollPane(participantsTable), cc.xy(1, 19));
		
		observersTableModel = new TableSelectionModel<Object>(roles, Bundle.getString("title.role"));
		observersTable = new JTable(observersTableModel);
		observersTable.getColumnModel().getColumn(0).setMaxWidth(25);
		builder.addSeparator(Bundle.getString("label.observers"),cc.xy(1, 21));
		builder.add(new JScrollPane(observersTable), cc.xy(1, 23));
		
		
		builder.add(new ObjectDialogButtonBarPanel(this), cc.xy(1, 25));
		
		add(builder.getPanel());
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	public Conference getObject() {
		return service;
	}

	public void setObject(Conference object) {

		this.service = object;

		textField.setText(object.getTitle());
		
		if(service.getConferenceType() == null){
			typeCombo.setSelectedItem("");
		}
		else if(service.getConferenceType().equals(ConferenceTypeSelect.SYNCHRONOUS)){
			typeCombo.setSelectedItem(Bundle.getString("combo.synchronous"));
		}
		else if(service.getConferenceType().equals(ConferenceTypeSelect.ASYNCHRONOUS)){
			typeCombo.setSelectedItem(Bundle.getString("combo.asynchronous"));
		}
		else if(service.getConferenceType().equals(ConferenceTypeSelect.ANNOUNCEMENT)){
			typeCombo.setSelectedItem(Bundle.getString("combo.announcement"));
		}
		
		Object roleManager = service.getConferenceManager().getRoleRef();
		if(roleManager != null){
			managerComboModel.setObjectSelected(roleManager);
		}
		
		Object roleModerator = service.getModerator().getRoleRef();
		if(roleModerator != null){
			moderatorComboModel.setObjectSelected(roleModerator);
		}
		
		List<Object> participantsSelected = new ArrayList<Object>();
		for(Participant participant : service.getParticipantList()){
			participantsSelected.add(participant.getRoleRef());
		}
		participantsTableModel.setObjectsSelected(participantsSelected);

		
		List<Object> observersSelected = new ArrayList<Object>();
		for(Observer observer : service.getObserverList()){
			observersSelected.add(observer.getRoleRef());
		}
		observersTableModel.setObjectsSelected(observersSelected);

	}

	public void populateObject() {
		
		if(service == null){
			service = new Conference();
		}
		
		service.setTitle(textField.getText());
		
		if(typeCombo.getSelectedItem().equals(Bundle.getString("combo.synchronous"))){
			service.setConferenceType(ConferenceTypeSelect.SYNCHRONOUS);
		}
		else if(typeCombo.getSelectedItem().equals(Bundle.getString("combo.announcement"))){
			service.setConferenceType(ConferenceTypeSelect.ANNOUNCEMENT);
		}
		else if(typeCombo.getSelectedItem().equals(Bundle.getString("combo.asynchronous"))){
			service.setConferenceType(ConferenceTypeSelect.ASYNCHRONOUS);
		} 
		else {
			service.setConferenceType(null);
		}
		
		Object roleManager = managerComboModel.getObjectSelected();
		ConferenceManager manager = new ConferenceManager();
		manager.setRoleRef(roleManager);
		service.setConferenceManager(manager);
		
		Object roleModerator = moderatorComboModel.getObjectSelected();
		Moderator moderator = new Moderator();
		moderator.setRoleRef(roleModerator);
		service.setModerator(moderator);
	
		service.getParticipantList().clear();
		for(Object role : participantsTableModel.getObjectsSelected()){
			Participant data = new Participant();
			data.setRoleRef(role);
			service.getParticipantList().add(data);
		}
		
		service.getObserverList().clear();
		for(Object role : observersTableModel.getObjectsSelected()){
			Observer data = new Observer();
			data.setRoleRef(role);
			service.getObserverList().add(data);
		}
		
		
		
	}

}
