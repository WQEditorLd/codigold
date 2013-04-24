package org.sakaiproject.authoring.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.imsglobal.jaxb.ld.Environment;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class EnvironmentMiniDialog extends JDialog {

	private static final long serialVersionUID = -6186101235667979250L;
	

	private JTextField textField = new JTextField(15);
	
	
	private Object environment;
	
	public EnvironmentMiniDialog() {
		super(Authoring.getInstance(), true);
		init();
	}

	private void init() {
		
		FormLayout layout = new FormLayout("left:p", "p, 2dlu, p, 7dlu, p");
		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xy(1, 1));
		builder.add(textField, cc.xy(1, 3));
		
		JPanel buttonsPanel = createButtonsPanel();		
		builder.add(buttonsPanel, cc.xy(1, 5));
		
		add(builder.getPanel());
		
		setTitle(Bundle.getString("title.environment"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	private JPanel createButtonsPanel() {
		
		JButton okButton = new JButton(Bundle.getString("button.ok"));
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				populateEnvironment();
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
	
	private void populateEnvironment() {
		
		Environment environment = new Environment();
		
		environment.setTitle(textField.getText());
		
		this.environment = environment;
	}

	public Object getEnvironment() {
		return environment;
	}

	public void setEnvironment(Object environment) {
		this.environment = environment;
	}


	
	

}
