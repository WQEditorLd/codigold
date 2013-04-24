package org.sakaiproject.authoring.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.imsglobal.jaxb.ld.EmailData;
import org.imsglobal.jaxb.ld.RoleRef;
import org.imsglobal.jaxb.ld.SendMail;
import org.imsglobal.ld.constants.SendMailSelect;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.table.TableSelectionModel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class SendMailDialog extends JDialog implements ObjectDialog<SendMail> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.sendmail");

	private JTextField textField = new JTextField(40);
	
	private JComboBox selectCombo = new JComboBox(new Object[] { "",
			Bundle.getString("combo.allpersonsinrole"),
			Bundle.getString("combo.personsinrole") });
	
	private JTable rolesTable;
	private TableSelectionModel<Object> rolesTableModel;

	private SendMail service;
	
	public SendMailDialog(JDialog parent, RolesModel rolesModel) {
		
		super(parent, true);
		
		FormLayout layout = new FormLayout("left:200dlu", "p, 2dlu, p, 7dlu, p, 2dlu, p, 7dlu, p, 2dlu, 100dlu, 7dlu, p");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xy(1, 1));
		builder.add(textField, cc.xy(1, 3));
		
		builder.addSeparator(Bundle.getString("label.select"), cc.xy(1, 5));
		builder.add(selectCombo, cc.xy(1, 7));
		
		List<Object> roles = new ArrayList<Object>();
		roles.addAll(rolesModel.getRoles().getLearnerList());
		roles.addAll(rolesModel.getRoles().getStaffList());
		rolesTableModel = new TableSelectionModel<Object>(roles, Bundle.getString("title.roles"));
		rolesTable = new JTable(rolesTableModel);
		rolesTable.getColumnModel().getColumn(0).setMaxWidth(25);
		
		builder.addSeparator(Bundle.getString("label.roles"), cc.xy(1, 9));
		builder.add(new JScrollPane(rolesTable), cc.xy(1, 11));
		
		builder.add(new ObjectDialogButtonBarPanel(this), cc.xy(1, 13));
		
		add(builder.getPanel());
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	public SendMail getObject() {
		return service;
	}

	public void setObject(SendMail object) {

		this.service = object;

		textField.setText(object.getTitle());
		
		List<Object> rolesSelected = new ArrayList<Object>();
		for(EmailData mailData : service.getEmailDataList()){
			rolesSelected.add(mailData.getRoleRef().getRef());
		}
		rolesTableModel.setObjectsSelected(rolesSelected);
		
		if(service.getSelect() == null){
			selectCombo.setSelectedItem("");
		}
		else if(service.getSelect().equals(SendMailSelect.ALL_PERSONS_IN_ROLE)){
			selectCombo.setSelectedItem(Bundle.getString("combo.allpersonsinrole"));
		}
		else if(service.getSelect().equals(SendMailSelect.PERSONS_IN_ROLE)){
			selectCombo.setSelectedItem(Bundle.getString("combo.personsinrole"));
		}

	}

	public void populateObject() {
		
		if(service == null){
			service = new SendMail();
		}
		
		service.setTitle(textField.getText());
		
		if(selectCombo.getSelectedItem().equals(Bundle.getString("combo.allpersonsinrole"))){
			service.setSelect(SendMailSelect.ALL_PERSONS_IN_ROLE);
		}
		else if(selectCombo.getSelectedItem().equals(Bundle.getString("combo.personsinrole"))){
			service.setSelect(SendMailSelect.PERSONS_IN_ROLE);
		} else {
			service.setSelect(null);
		}
		
		service.getEmailDataList().clear();
		for(Object role : rolesTableModel.getObjectsSelected()){
			EmailData data = new EmailData();
			RoleRef roleRef = new RoleRef();
			roleRef.setRef(role);
			data.setRoleRef(roleRef);
			service.getEmailDataList().add(data);
		}
		
		
		
	}

}
