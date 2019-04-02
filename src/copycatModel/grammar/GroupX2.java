package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;

import copycatModel.implementations.AbstractDescriptorV1;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;

public class GroupX2 extends HowManyGroups implements Cloneable {
	
	private static final String descriptorName = "groupX2";
	private Group group1;
	private Group group2;

	public GroupX2(boolean codingDescriptor, Group group1, Group group2) 
			throws DescriptorsBuilderCriticalException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		ArrayList<AbstractDescriptorV1> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(DescGenSettings.COMPONENT_AUTO_POSITIONING, componentDescriptors);
	}
	
	@Override
	protected GroupX2 clone() throws CloneNotSupportedException {
		GroupX2 cloneGroupX2;
		Group cloneGroup1 = group1.clone();
		Group cloneGroup2 = group2.clone();
		try {
			cloneGroupX2 = new GroupX2(isCodingDescriptor, cloneGroup1, cloneGroup2);
		} catch (DescriptorsBuilderCriticalException e) {
			throw new CloneNotSupportedException("GroupX2 : error in clone() method.");
		}		
		return cloneGroupX2;
	}

	@Override
	protected ArrayList<AbstractDescriptorV1> buildListOfComponents(){
		ArrayList<AbstractDescriptorV1> componentDescriptors = new ArrayList<AbstractDescriptorV1>(
				Arrays.asList(group1, group2));
		return componentDescriptors;
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
	protected ArrayList<AbstractDescriptorV1> buildListOfRelevantComponentsForRelationBuilding() {
		ArrayList<AbstractDescriptorV1> listOfRelevantComponents = new ArrayList<AbstractDescriptorV1>();
		listOfRelevantComponents.add(group1);
		return listOfRelevantComponents;
	}	
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}


}
