package org.sakaiproject.authoring.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.Roles;
import org.imsglobal.jaxb.ld.Staff;
import org.sakaiproject.authoring.event.EventController;
import org.sakaiproject.authoring.utils.LearningDesignUtils;

public class RolesModel {
	
	private Roles roles;
	
	public static final String ROLE_ADDED_EVENT = "RoleAddEvent";
	public static final String ROLE_REMOVED_EVENT = "RoleRemoveEvent";
	public static final String ROLE_CHANGED_EVENT = "RoleChangedEvent";
	
	private int roleCount;

	public Roles getRoles() {
		
		if(roles == null){
			roles = new Roles();
		}
		
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
		
		if(roles == null){
			return;
		}
		
		for(Learner learner : roles.getLearnerList()){
			EventController.getInstance().dispatchEvent(ROLE_ADDED_EVENT, learner);
		}
		for(Staff staff : roles.getStaffList()){
			EventController.getInstance().dispatchEvent(ROLE_ADDED_EVENT, staff);
		}
		
		roleCount = roles.getLearnerList().size() + roles.getStaffList().size();
	}
	
	public void addRole(Object role){
		
		if(roles == null){
			roles = new Roles();
		}
		
		if(!(role instanceof Learner) && !(role instanceof Staff)){
			return;
		}
		if(role instanceof Learner){
			((Learner)role).setIdentifier("Role"+roleCount++);
			roles.getLearnerList().add((Learner)role);
		}
		if(role instanceof Staff){
			((Staff)role).setIdentifier("Role"+roleCount++);
			roles.getStaffList().add((Staff) role);
		}
		EventController.getInstance().dispatchEvent(ROLE_ADDED_EVENT, role);		
	}
	
	public void removeRole(Object role){
		
		if(roles == null){
			return;
		}
		
		if(!(role instanceof Learner) && !(role instanceof Staff)){
			return;
		}
		if(role instanceof Learner){
			roles.getLearnerList().remove((Learner)role);
		}
		if(role instanceof Staff){
			roles.getStaffList().remove((Staff) role);
		}
		EventController.getInstance().dispatchEvent(ROLE_REMOVED_EVENT, role);			
	}
	
	public List<Resource> getResources(File directory){
		
		List<Resource> resources = new ArrayList<Resource>();
		
		if(roles == null){
			return resources;
		}
		
		for(Learner learner : roles.getLearnerList()){	
			if(learner.getInformation() != null){
				resources.addAll(LearningDesignUtils.getResources(
						learner.getInformation().getItemList(), directory));
			}
		}
		for(Staff staff : roles.getStaffList()){	
			if(staff.getInformation() != null){
				resources.addAll(LearningDesignUtils.getResources(
						staff.getInformation().getItemList(), directory));
			}
		}
		
		return resources;
	}

	public Object getRoleWithIdentifier(String objectIdentifier) {
		if(roles == null){
			return null;
		}
		for(Learner learner : roles.getLearnerList()){
			if(learner.getIdentifier().equals(objectIdentifier)){
				return learner;
			}
		}
		for(Staff staff : roles.getStaffList()){
			if(staff.getIdentifier().equals(objectIdentifier)){
				return staff;
			}
		}
		return null;
	}

}
