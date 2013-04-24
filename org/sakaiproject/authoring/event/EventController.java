package org.sakaiproject.authoring.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventController {
	
	private static EventController instance;
	
	private EventController(){}
	
	public static EventController getInstance(){
		if(instance == null){
			instance = new EventController();
		}
		return instance;
	}
	
	private Map<String, List<EventListener>> listenersMap = new HashMap<String, List<EventListener>>();
	
	public void dispatchEvent(String eventName, Object ... args){
		
		if(!listenersMap.containsKey(eventName)){
			return;
		}
		
		List<EventListener> listeners = listenersMap.get(eventName);
		
		for(EventListener listener : listeners){
			listener.invoke(args);
		}
	}
	
	public void addListener(String eventName, EventListener listener){
		if(!listenersMap.containsKey(eventName)){
			listenersMap.put(eventName, new ArrayList<EventListener>());
		}
		listenersMap.get(eventName).add(listener);
	}
	
}
