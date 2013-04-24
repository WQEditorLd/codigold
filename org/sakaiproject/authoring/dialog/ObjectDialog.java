package org.sakaiproject.authoring.dialog;

public interface ObjectDialog<T> {
	
	static final String OBJECT_CHANGE_EVENT = "ObjectChangedEvent";

	public T getObject();
	
	public void setObject(T object);
	
	public void setVisible(boolean visible);	

	public void populateObject();
	
}
