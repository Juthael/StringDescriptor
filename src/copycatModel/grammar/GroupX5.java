package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;

public class GroupX5 extends HowManyGroups implements Cloneable {

	private static final String descriptorName = "groupX5";
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	private Group group5;
	
	public GroupX5(boolean codingDescriptor, Group group1, Group group2, Group group3, Group group4, Group group5) 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		this.group3 = group3.clone();
		this.group4 = group4.clone();
		this.group5 = group5.clone();
		ArrayList<AbstractDescriptorV1> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(DescGenSettings.COMPONENT_AUTO_POSITIONING, componentDescriptors);	
	}
	
	@Override
	protected GroupX5 clone() throws CloneNotSupportedException {
		GroupX5 cloneGroupX5;
		Group cloneableGroup1 = group1.clone();
		Group cloneableGroup2 = group2.clone();
		Group cloneableGroup3 = group3.clone();
		Group cloneableGroup4 = group4.clone();
		Group cloneableGroup5 = group5.clone();
		try {
			cloneGroupX5 = new GroupX5(isCodingDescriptor, cloneableGroup1, cloneableGroup2, 
					cloneableGroup3, cloneableGroup4, cloneableGroup5);
		} catch (DescriptorsBuilderCriticalException e) {
			throw new CloneNotSupportedException("GroupX5 : error error in clone() method.");
		}
		return cloneGroupX5;
	}	

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(group1, group2, group3, group4, group5));
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
