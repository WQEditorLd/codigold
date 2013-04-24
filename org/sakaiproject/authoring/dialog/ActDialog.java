package org.sakaiproject.authoring.dialog;

import java.awt.TextField;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.Act;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.CompleteActPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class ActDialog extends JDialog implements ObjectDialog<Act> {

	private static final long serialVersionUID = -6186101235667979250L;
	
	private TextField textField = new TextField();
	
	private CompleteActPanel completeActPanel;
	
	private Act act;
	
	public ActDialog(Act act) {
		
		super(Authoring.getInstance(), true);
		
		this.act = act;
		
		completeActPanel = new CompleteActPanel(act.getRolePartList());
		
		FormLayout layout = new FormLayout();		
		PanelBuilder builder = new PanelBuilder();
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xy(1, 1));
		builder.add(textField, cc.xy(1, 3));
		builder.addSeparator(Bundle.getString("title.completeact"), cc.xy(1, 5));		
		builder.add(completeActPanel, cc.xy(1, 7));
		
		
		builder.add(new ObjectDialogButtonBarPanel(this), cc.xy(1, 9));
		
		add(builder.getPanel());
		
		setTitle(Bundle.getString("title.act"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}
	
	
	public void populateObject() {
		act.setTitle(textField.getText());
		act.setCompleteAct(completeActPanel.getCompleteAct());
	}
	
	
	public void setObject(Act act){
		this.act = act;
		textField.setText(act.getTitle());
		completeActPanel.setCompleteAct(act.getCompleteAct());		
	}
	

	public Act getObject() {
		return act;
	}


}
