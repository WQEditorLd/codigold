package org.sakaiproject.authoring.panel;

import java.awt.Color;
import java.math.BigInteger;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.Staff;
import org.imsglobal.ld.constants.CreateNew;
import org.imsglobal.ld.constants.MatchPersons;
import org.sakaiproject.authoring.utils.Bundle;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class RoleGeneralPanel extends JPanel{

	private static final long serialVersionUID = -3860214966773475639L;
	
	private JLabel titleLabel = new JLabel(Bundle.getString("label.name"));
	private JTextField titleField = new JTextField(100);
	private JLabel minPersonsLabel = new JLabel(Bundle.getString("label.minpersons"));
	private JTextField minPersonsField = new JTextField(5);
	private JLabel maxPersonsLabel = new JLabel(Bundle.getString("label.maxpersons"));
	private JTextField maxPersonsField = new JTextField(5);
	private JLabel createNewLabel = new JLabel(Bundle.getString("label.createnew"));
	private JComboBox createNewCombo = new JComboBox(new Object[]{"", 
			Bundle.getString("combo.allowed"), 
			Bundle.getString("combo.notallowed")});
	private JLabel matchPersonsLabel = new JLabel(Bundle.getString("label.matchpersons"));
	private JComboBox matchPersonsCombo = new JComboBox(new Object[]{"", 
			Bundle.getString("combo.exclusivelyinroles"), 
			Bundle.getString("combo.notexclusively")});
	
	public RoleGeneralPanel(){
		
		FormLayout layout = new FormLayout("right:p, 3dlu, left:100dlu", "p, 5dlu, p, 5dlu, p, 5dlu, p, 5dlu, p");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		titleLabel.setForeground(Color.BLUE);
		minPersonsLabel.setForeground(Color.BLUE);
		minPersonsField.setText("0");
		maxPersonsLabel.setForeground(Color.BLUE);
		
		createNewLabel.setForeground(Color.BLUE);
		matchPersonsLabel.setForeground(Color.BLUE);
		
		builder.add(titleLabel, (CellConstraints)cc.xy(1,1).clone(), titleField, cc.xy(3, 1));
		builder.add(minPersonsLabel, (CellConstraints)cc.xy(1,3).clone(), minPersonsField, cc.xy(3, 3));
		builder.add(maxPersonsLabel, (CellConstraints)cc.xy(1,5).clone(), maxPersonsField, cc.xy(3, 5));
		builder.add(createNewLabel, (CellConstraints)cc.xy(1,7).clone(), createNewCombo, cc.xy(3, 7));
		builder.add(matchPersonsLabel, (CellConstraints)cc.xy(1,9).clone(), matchPersonsCombo, cc.xy(3, 9));
		
		add(builder.getPanel());
	}

	public void setTitle(String title){
		if(title != null){
			titleField.setText(title);
		}
	}
	
	public String getTitle(){
		return titleField.getText();
	}
	
	public void setMinPersons(BigInteger minPersons){
		minPersonsField.setText(minPersons.toString());
	}
	
	public BigInteger getMinPersons(){
		if(minPersonsField.getText() == null || minPersonsField.getText().equals("")){
			return null;
		}
		return new BigInteger(minPersonsField.getText());
	}
	
	public void setMaxPersons(BigInteger maxPersons){
		maxPersonsField.setText(maxPersons.toString());
	}
	
	public BigInteger getMaxPersons(){
		if(maxPersonsField.getText() == null || maxPersonsField.getText().equals("")){
			return null;
		}
		return new BigInteger(maxPersonsField.getText());
	}
	
	public String getCreateNew(){
		if(Bundle.getString("combo.allowed").equalsIgnoreCase(createNewCombo.getSelectedItem().toString())){
			return CreateNew.ALOWED;
		}
		if(Bundle.getString("combo.notallowed").equalsIgnoreCase(createNewCombo.getSelectedItem().toString())){
			return CreateNew.NOT_ALOWED;
		}
		return null;
	}
	
	public void setCreateNew(String createNew){
		if(createNew.equalsIgnoreCase(CreateNew.ALOWED)){
			createNewCombo.setSelectedItem(Bundle.getString("combo.allowed"));
		}
		else if(createNew.equalsIgnoreCase(CreateNew.NOT_ALOWED)){
			createNewCombo.setSelectedItem(Bundle.getString("combo.notallowed"));
		} else {
			createNewCombo.setSelectedIndex(0);
		}
	}
	
	public String getMatchPersons(){
		if(Bundle.getString("combo.exclusivelyinroles").equalsIgnoreCase(
				matchPersonsCombo.getSelectedItem().toString())){
			return MatchPersons.EXCLUSIVELY_IN_ROLES;
		}
		if(Bundle.getString("combo.notexclusively").equalsIgnoreCase(
				matchPersonsCombo.getSelectedItem().toString())){
			return MatchPersons.NOT_EXCLUSIVELY;
		}
		return null;
	}
	
	public void setMatchPersons(String matchPersons){
		if(matchPersons.equalsIgnoreCase(MatchPersons.EXCLUSIVELY_IN_ROLES)){
			matchPersonsCombo.setSelectedItem(Bundle.getString("combo.exclusivelyinroles"));
		}
		else if(matchPersons.equalsIgnoreCase(MatchPersons.NOT_EXCLUSIVELY)){
			matchPersonsCombo.setSelectedItem(Bundle.getString("combo.notexclusively"));
		} else {
			matchPersonsCombo.setSelectedIndex(0);
		}
	}
	
	public void populatePanel(Staff staff){
		if(staff.getTitle() != null){
			setTitle(staff.getTitle());
		}
		if(staff.getMinPersons() != null){
			setMinPersons(staff.getMinPersons());
		}
		if(staff.getMaxPersons() != null){
			setMaxPersons(staff.getMaxPersons());
		}
		if(staff.getCreateNew() != null){
			setCreateNew(staff.getCreateNew());
		}
		if(staff.getMatchPersons() != null){
			setMatchPersons(staff.getMatchPersons());
		}
	}
	
	public void populateStaff(Staff staff){
		staff.setTitle(getTitle());
		staff.setCreateNew(getCreateNew());
		staff.setMatchPersons(getMatchPersons());
		staff.setMinPersons(getMinPersons());
		staff.setMaxPersons(getMaxPersons());
	}
	
	public void populatePanel(Learner learner){
		if(learner.getTitle() != null){
			setTitle(learner.getTitle());
		}
		if(learner.getMinPersons() != null){
			setMinPersons(learner.getMinPersons());
		}
		if(learner.getMaxPersons() != null){
			setMaxPersons(learner.getMaxPersons());
		}
		if(learner.getCreateNew() != null){
			setCreateNew(learner.getCreateNew());
		}
		if(learner.getMatchPersons() != null){
			setMatchPersons(learner.getMatchPersons());
		}
	}
	
	public void populateLearner(Learner learner){
		learner.setTitle(getTitle());
		learner.setCreateNew(getCreateNew());
		learner.setMatchPersons(getMatchPersons());
		learner.setMinPersons(getMinPersons());
		learner.setMaxPersons(getMaxPersons());
	}
	
	public void populateRole(Object role){
		if(role instanceof Learner){
			populateLearner((Learner) role);
			
		}
		if(role instanceof Staff){
			populateStaff((Staff) role);
		}
	}
}
