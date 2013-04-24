package org.sakaiproject.authoring.panel;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.sakaiproject.authoring.utils.Bundle;

public class DurationPanel extends JPanel {
	
	private static final long serialVersionUID = -7911830274334826917L;
	
	private JLabel yearLabel = new JLabel(Bundle.getString("label.short.years"));
	private JLabel monthsLabel = new JLabel(Bundle.getString("label.short.months"));
	private JLabel daysLabel = new JLabel(Bundle.getString("label.short.days"));
	private JLabel hoursLabel = new JLabel(Bundle.getString("label.short.hours"));
	private JLabel minsLabel = new JLabel(Bundle.getString("label.short.mins"));
	private JLabel secsLabel = new JLabel(Bundle.getString("label.short.secs"));
	
	private JTextField yearField = new JTextField(3);
	private JTextField monthsField = new JTextField(3);
	private JTextField daysField = new JTextField(3);
	private JTextField hoursField = new JTextField(3);
	private JTextField minsField = new JTextField(3);
	private JTextField secsField = new JTextField(3);
	

	public DurationPanel(){
		init();
	}

	private void init() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(yearLabel);
		add(yearField);
		add(monthsLabel);
		add(monthsField);
		add(daysLabel);
		add(daysField);
		add(hoursLabel);
		add(hoursField);
		add(minsLabel);
		add(minsField);
		add(secsLabel);
		add(secsField);	
			
	}
	
	public void setDuration(Duration duration){
		yearField.setText(Integer.toString(duration.getYears()));
		monthsField.setText(Integer.toString(duration.getMonths()));
		daysField.setText(Integer.toString(duration.getDays()));
		hoursField.setText(Integer.toString(duration.getDays()));
		minsField.setText(Integer.toString(duration.getDays()));
		secsField.setText(Integer.toString(duration.getDays()));
	}
	
	public Duration getDuration(){
		try {
			DatatypeFactory dbf = DatatypeFactory.newInstance();
			Duration duration = dbf.newDuration(true, 
					Integer.parseInt(yearField.getText()), 
					Integer.parseInt(monthsField.getText()), 
					Integer.parseInt(daysField.getText()), 
					Integer.parseInt(hoursField.getText()), 
					Integer.parseInt(minsField.getText()), 
					Integer.parseInt(secsField.getText()));
			
			return duration;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			return null;
		}
	}
}
