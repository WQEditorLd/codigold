package org.sakaiproject.authoring.panel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.imsglobal.jaxb.ld.Environment;
import org.imsglobal.jaxb.ld.EnvironmentRef;
import org.imsglobal.jaxb.ld.LearningActivity;
import org.sakaiproject.authoring.model.EnvironmentsModel;
import org.sakaiproject.authoring.panel.ActivityGeneralPanel;
import org.sakaiproject.authoring.panel.CompleteActivityPanel;
import org.sakaiproject.authoring.panel.ItemModelPanel;
import org.sakaiproject.authoring.utils.Bundle;

/*
 * LearningActivityPanel
 * 
 * Essa classe permite a edição da atividade de aprendizado. Era anteriormente uma Dialog somente e foi revisada
 * para suportar atividades que respeitem a templates pré-definidos como WebQuest.
 * 
 * TODO: verificar como usar o panel sem o botão OK (aciona PopulateObject), provavel listener nos botões das seções do WebQuest. 
 */
public class LearningActivityPanel extends JPanel {

	private static final long serialVersionUID = -1761977183175910360L;
	
	private ActivityGeneralPanel generalPanel;
	private ItemModelPanel descriptionPanel;
	private ItemModelPanel prerequisitesPanel;
	private ItemModelPanel objectivesPanel;
	private CompleteActivityPanel completeActivityPanel;
	
	private JTabbedPane tabbedPane;
	
	private LearningActivity activity;

	public LearningActivityPanel(EnvironmentsModel environmentModel){	
		
		setLayout(new BorderLayout());
		
		tabbedPane = new JTabbedPane();
		
		generalPanel = new ActivityGeneralPanel(environmentModel); 
		tabbedPane.add(Bundle.getString("panel.general"), generalPanel);
		
		descriptionPanel = new ItemModelPanel(Bundle.getString("panel.description"));
		tabbedPane.add(Bundle.getString("panel.description"), descriptionPanel);
		
		prerequisitesPanel = new ItemModelPanel(Bundle.getString("panel.prerequisite"));
		tabbedPane.add(Bundle.getString("panel.prerequisites"), prerequisitesPanel);
		
		objectivesPanel = new ItemModelPanel(Bundle.getString("panel.objective"));
		tabbedPane.add(Bundle.getString("panel.objectives"), objectivesPanel);
		
		completeActivityPanel = new CompleteActivityPanel();
		tabbedPane.add(Bundle.getString("panel.completeactivity"), completeActivityPanel);
		
		add(tabbedPane, BorderLayout.CENTER);		
		
	}
	
	public LearningActivity getObject() {
		return activity;
	}

	public void setObject(LearningActivity activity) {
		this.activity = activity;
		
		generalPanel.setTitle(activity.getTitle());
		generalPanel.setIsVisible(activity.isIsvisible());
		generalPanel.setParametersField(activity.getParameters());		
		//TODO: TESTAR -> campo parametros não é setado de forma correta ?!
		
		List<Environment> environments = new ArrayList<Environment>();
		for(EnvironmentRef ref : activity.getEnvironmentRefList()){
			environments.add((Environment)ref.getRef());
		}
		generalPanel.setEnvironmentsSelected(environments);
		
		
		descriptionPanel.setItemModel(activity.getActivityDescription());
		
		objectivesPanel.setItemModel(activity.getLearningObjectives());
		
		prerequisitesPanel.setItemModel(activity.getPrerequisites());
		
		// TODO: complete não está salvando corretamente (mudei para Indeterminado e não salvou)
		completeActivityPanel.setCompleteActivity(activity.getCompleteActivity());
		
	}
	
	public void populateObject() {
		activity.setTitle(generalPanel.getTitle());
		if (generalPanel.getParametersField()!= null && !generalPanel.getParametersField().isEmpty() ) 
			activity.setParameters(generalPanel.getParametersField());
		activity.setIsvisible(generalPanel.getIsVisible());
		activity.setActivityDescription(descriptionPanel.getItemModel());
		activity.setLearningObjectives(objectivesPanel.getItemModel());
		activity.setPrerequisites(prerequisitesPanel.getItemModel());
		activity.setCompleteActivity(completeActivityPanel.getCompleteActivity());

		// Não implementados: Metadata, OnCompletion

		List<EnvironmentRef> refs = new ArrayList<EnvironmentRef>();
		for(Environment environment : generalPanel.getEnvironmentsSelected()){
			EnvironmentRef ref = new EnvironmentRef();
			ref.setRef(environment);
			refs.add(ref);
		}
		activity.getEnvironmentRefList().clear();
		activity.getEnvironmentRefList().addAll(refs);
	}
	
}
