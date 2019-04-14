package copycatModel.synTreeModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.synTreeModel.impl.SynTreeIntegrableElementImpl;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public class GroupX8 extends HowManyGroups implements Cloneable {

	private static final String descriptorName = "groupX8";
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	private Group group5;
	private Group group6;
	private Group group7;
	private Group group8;
	
	public GroupX8(boolean codingDescriptor, Group group1, Group group2, Group group3, Group group4, Group group5,
			Group group6, Group group7, Group group8) 
					throws SynTreeGenerationException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		this.group3 = group3.clone();
		this.group4 = group4.clone();
		this.group5 = group5.clone();
		this.group6 = group6.clone();
		this.group7 = group7.clone();
		this.group8 = group8.clone();
		List<SynTreeIntegrableElementImpl> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, componentDescriptors);	
	}
	
	@Override
	protected GroupX8 clone() throws CloneNotSupportedException {
		GroupX8 cloneGroupX8;
		Group cloneableGroup1 = group1.clone();
		Group cloneableGroup2 = group2.clone();
		Group cloneableGroup3 = group3.clone();
		Group cloneableGroup4 = group4.clone();
		Group cloneableGroup5 = group5.clone();
		Group cloneableGroup6 = group6.clone();
		Group cloneableGroup7 = group7.clone();
		Group cloneableGroup8 = group8.clone();
		try {
			cloneGroupX8 = new GroupX8(isCodingByDecomposition, cloneableGroup1, cloneableGroup2, 
					cloneableGroup3, cloneableGroup4, cloneableGroup5, cloneableGroup6, cloneableGroup7, 
					cloneableGroup8);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("GroupX8 : error error in clone() method.");
		}
		return cloneGroupX8;
	}	

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(group1, group2, group3, group4, group5, group6, group7, group8));
		return componentDescriptors;
	}
	
	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = new ArrayList<SynTreeIntegrableElementImpl>();
		listOfRelevantComponents.add(group1);
		return listOfRelevantComponents;
	}	
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeIntegrableElementImpl componentDescriptor : listOfRelevantComponents) {
			List<String> listOfComponentRelevantPropertiesWithPath = 
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
