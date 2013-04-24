package org.sakaiproject.authoring.model;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.imsglobal.jaxb.content.Manifest;
import org.imsglobal.jaxb.content.Organizations;
import org.imsglobal.jaxb.content.Resources;
import org.imsglobal.jaxb.ld.LearningDesign;

import org.sakaiproject.authoring.utils.GraphUtil;
import org.sakaiproject.authoring.utils.ManifestUtil;
import org.w3c.dom.Node;

/**
 * ManifestModel
 * 
 * Classe responsável por carregar os arquivos de grafo e manifest nas
 * estruturas correspondentes.
 * @see Manifest
 * @see LearningDesign
 *   
 * @author Márcio Varcharvsky
 * @author Elisa
 *
 */
public class ManifestModel {
	
	private static final String GRAPH_FILE = "graph.xml";
	
	private File manifestFile;	
	private Manifest manifest;
	
	//submodel
	private LearningDesignModel learningDesignModel = new LearningDesignModel();
	
	public LearningDesignModel getLearningDesignModel() {
		return learningDesignModel;
	}

	// auxiliares
//	public LearningDesign getLearningDesign() {
//		return getLearningDesignModel().getLearningDesign();
//	}

	public Node getGraphXML() {
		return (Node) getLearningDesignModel().getGraph().getXML();
	}

	
	// Without reading graph objects for method in XML
	public Manifest getManifestForWebQuest() {
		return getManifest(false);
	}
	
	// Default, considers graph and realoads method in LD-XML
	public Manifest getManifest() {
		return getManifest(true);
	}
	
	@SuppressWarnings("unchecked")
	public Manifest getManifest(boolean reloadMethod) {
			
		manifest.setIdentifier("manifest");
		
		if(manifest.getOrganizations() == null){
			manifest.setOrganizations(new Organizations());
		}
		
		if(manifest.getOrganizations().getAny().size() == 0){
			manifest.getOrganizations().getAny().add(new JAXBElement<LearningDesign>(new QName(""), 
					LearningDesign.class, null));
		}
		
		if ( reloadMethod )
			((JAXBElement<LearningDesign>)manifest.getOrganizations().getAny().get(0)).setValue(
					learningDesignModel.createLearningDesign(true));  // Reloads Method from Graph
		else
			((JAXBElement<LearningDesign>)manifest.getOrganizations().getAny().get(0)).setValue(
					learningDesignModel.getLearningDesign());
		
		if (manifest.getResources() == null){
			manifest.setResources(new Resources());
		}
		manifest.getResources().getResourceList().clear();
		manifest.getResources().getResourceList().addAll(learningDesignModel.getResources(manifestFile.getParentFile()));
		
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
		
		if(manifest != null){
			learningDesignModel.setLearningDesign(ManifestUtil.getLearningDesign(manifest));
		} else {
			learningDesignModel.setLearningDesign(null);
		}
		
		// TODO: debug why graph is not being reset when new after editing. 
		if(manifestFile != null){
			loadGraphFromFile();
		} else {
			learningDesignModel.getGraph().clear();
		}
	}

	private void loadGraphFromFile() {
		try {
			learningDesignModel.loadGraph(GraphUtil.openGraphInFile(getGraphFile()));
		} catch (Exception e) {			
			// TODO: Exception Handling to be Reviewed.
			learningDesignModel.getGraph().clear(); // enable loading without graph
			e.printStackTrace();
		}
	}

	public File getManifestFile() {
		return manifestFile;
	}

	public void setManifestFile(File manifestFile) {
		this.manifestFile = manifestFile;		
	}

	public boolean containsManifest() {
		return manifest != null;
	}

	public File getGraphFile(){
		File graphFile = new File(manifestFile.getParent(), GRAPH_FILE);
		if(!graphFile.exists()){			
			try {
				graphFile.createNewFile();
				//TODO: OPCIONAL! no LD, se as seções correspondentes ao graph.xml (Method, Act, Play, Role-Part) existirem no imsmanifest.xml, deveria recuperar e criar um grafo default (sem layout definido)
			} catch (IOException e) {
				//TODO: Exception Handling to be Reviewed (verificar, mensagem (null?))
				e.printStackTrace();
			}
		}
		return graphFile;
	}

}
