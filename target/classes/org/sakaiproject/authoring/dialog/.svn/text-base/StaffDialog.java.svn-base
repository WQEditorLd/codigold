package org.sakaiproject.authoring.dialog;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import org.imsglobal.jaxb.ld.Staff;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.panel.RoleGeneralPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

public class StaffDialog extends JDialog implements ObjectDialog<Staff> {

	private static final long serialVersionUID = -6186101235667979250L;
	
	private RoleGeneralPanel generalPanel;
	private ItemModelPanel informationPanel;

	private JTabbedPane tabbedPane;
	
	private Staff staff;
	
	public StaffDialog() {
		super(Authoring.getInstance(), true);
		
		tabbedPane = new JTabbedPane();
		
		generalPanel = new RoleGeneralPanel(); 
		tabbedPane.add(Bundle.getString("panel.general"), generalPanel);
		
		informationPanel = new ItemModelPanel(Bundle.getString("panel.information"));
		tabbedPane.add(Bundle.getString("panel.informations"), informationPanel);
		
		add(tabbedPane, BorderLayout.CENTER);
		add(new ObjectDialogButtonBarPanel(this), BorderLayout.SOUTH);
		
		setTitle(Bundle.getString("title.staff"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));

	}
	
	public void populateObject() {
		generalPanel.populateStaff(staff);
		staff.setInformation(informationPanel.getItemModel());
	}
	

	public void setObject(Staff staff){
		this.staff = staff;
		generalPanel.populatePanel(staff);
		if(staff.getInformation() != null){
			informationPanel.setItemModel(staff.getInformation());
		}
	}

	public Staff getObject() {
		return staff;
	}

}
