package org.sakaiproject.authoring.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.Conference;
import org.imsglobal.jaxb.ld.DidaticItem;
import org.imsglobal.jaxb.ld.Environment;
import org.imsglobal.jaxb.ld.Environments;
import org.imsglobal.jaxb.ld.GameItem;
import org.imsglobal.jaxb.ld.GameService;
import org.imsglobal.jaxb.ld.IndexSearch;
import org.imsglobal.jaxb.ld.Item;
import org.imsglobal.jaxb.ld.LearningObject;
import org.imsglobal.jaxb.ld.SendMail;
import org.imsglobal.jaxb.ld.Service;
import org.sakaiproject.authoring.event.EventController;
import org.sakaiproject.authoring.utils.LearningDesignUtils;

public class EnvironmentsModel {
	
	private Environments environments;
	
	private int environmentCount;
	
	public static final String ENVIRONMENT_ADDED_EVENT = "EnvironmentAddedEvent";
	public static final String ENVIRONMENT_REMOVED_EVENT = "EnvironmentRemovedEvent";
	public static final String ENVIRONMENT_CHANGED_EVENT = "EnvironmentChangedEvent";

	public Environments getEnvironments() {
		
		if(environments == null){
			environments = new Environments();
		}
		
		return environments;
	}

	public void setEnvironments(Environments environments) {
		this.environments = environments;
		
		if(environments == null){
			return;
		}
		
		for(Environment environment : environments.getEnvironmentList()){
			EventController.getInstance().dispatchEvent(ENVIRONMENT_ADDED_EVENT, environment);
		}
		
		environmentCount = environments.getEnvironmentList().size();
	}
	
	public void addEnvironment(Environment environment){
		
		if(environments == null){
			environments = new Environments();
		}
		environment.setIdentifier("Environment"+environmentCount++);
		environments.getEnvironmentList().add(environment);
		
		EventController.getInstance().dispatchEvent(ENVIRONMENT_ADDED_EVENT, environment);		
	}
	
	public void removeEnvironment(Object environment){
		
		if(environments == null){
			return;
		}
		
		environments.getEnvironmentList().remove(environment);
		
		EventController.getInstance().dispatchEvent(ENVIRONMENT_REMOVED_EVENT, environment);		
	}
	
	
	public static Object getServiceItem(Service service){
		if(service.getSendMail() != null){
			return service.getSendMail();
		}
		if(service.getConference() != null){
			return service.getConference();
		}
		if(service.getIndexSearch() != null){
			return service.getIndexSearch();
		}
		if(service.getGameService() != null){
			return service.getGameService();
		}
		return null;
		
	}
	
	public static Service getService(Object serviceItem){
		Service service = new Service();
		if(serviceItem instanceof SendMail){
			service.setSendMail((SendMail)serviceItem);
		}
		if(serviceItem instanceof Conference){
			service.setConference((Conference)serviceItem);
		}
		if(serviceItem instanceof IndexSearch){
			service.setIndexSearch((IndexSearch)serviceItem);
		}
		if(serviceItem instanceof GameService){
			service.setGameService((GameService)serviceItem);
		}
		return service;
		
	}
	
	public List<Resource> getResources(File directory){
		
		List<Resource> resources = new ArrayList<Resource>();
		
		if(environments == null){
			return resources;
		}
		
		for(Environment environment : environments.getEnvironmentList()){
			for(Object object : environment.getLearningObjectOrServiceOrEnvironmentRef()){
				if(object instanceof LearningObject){
					LearningObject learningObject = (LearningObject) object;
					resources.addAll(LearningDesignUtils.getResources(learningObject.getItemList(), directory));
				}
				if(object instanceof Service){
					Service service = (Service) object;
					if ( service != null ) {
						GameService gameService = service.getGameService();
						List<Item> items = new ArrayList<Item>();
						if (gameService != null) {
							for(GameItem gameItem : gameService.getGameItemList()){
								items.add(gameItem.getItem());
							}						
							for(DidaticItem didaticItem : gameService.getDidaticKit()){
							items.add(didaticItem.getItem());
							}
						}
						resources.addAll(LearningDesignUtils.getResources(items, directory));
					}
				}
			}
		}
		
		return resources;
	}
	
	public Object getEnvironmentWithIdentifier(String objectIdentifier) {
		if(environments == null){
			return null;
		}
		for(Object env : environments.getEnvironmentList()){
			if(objectIdentifier.equals(LearningDesignUtils.getIdentifier(env))){
				return env;
			}
		}
		return null;
	}

}
