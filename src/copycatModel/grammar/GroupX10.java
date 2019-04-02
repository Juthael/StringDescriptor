package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;

public class GroupX10 extends HowManyGroups implements Cloneable {

	private static final String descriptorName = "groupX10";
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	private Group group5;
	private Group group6;
	private Group group7;
	private Group group8;
	private Group group9;
	private Group group10;
	
	public GroupX10(boolean codingDescriptor, Group group1, Group group2, Group group3, Group group4, Group group5,
			Group group6, Group group7, Group group8, Group group9, Group group10) 
					throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		this.group3 = group3.clone();
		this.group4 = group4.clone();
		this.group5 = group5.clone();
		this.group6 = group6.clone();
		this.group7 = group7.clone();
		this.group8 = group8.clone();
		this.group9 = group9.clone();
		this.group10 = group10.clone();
		ArrayList<AbstractDescriptorV1> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(DescGenSettings.COMPONENT_AUTO_POSITIONING, componentDescriptors);	
	}
	
	@Override
	protected GroupX10 clone() throws CloneNotSupportedException {
		GroupX10 cloneGroupX10;
		Group cloneableGroup1 = group1.clone();
		Group cloneableGroup2 = group2.clone();
		Group cloneableGroup3 = group3.clone();
		Group cloneableGroup4 = group4.clone();
		Group cloneableGroup5 = group5.clone();
		Group cloneableGroup6 = group6.clone();
		Group cloneableGroup7 = group7.clone();
		Group cloneableGroup8 = group8.clone();
		Group cloneableGroup9 = group9.clone();
		Group cloneableGroup10 = group10.clone();
		try {
			cloneGroupX10 = new GroupX10(isCodingDescriptor, cloneableGroup1, cloneableGroup2, 
					cloneableGroup3, cloneableGroup4, cloneableGroup5, cloneableGroup6, cloneableGroup7, 
					cloneableGroup8, cloneableGroup9, cloneableGroup10);
		} catch (DescriptorsBuilderCriticalException e) {
			throw new CloneNotSupportedException("GroupX10 : error error in clone() method.");
		}
		return cloneGroupX10;
	}	

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(group1, group2, group3, group4, group5, group6, group7, group8, group9, group10));
		return componentDescriptors;
	}
	
	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = new ArrayList<AbstractDescriptorV1>();
		listOfRelevantComponents.add(group1);
		return listOfRelevantComponents;
	}
	
	@Override
	public ArrayList<String> getListOfRelevantPropertiesWithPath(){
		ArrayList<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (AbstractDescriptorV1 componentDescriptor : listOfRelevantComponents) {
			ArrayList<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			listOfRelevantPropertiesWithPath.addAll(listOfComponentRelevantPropertiesWithPath);
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}

}
