package org.sakaiproject.authoring.panel;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.datatype.DatatypeFactory;

import org.imsglobal.jaxb.ld.CompleteUnitOfLearning;
import org.imsglobal.jaxb.ld.Play;
import org.imsglobal.jaxb.ld.TimeLimit;
import org.imsglobal.jaxb.ld.WhenPlayCompleted;
import org.sakaiproject.authoring.table.PlaySelectionModel;
import org.sakaiproject.authoring.utils.Bundle;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class CompleteUnitOfLearningPanel extends JPanel {

	private static final long serialVersionUID = 4912300458634612305L;
	
	private CompleteUnitOfLearning completeUoL = new CompleteUnitOfLearning();
	
	private TextField durationField = new TextField();
	
	private JRadioButton noneButton = new JRadioButton(Bundle.getString("button.none"), true);
	private JRadioButton playButton = new JRadioButton(Bundle.getString("button.whenplaycompletedlist"));
	private JRadioButton timeLimitButton = new JRadioButton(Bundle.getString("button.timelimit"));
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	private JTable playsTable;
	private PlaySelectionModel playsModel;
	
	public CompleteUnitOfLearning getCompleteUol() {
		
		completeUoL.getWhenPlayCompletedList().clear();
		completeUoL.setTimeLimit(null);
		
		if(noneButton.isSelected()){
			return null;
		}
		
		if(playButton.isSelected()){
			completeUoL.getWhenPlayCompletedList().addAll(getWhenPlayCompletedList());
			return completeUoL;
		}
		
		try{
			if(timeLimitButton.isSelected()){
				TimeLimit timeLimit = new TimeLimit();
				timeLimit.setValue(DatatypeFactory.newInstance().newDuration(durationField.getText().toUpperCase()));
				completeUoL.setTimeLimit(timeLimit);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		return completeUoL;
	}

	private List<WhenPlayCompleted> getWhenPlayCompletedList() {
		List<Play> plays = playsModel.getObjectsSelected();
		
		List<WhenPlayCompleted> playCompletedList = new ArrayList<WhenPlayCompleted>();
		
		for(Play play : plays){
			WhenPlayCompleted completed = new WhenPlayCompleted();
			completed.setRef(play);
			playCompletedList.add(completed);
		}
		
		return playCompletedList;
	}
	
	private List<Play> getPlays(List<WhenPlayCompleted> whenPlayCompletedList){
		List<Play> plays = new ArrayList<Play>();
		
		for(WhenPlayCompleted completed : whenPlayCompletedList){
			Play play = (Play) completed.getRef();
			plays.add(play);
		}
		
		return plays;
	}

	public void setCompleteUoL(CompleteUnitOfLearning completeUoL) {
		
		this.completeUoL = completeUoL;
		durationField.setText("");
		noneButton.setSelected(true);
		
		if(completeUoL == null){
			this.completeUoL = new CompleteUnitOfLearning();
			return;
		}
		
		if(completeUoL.getWhenPlayCompletedList() != null && 
				completeUoL.getWhenPlayCompletedList().size() != 0){
			playButton.setSelected(true);
			playsModel.setObjectsSelected(getPlays(completeUoL.getWhenPlayCompletedList()));
		} else if(completeUoL.getTimeLimit() != null){
			timeLimitButton.setSelected(true);
			durationField.setText(completeUoL.getTimeLimit().getValue().toString());
		} 
		
	}

	public CompleteUnitOfLearningPanel(List<Play> plays){
		FormLayout layout = new FormLayout("left:p, 5dlu, 75dlu, 3dlu, p", "p, 4dlu, p, 4dlu, 150dlu");
		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		buttonGroup.add(noneButton);
		buttonGroup.add(playButton);
		buttonGroup.add(timeLimitButton);
		noneButton.setSelected(true);
		durationField.setEditable(false);
		
		ActionListener listener = new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				durationField.setEditable(timeLimitButton.isSelected());
				playsModel.setSelectable(playButton.isSelected());
				playsTable.setEnabled(playButton.isSelected());
			}
			
		};
		
		noneButton.addActionListener(listener);
		playButton.addActionListener(listener);
		timeLimitButton.addActionListener(listener);
		
		builder.add(noneButton, cc.xy(1, 1));
		builder.add(playButton, cc.xyw(3, 1, 3));
		builder.add(timeLimitButton, cc.xy(1, 3));
		builder.add(durationField, cc.xy(3,3));
		
		JLabel label = new JLabel(Bundle.getString("label.durationexample"));
		label.setForeground(Color.BLUE);
		builder.add(label, cc.xy(5,3));
		
		playsModel = new PlaySelectionModel(plays);
		playsTable = new JTable(playsModel);
		playsTable.getColumnModel().getColumn(0).setMaxWidth(25);
		
		builder.add(new JScrollPane(playsTable), cc.xyw(1, 5, 5));
		
		add(builder.getPanel());
	}

	

}
