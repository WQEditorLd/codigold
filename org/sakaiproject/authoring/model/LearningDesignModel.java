package org.sakaiproject.authoring.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.LearningDesign;
import org.imsglobal.jaxb.ld.Method;
import org.imsglobal.jaxb.ld.Play;
import org.sakaiproject.authoring.graph.Cell;
import org.sakaiproject.authoring.graph.Graph;
import org.sakaiproject.authoring.jgraphx.AuthoringGraphImpl;
import org.sakaiproject.authoring.listener.GraphDoubleClickListener;
import org.sakaiproject.authoring.listener.RightClickMouseGraphListener;
import org.sakaiproject.authoring.utils.LearningDesignUtils;

//TODO: REVIEW: vide trechos em comentário, analisar impacto da alteração dos atributos de classe - componentsModel / graph
//TODO: REVIEW: getLearningDesign() -> verificar pq estava criando um novo objeto sem "guardar", método get ia contra o "padrão" JavaBean.
public class LearningDesignModel {
	
	private LearningDesign learningDesign;
	
	private ComponentsModel componentsModel;   // = new ComponentsModel(); -- class instance ?!
	
	private Graph graph; // = new AuthoringGraphImpl(); -- class Instance?!
	
	public LearningDesignModel(){
		componentsModel = new ComponentsModel(); // NEW
		graph = new AuthoringGraphImpl();        // NEW
		
		graph.addMouseListener(new RightClickMouseGraphListener(graph, this));
		graph.addMouseListener(new GraphDoubleClickListener(graph, this));
	}
	
	public void setLearningDesign(LearningDesign learningDesign) {
		this.learningDesign = learningDesign;
		
		if(learningDesign != null){
			componentsModel.setComponents(learningDesign.getComponents());
		}
		else {
			componentsModel.setComponents(null);
		}
		
	}
	
	public void loadGraph(Object graphXML){
		if(graph != null){
			graph.setXML(graphXML);
			loadComponentCellsFromModel();
			graph.center();
		}
	}
	
	private void loadComponentCellsFromModel() {
		for(Cell cell : graph.getCells()){
			if(LearningDesignUtils.isComponent(cell.getObject())){
				String objectIdentifier = LearningDesignUtils.getIdentifier(cell.getObject());
				cell.setObject(componentsModel.getComponentWithIdentifier(objectIdentifier));	
			}
		}
	}
	
	public LearningDesign getLearningDesign() {
		return createLearningDesign(false);
	}
	
	public LearningDesign createLearningDesign(boolean loadMethodFromGraph) {
		
		//TODO: test, before "return new LearningDesign() without setting internal structure"
		if(learningDesign == null){
			learningDesign = new LearningDesign();  // OLD => return new LearningDesign();
		}
		
		// Não modifica o cabeçalho de um LD pré-existente ?!
		if ( learningDesign.getIdentifier() == null || learningDesign.getIdentifier().isEmpty())
			learningDesign.setIdentifier("ld" + System.currentTimeMillis());
		if ( learningDesign.getUri() == null || learningDesign.getUri().isEmpty())
			learningDesign.setUri("LD-URI");
		if ( learningDesign.getLevel() == null || learningDesign.getLevel().isEmpty())
			learningDesign.setLevel("A");
		
		// LD Editor / WebQuest Editor updates components (check for indirect update already made).
		learningDesign.setComponents(componentsModel.getComponents());
		
		// Clears method section and reloads from Graph 
		if ( loadMethodFromGraph )
			fillLearningDesignMethod();
		
		return learningDesign;
	}

	private void fillLearningDesignMethod() {
		if(learningDesign.getMethod() == null){
			learningDesign.setMethod(new Method());
		}
		
		learningDesign.getMethod().getPlayList().clear();
		
		for(Cell cell : graph.getCells()){
			if(cell.getObject() instanceof Play){
				learningDesign.getMethod().getPlayList().add((Play)cell.getObject());
			}
		}
		
	}

	public Graph getGraph() {
		return graph;
	}

	public ComponentsModel getComponentsModel() {
		return componentsModel;
	}

	public Collection<Resource> getResources(File directory) {
		
		List<Resource> resources = new ArrayList<Resource>();
		
		if(learningDesign.getLearningObjectives() != null){
			resources.addAll(LearningDesignUtils.getResources(
					learningDesign.getLearningObjectives().getItemList(), directory));
		}
		if(learningDesign.getPrerequisites() != null){
			resources.addAll(LearningDesignUtils.getResources(
					learningDesign.getPrerequisites().getItemList(), directory));
		}
		
		resources.addAll(componentsModel.getResources(directory));
		
		return resources;
	}

	public List<Play> getPlayListFromGraph() {
		fillLearningDesignMethod();
		return learningDesign.getMethod().getPlayList();
	}


}
