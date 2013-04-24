package org.sakaiproject.authoring.dialog;

import javax.swing.JDialog;

import org.imsglobal.jaxb.ld.LearningObject;
import org.sakaiproject.authoring.panel.EnvironmentItemGeneralPanel;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.panel.ObjectDialogButtonBarPanel;
import org.sakaiproject.authoring.utils.Bundle;
import org.sakaiproject.authoring.utils.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class LearningObjectDialog extends JDialog implements ObjectDialog<LearningObject>{

	private static final long serialVersionUID = -1761977183175910360L;

	private static final String TITLE = Bundle.getString("title.learningobject");
		
	private EnvironmentItemGeneralPanel generalPanel = new EnvironmentItemGeneralPanel();
	private ItemModelPanel itensPanel = new ItemModelPanel(Bundle.getString("panel.itens"));
		
	private LearningObject object;

	public LearningObjectDialog(EnvironmentDialog parent){
		super(parent, true);
		
		FormLayout layout = new FormLayout("left:p", "p, 0dlu, p, 7dlu, p");		
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();
		
		CellConstraints cc = new CellConstraints();
		
		builder.add(generalPanel, cc.xy(1, 1));
		builder.add(itensPanel, cc.xy(1, 3));
		builder.add(new ObjectDialogButtonBarPanel(this), cc.xy(1, 5));
		
		add(builder.getPanel());
		
		setTitle(TITLE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		pack();
		setLocation(SwingUtil.getCenterPosition(this));
	
	}
	
	public LearningObject getObject() {
		return object;
	}

	public void setObject(LearningObject object) {
		this.object = object;
		
		generalPanel.setTitle(object.getTitle());
		generalPanel.setIsVisible(object.isIsvisible());
		
		itensPanel.setItemList(object.getItemList());
		
	}
	
	public void populateObject() {
		
		if(object == null){
			object = new LearningObject();
		}
		
		object.setTitle(generalPanel.getTitle());
		object.setIsvisible(generalPanel.getIsVisible());
		object.getItemList().clear();
		object.getItemList().addAll(itensPanel.getItemList());
		
	}
	
}
