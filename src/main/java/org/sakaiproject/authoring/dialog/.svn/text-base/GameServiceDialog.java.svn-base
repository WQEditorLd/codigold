package org.sakaiproject.authoring.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.imsglobal.jaxb.ld.DidaticItem;
import org.imsglobal.jaxb.ld.GameItem;
import org.imsglobal.jaxb.ld.GamePerformance;
import org.imsglobal.jaxb.ld.GameService;
import org.imsglobal.ld.constants.GameUse;
import org.imsglobal.ld.constants.Level;
import org.imsglobal.ld.constants.Skill;
import org.sakaiproject.authoring.model.RolesModel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.textfield.ItemTextField;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class GameServiceDialog extends JDialog implements ObjectDialog<GameService> {

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.game");

	private JTextField titleField = new JTextField(40);
	
	private ItemTextField begginerGameField = new ItemTextField();
	private ItemTextField intermediateGameField = new ItemTextField();
	private ItemTextField advancedGameField = new ItemTextField();
	
	private ItemTextField begginerDidaticItem = new ItemTextField();
	private ItemTextField intermediateDidaticItem = new ItemTextField();
	private ItemTextField advancedDidaticItem = new ItemTextField();
	
	private JTextField begginerItemResponse = new JTextField();
	private JTextField intermediateItemResponse = new JTextField();
	private JTextField advancedItemResponse = new JTextField();
	
	private JTextField averageLevelField = new JTextField(2);
	private JTextField aboveAverageLevelField = new JTextField(2);
	
	
	private JComboBox gameUseCombo = new JComboBox(new Object[] { "",
			Bundle.getString("combo.cooperative"),
			Bundle.getString("combo.individual")});
	
	private JComboBox skillCombo = new JComboBox(new Object[] { "",
			Bundle.getString("combo.write"),
			Bundle.getString("combo.read"),
			Bundle.getString("combo.draw")});
	
//	private JTable participantsTable;
//	private TableSelectionModel<Object> participantsTableModel;


	private GameService service;
	
	public GameServiceDialog(JDialog parent, RolesModel rolesModel) {
		
		super(parent, true);
		
		FormLayout layout = new FormLayout("100dlu, 2dlu, 100dlu", 
				"p, 2dlu, p, 7dlu, " + // titulo
				"p, 2dlu, p, 2dlu, p, 2dlu, p, 7dlu, " + //jogo
				"p, 2dlu, p, 2dlu, p, 7dlu, " + // performance
				"p, 2dlu, p, 2dlu, p, 2dlu, p, 2dlu, p, 2dlu, p, 2dlu, p, 7dlu, " + //kit didatico
				"p, 2dlu, p, 7dlu, " + //uso do jogo
				"p, 2dlu, p, 7dlu, " + //habilidade
				"p, 2dlu, 100dlu, 7dlu, p "); //participantes e botoes		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.addSeparator(Bundle.getString("label.name"), cc.xyw(1, 1, 3));
		builder.add(titleField, cc.xyw(1, 3, 3));
		
		builder.addSeparator(Bundle.getString("label.game"), cc.xyw(1, 5, 3));
		builder.add(new JLabel(Bundle.getString("label.begginer")), (CellConstraints) cc.xy(1,7).clone(), begginerGameField, cc.xy(3, 7));
		builder.add(new JLabel(Bundle.getString("label.intermediate")), (CellConstraints)cc.xy(1,9).clone(), intermediateGameField, cc.xy(3, 9));
		builder.add(new JLabel(Bundle.getString("label.advanced")), (CellConstraints)cc.xy(1,11).clone(), advancedGameField, cc.xy(3, 11));
		
		builder.addSeparator(Bundle.getString("label.performancelevel"), cc.xyw(1, 13, 3));
		builder.add(new JLabel(Bundle.getString("label.averagelevel")), (CellConstraints)cc.xy(1,15).clone(), averageLevelField, cc.xy(3, 15));
		builder.add(new JLabel(Bundle.getString("label.aboveaveragelevel")), (CellConstraints)cc.xy(1,17).clone(), aboveAverageLevelField, cc.xy(3, 17));
		
		builder.addSeparator(Bundle.getString("label.didatickit"), cc.xyw(1, 19, 3));
		builder.add(new JLabel(Bundle.getString("label.begginer")), (CellConstraints)cc.xy(1,21).clone(), begginerDidaticItem, cc.xy(3, 21));
		builder.add(new JLabel(Bundle.getString("label.answers")+ " " + Bundle.getString("label.begginer")), (CellConstraints)cc.xy(1,23).clone(), begginerItemResponse, cc.xy(3, 23));
		builder.add(new JLabel(Bundle.getString("label.intermediate")), (CellConstraints)cc.xy(1,25).clone(), intermediateDidaticItem, cc.xy(3, 25));
		builder.add(new JLabel(Bundle.getString("label.answers")+ " " + Bundle.getString("label.intermediate")), (CellConstraints)cc.xy(1,27).clone(), intermediateItemResponse, cc.xy(3, 27));
		builder.add(new JLabel(Bundle.getString("label.advanced")), (CellConstraints)cc.xy(1,29).clone(), advancedDidaticItem, cc.xy(3, 29));
		builder.add(new JLabel(Bundle.getString("label.answers")+ " " + Bundle.getString("label.advanced")), (CellConstraints)cc.xy(1,31).clone(), advancedItemResponse, cc.xy(3, 31));
	
		
		builder.addSeparator(Bundle.getString("label.gameuse"), cc.xyw(1, 33, 3));
		gameUseCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
		builder.add(gameUseCombo, cc.xyw(1, 35, 3));
		
		builder.addSeparator(Bundle.getString("label.skill"), cc.xyw(1, 37, 3));
		skillCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
		builder.add(skillCombo, cc.xyw(1, 39, 3));
		
		List<Object> roles = new ArrayList<Object>();
		roles.addAll(rolesModel.getRoles().getLearnerList());
		roles.addAll(rolesModel.getRoles().getStaffList());
		
//		participantsTableModel = new TableSelectionModel<Object>(roles, Bundle.getString("title.role"));
//		participantsTable = new JTable(participantsTableModel);
//		participantsTable.getColumnModel().getColumn(0).setMaxWidth(25);
//		builder.addSeparator(Bundle.getString("label.participants"),cc.xyw(1, 35, 3));
//		builder.add(new JScrollPane(participantsTable), cc.xyw(1, 37, 3));
		
		builder.add(new ObjectDialogButtonBarPanel(this), cc.xyw(1, 41, 3));
		
		add(builder.getPanel());
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	}

	public GameService getObject() {
		return service;
	}

	public void setObject(GameService object) {

		this.service = object;

		setTitle();
		
		setGame();
		
		setDidaticKit();
		
		setPerformanceLevel();
		
		setGameUse();
		
		setSkill();
		
		//setParticipants();


	}

	private void setGame() {
		
		for(GameItem item : service.getGameItemList()){
			if(item.getGameLevel().equals(Level.BEGGINER)){
				begginerGameField.setItem(item.getItem());
			}
			if(item.getGameLevel().equals(Level.INTERMEDIATE)){
				intermediateGameField.setItem(item.getItem());
			}
			if(item.getGameLevel().equals(Level.ADVANCED)){
				advancedGameField.setItem(item.getItem());
			}
		}
		
	}
	
	private void setDidaticKit() {
		
		for(DidaticItem item : service.getDidaticKit()){
			if(item.getGameLevel().equals(Level.BEGGINER)){
				begginerDidaticItem.setItem(item.getItem());
				begginerItemResponse.setText(item.getAnswer());
			}
			if(item.getGameLevel().equals(Level.INTERMEDIATE)){
				intermediateDidaticItem.setItem(item.getItem());
				intermediateItemResponse.setText(item.getAnswer());
			}
			if(item.getGameLevel().equals(Level.ADVANCED)){
				advancedDidaticItem.setItem(item.getItem());
				advancedItemResponse.setText(item.getAnswer());
			}
		}
		
	}
	
	private void setPerformanceLevel(){
		if(service.getGamePerformance() != null){
			if(service.getGamePerformance().getAboveAverage() != null){
				aboveAverageLevelField.setText(service.getGamePerformance().getAboveAverage().toString());
			}
			if(service.getGamePerformance().getAverage() != null){
				averageLevelField.setText(service.getGamePerformance().getAverage().toString());
			}
		}
	}

	private void setTitle() {
		titleField.setText(service.getTitle());
	}

//	private void setParticipants() {
//		List<Object> participantsSelected = new ArrayList<Object>();
//		for(Participant participant : service.getParticipantList()){
//			participantsSelected.add(participant.getRoleRef());
//		}
//		participantsTableModel.setObjectsSelected(participantsSelected);
//	}

	private void setSkill() {
		if(service.getSkill() == null){
			skillCombo.setSelectedItem("");
		}
		else if(service.getGameUse().equals(Skill.READ)){
			skillCombo.setSelectedItem(Bundle.getString("combo.read"));
		}
		else if(service.getGameUse().equals(Skill.WRITE)){
			skillCombo.setSelectedItem(Bundle.getString("combo.write"));
		}
		else if(service.getGameUse().equals(Skill.DRAW)){
			skillCombo.setSelectedItem(Bundle.getString("combo.draw"));
		}
	}

	private void setGameUse() {
		if(service.getGameUse() == null){
			gameUseCombo.setSelectedItem("");
		}
		else if(service.getGameUse().equals(GameUse.COOPERATIVE)){
			gameUseCombo.setSelectedItem(Bundle.getString("combo.cooperative"));
		}
		else if(service.getGameUse().equals(GameUse.INDIVIDUAL)){
			gameUseCombo.setSelectedItem(Bundle.getString("combo.individual"));
		}
	}

	public void populateObject() {
		
		if(service == null){
			service = new GameService();
		}
		
		populateTitle();
		
		populateGame();
		
		populatePerformanceLevel();
		
		populateDidaticKit();
		
		populateGameUse();
		
		populateSkill();
	
		//populateParticipants();
		
	}

	private void populateGame() {
		
		service.getGameItemList().clear();
		
		if(begginerGameField.getItem() != null){
			GameItem item = new GameItem();
			item.setGameLevel(Level.BEGGINER);
			item.setItem(begginerGameField.getItem());
			service.getGameItemList().add(item);
		}
		if(intermediateGameField.getItem() != null){
			GameItem item = new GameItem();
			item.setGameLevel(Level.INTERMEDIATE);
			item.setItem(intermediateGameField.getItem());
			service.getGameItemList().add(item);
		}
		if(advancedGameField.getItem() != null){
			GameItem item = new GameItem();
			item.setGameLevel(Level.ADVANCED);
			item.setItem(advancedGameField.getItem());
			service.getGameItemList().add(item);
		}
		
	}
	
	private void populateDidaticKit() {
		
		service.getDidaticKit().clear();
		
		if(begginerDidaticItem.getItem() != null){
			DidaticItem item = new DidaticItem();
			item.setGameLevel(Level.BEGGINER);
			item.setItem(begginerDidaticItem.getItem());
			item.setAnswer(begginerItemResponse.getText());
			service.getDidaticKit().add(item);
		}
		if(intermediateDidaticItem.getItem() != null){
			DidaticItem item = new DidaticItem();
			item.setGameLevel(Level.INTERMEDIATE);
			item.setItem(intermediateDidaticItem.getItem());
			item.setAnswer(intermediateItemResponse.getText());
			service.getDidaticKit().add(item);
		}
		if(advancedDidaticItem.getItem() != null){
			DidaticItem item = new DidaticItem();
			item.setGameLevel(Level.ADVANCED);
			item.setItem(advancedDidaticItem.getItem());
			item.setAnswer(advancedItemResponse.getText());
			service.getDidaticKit().add(item);
		}
		
	}
	
	private void populatePerformanceLevel(){
		service.setGamePerformance(new GamePerformance());
		if(averageLevelField.getText() != null && !averageLevelField.getText().equals("")){
			service.getGamePerformance().setAverage(Integer.parseInt(averageLevelField.getText()));
		}
		if(aboveAverageLevelField.getText() != null && !aboveAverageLevelField.getText().equals("")){
			service.getGamePerformance().setAboveAverage(Integer.parseInt(aboveAverageLevelField.getText()));
		}
	}

	private void populateTitle() {
		service.setTitle(titleField.getText());
	}

//	private void populateParticipants() {
//		service.getParticipantList().clear();
//		for(Object role : participantsTableModel.getObjectsSelected()){
//			Participant data = new Participant();
//			data.setRoleRef(role);
//			service.getParticipantList().add(data);
//		}
//	}

	private void populateSkill() {
		if(skillCombo.getSelectedItem().equals(Bundle.getString("combo.read"))){
			service.setSkill(Skill.READ);
		}
		else if(skillCombo.getSelectedItem().equals(Bundle.getString("combo.write"))){
			service.setSkill(Skill.WRITE);
		}
		else if(skillCombo.getSelectedItem().equals(Bundle.getString("combo.draw"))){
			service.setSkill(Skill.DRAW);
		}
		else {
			service.setSkill(null);
		}
	}

	private void populateGameUse() {
		if(gameUseCombo.getSelectedItem().equals(Bundle.getString("combo.cooperative"))){
			service.setGameUse(GameUse.COOPERATIVE);
		}
		else if(gameUseCombo.getSelectedItem().equals(Bundle.getString("combo.individual"))){
			service.setGameUse(GameUse.INDIVIDUAL);
		}
		else {
			service.setGameUse(null);
		}
	}

}
