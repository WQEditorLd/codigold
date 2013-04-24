package org.sakaiproject.authoring.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.Staff;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class RoleMiniDialog extends JDialog {

	private static final long serialVersionUID = -6186101235667979250L;
		
	private JTextField textField = new JTextField(15);
	private JRadioButton learnerButton = new JRadioButton(Bundle.getString("title.learner"),true);
	private JRadioButton staffButton= new JRadioButton(Bundle.getString("title.staff"));
	
	private Object role;
	
	public RoleMiniDialog() {
		super(Authoring.getInstance(), true);
		init();
	}

	private void init() {
		
		FormLayout layout = new FormLayout("left:p", "p, 2dlu, p, 7dlu, p, 2dlu, p, 2dlu, p, 7dlu, p");
		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(learnerButton);
		buttonGroup.add(staffButton);
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xy(1, 1));
		builder.add(textField, cc.xy(1, 3));
		builder.addSeparator(Bundle.getString("title.type"), cc.xy(1, 5));
		builder.add(learnerButton, cc.xy(1, 7));
		builder.add(staffButton, cc.xy(1, 9));
		
		JPanel buttonsPanel = createButtonsPanel();		
		builder.add(buttonsPanel, cc.xy(1, 11));
		
		add(builder.getPanel());
		
		setTitle(Bundle.getString("label.role"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}
	
	private JPanel createButtonsPanel() {
		
		JButton okButton = new JButton(Bundle.getString("button.ok"));
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				populateRole();
				setVisible(false);
			}
		});
		
		JButton cancelButton = new JButton(Bundle.getString("button.cancel"));
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		return ButtonBarFactory.buildOKCancelBar(okButton, cancelButton);
	}
	
	private void populateRole() {
		if(learnerButton.isSelected()){
			role = new Learner();
		}
		if(staffButton.isSelected()){
			role = new Staff();
		}
		
		try {
			role.getClass().getMethod("setTitle", String.class).invoke(role, textField.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getRole() {
		return role;
	}

	public void setRole(Object role) {
		this.role = role;
	}
	
	

}
