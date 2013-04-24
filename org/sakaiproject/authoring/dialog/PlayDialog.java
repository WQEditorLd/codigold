package org.sakaiproject.authoring.dialog;

import java.awt.TextField;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.Play;
import org.sakaiproject.authoring.Authoring;
import org.sakaiproject.authoring.panel.CompletePlayPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


public class PlayDialog extends JDialog implements ObjectDialog<Play> {

	private static final long serialVersionUID = -6186101235667979250L;
	
	private TextField textField = new TextField();
	
	private CompletePlayPanel completePlayPanel;
	
	private Play play;
	
	public PlayDialog(Play play) {
		
		super(Authoring.getInstance(), true);
		
		this.play = play;
		
		completePlayPanel = new CompletePlayPanel();
		
		FormLayout layout = new FormLayout("p", "p, 2dlu, p, 7dlu, p, 0dlu, p, 7dlu, p");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xy(1, 1));
		builder.add(textField, cc.xy(1, 3));
		builder.addSeparator(Bundle.getString("title.completeplay"), cc.xy(1, 5));		
		builder.add(completePlayPanel, cc.xy(1, 7));
		
		
		builder.add(new ObjectDialogButtonBarPanel(this), cc.xy(1, 9));
		
		add(builder.getPanel());
		
		setTitle(Bundle.getString("title.play"));
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}
	
	
	public void populateObject() {
		play.setTitle(textField.getText());
		play.setCompletePlay(completePlayPanel.getCompletePlay());
	}
	
	
	public void setObject(Play play){
		this.play = play;
		textField.setText(play.getTitle());
		completePlayPanel.setCompletePlay(play.getCompletePlay());		
	}
	

	public Play getObject() {
		return play;
	}


}
