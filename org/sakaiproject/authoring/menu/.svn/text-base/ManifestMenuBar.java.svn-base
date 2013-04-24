package org.sakaiproject.authoring.menu;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.sakaiproject.authoring.menu.action.EditCompleteUoLAction;
import org.sakaiproject.authoring.menu.action.EditUnitOfLearningTitleAction;
import org.sakaiproject.authoring.menu.action.EditObjectivesAction;
import org.sakaiproject.authoring.menu.action.EditPrerequisitesAction;
import org.sakaiproject.authoring.menu.action.NewUnitOfLearningAction;
import org.sakaiproject.authoring.menu.action.OpenUnitOfLearningAction;
import org.sakaiproject.authoring.menu.action.SaveAsUnitOfLearningAction;
import org.sakaiproject.authoring.menu.action.SaveUnitOfLearningAction;
import org.sakaiproject.authoring.model.ManifestModel;
import org.sakaiproject.authoring.utils.Bundle;

public class ManifestMenuBar extends JMenuBar {
		
	private static final long serialVersionUID = 684611268883277370L;
	
	private ManifestModel manifestModel;
	
	private JMenuItem newMenu;
	private JMenuItem openMenu;
	private JMenuItem saveMenu;
	private JMenuItem saveasMenu;
	
	private JMenuItem nameMenu;
	private JMenuItem objetivesMenu;
	private JMenuItem prerequisitesMenu;
	private JMenuItem completeMenu;
	
	public ManifestMenuBar(ManifestModel manifestModel){
		
		super();
		this.manifestModel = manifestModel;
		
		addFileMenu();
	}

	private void addFileMenu() {
		
		JMenu menuFile = new JMenu(Bundle.getString("menu.file"));
		
		newMenu = new JMenuItem(new NewUnitOfLearningAction(manifestModel));
		menuFile.add(newMenu);
		
		openMenu = new JMenuItem(new OpenUnitOfLearningAction(manifestModel));
		menuFile.add(openMenu);
		
		saveMenu = new JMenuItem(new SaveUnitOfLearningAction(manifestModel));
		menuFile.add(saveMenu);
		
		saveasMenu = new JMenuItem(new SaveAsUnitOfLearningAction(manifestModel));
		menuFile.add(saveasMenu);
		
		add(menuFile);		
		
		menuFile.addItemListener(new ItemListener(){

			public void itemStateChanged(ItemEvent arg0) {
				saveMenu.setEnabled(manifestModel.containsManifest());
				saveasMenu.setEnabled(manifestModel.containsManifest());
			}
			
		});
		
		JMenu menuEdit = new JMenu(Bundle.getString("menu.edit"));
		
		nameMenu = new JMenuItem(new EditUnitOfLearningTitleAction(manifestModel.getLearningDesignModel()));
		menuEdit.add(nameMenu);
		
		objetivesMenu = new JMenuItem(new EditObjectivesAction(manifestModel.getLearningDesignModel()));
		menuEdit.add(objetivesMenu);
		
		prerequisitesMenu = new JMenuItem(new EditPrerequisitesAction(manifestModel.getLearningDesignModel()));
		menuEdit.add(prerequisitesMenu);
		
		completeMenu = new JMenuItem(new EditCompleteUoLAction(manifestModel.getLearningDesignModel()));
		menuEdit.add(completeMenu);
		
		
		add(menuEdit);
		
		menuEdit.addItemListener(new ItemListener(){


			public void itemStateChanged(ItemEvent e) {
				nameMenu.setEnabled(manifestModel.containsManifest());
				objetivesMenu.setEnabled(manifestModel.containsManifest());
				prerequisitesMenu.setEnabled(manifestModel.containsManifest());
				completeMenu.setEnabled(manifestModel.containsManifest());
			}
			
		});
	}

	

}




