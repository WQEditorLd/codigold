package org.sakaiproject.authoring.panel;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.xml.datatype.DatatypeFactory;

import org.imsglobal.jaxb.ld.CompletePlay;
import org.imsglobal.jaxb.ld.TimeLimit;
import org.imsglobal.jaxb.ld.WhenLastActCompleted;
import org.sakaiproject.authoring.utils.Bundle;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class CompletePlayPanel extends JPanel {

	private static final long serialVersionUID = 4912300458634612305L;
	
	private CompletePlay completePlay = new CompletePlay();
	
	private TextField durationField = new TextField();
	
	private JRadioButton noneButton = new JRadioButton(Bundle.getString("button.none"), true);
	private JRadioButton lastActButton = new JRadioButton(Bundle.getString("button.lastact"));
	private JRadioButton timeLimitButton = new JRadioButton(Bundle.getString("button.timelimit"));
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	public CompletePlay getCompletePlay() {
		
		completePlay.setWhenLastActCompleted(null);
		completePlay.setTimeLimit(null);
		
		if(noneButton.isSelected()){
			return null;
		}
		
		if(lastActButton.isSelected()){
			completePlay.setWhenLastActCompleted(new WhenLastActCompleted());
			return completePlay;
		}
		
		try{
			if(timeLimitButton.isSelected()){
				TimeLimit timeLimit = new TimeLimit();
				timeLimit.setValue(DatatypeFactory.newInstance().newDuration(durationField.getText().toUpperCase()));
				completePlay.setTimeLimit(timeLimit);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		return completePlay;
	}


	public void setCompletePlay(CompletePlay completePlay) {
		
		this.completePlay = completePlay;
		durationField.setText("");
		noneButton.setSelected(true);
		
		
		if(completePlay == null){
			this.completePlay = new CompletePlay();
			return;
		}
		
		if(completePlay.getWhenLastActCompleted() != null){
			lastActButton.setSelected(true);
		} else if(completePlay.getTimeLimit() != null){
			timeLimitButton.setSelected(true);
			durationField.setText(completePlay.getTimeLimit().getValue().toString());
		} 
		
	}

	public CompletePlayPanel(){
		
		FormLayout layout = new FormLayout("left:p, 5dlu, 75dlu, 3dlu, p", "p, 4dlu, p");
		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		buttonGroup.add(noneButton);
		buttonGroup.add(lastActButton);
		buttonGroup.add(timeLimitButton);
		noneButton.setSelected(true);
		durationField.setEditable(false);
		
		ActionListener listener = new ActionListener(){

			
			public void actionPerformed(ActionEvent e) {
				durationField.setEditable(timeLimitButton.isSelected());
			}
			
		};
		
		noneButton.addActionListener(listener);
		lastActButton.addActionListener(listener);
		timeLimitButton.addActionListener(listener);
		
		builder.add(noneButton, cc.xy(1, 1));
		builder.add(lastActButton, cc.xyw(3, 1, 3));
		builder.add(timeLimitButton, cc.xy(1, 3));
		builder.add(durationField, cc.xy(3,3));
		
		JLabel label = new JLabel(Bundle.getString("label.durationexample"));
		label.setForeground(Color.BLUE);
		builder.add(label, cc.xy(5,3));
		
		add(builder.getPanel());
	}

	

}
