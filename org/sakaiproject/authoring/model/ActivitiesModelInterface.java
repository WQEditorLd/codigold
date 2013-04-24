package org.sakaiproject.authoring.model;

import java.io.File;
import java.util.List;

import org.imsglobal.jaxb.content.Resource;
import org.imsglobal.jaxb.ld.Activities;
@Deprecated // checking
public interface ActivitiesModelInterface {

	public abstract Activities getActivities();

	public abstract void setActivities(Activities activities);

	public abstract void addActivity(Object activity);

	public abstract void removeActivity(Object activity);

	public abstract List<Resource> getResources(File directory);

	public abstract Object getActivityWithIdentifier(String objectIdentifier);

}