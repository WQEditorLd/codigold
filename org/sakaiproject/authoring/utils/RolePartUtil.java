package org.sakaiproject.authoring.utils;

import java.util.ArrayList;
import java.util.List;

import org.imsglobal.jaxb.ld.ActivityStructure;
import org.imsglobal.jaxb.ld.ActivityStructureRef;
import org.imsglobal.jaxb.ld.Learner;
import org.imsglobal.jaxb.ld.LearningActivity;
import org.imsglobal.jaxb.ld.LearningActivityRef;
import org.imsglobal.jaxb.ld.RolePart;
import org.imsglobal.jaxb.ld.RoleRef;
import org.imsglobal.jaxb.ld.Staff;
import org.imsglobal.jaxb.ld.SupportActivity;
import org.imsglobal.jaxb.ld.SupportActivityRef;
import org.sakaiproject.authoring.graph.Graph;

public class RolePartUtil {

	/*
	 * Iterates over Edges selected in graph, recovering Vertex Source and Target.
	 * For each Source/Target Pair creates a RolePart with activity-ref / role-ref filled. 
	 */
	public static List<RolePart> getRolePartsSelected(Graph graph) {
		List<RolePart> rolePartList = new ArrayList<RolePart>();
		for(Object edge : graph.getEdgesSelected()){			
			RolePart rolePart = createRolePart(); 
			addObjectToRolePart(rolePart, graph.getSourceVertex(edge).getObject());
			addObjectToRolePart(rolePart, graph.getTargetVertex(edge).getObject());
			rolePartList.add(rolePart);			
		}
		return rolePartList;
	}

	
	public static List<RolePart> getWebQuestRolePartList(Object activity, Learner defaultLearner, Staff defaultStaff) {
		List<RolePart> rolePartList = new ArrayList<RolePart>();
		RolePart rolePart = createRolePart();
		LearningActivity la = (LearningActivity) activity;
		
		if( !la.getIdentifier().equals("instructor") ){ // TODO: improve - objetivo é inibir o aluno de visualizar a página do professor.		
			addObjectToRolePart(rolePart, activity);
			addObjectToRolePart(rolePart, defaultLearner);
			rolePartList.add(rolePart);
		}
		
		rolePart = createRolePart();
		addObjectToRolePart(rolePart, activity);
		addObjectToRolePart(rolePart, defaultStaff);
		rolePartList.add(rolePart);
		
		return rolePartList;
	}
	
	private static RolePart createRolePart() {
		RolePart rolePart = new RolePart();
		rolePart.setIdentifier("RolePart"+Integer.toString((int)(Math.random()*100000)));
		return rolePart;
	}
	
	private static void addObjectToRolePart(RolePart rolePart, Object object) {
		
		if(object instanceof LearningActivity){
			LearningActivityRef ref = new LearningActivityRef();
			ref.setRef(object);
			rolePart.setLearningActivityRef(ref);
		}
		if(object instanceof SupportActivity){
			SupportActivityRef ref = new SupportActivityRef();
			ref.setRef(object);
			rolePart.setSupportActivityRef(ref);
		}
		if(object instanceof ActivityStructure){
			ActivityStructureRef ref = new ActivityStructureRef();
			ref.setRef(object);
			rolePart.setActivityStructureRef(ref);
		}
		if(LearningDesignUtils.isRole(object)){
			RoleRef ref = new RoleRef();
			ref.setRef(object);
			rolePart.setRoleRef(ref);
		}
		
	}

	
}
