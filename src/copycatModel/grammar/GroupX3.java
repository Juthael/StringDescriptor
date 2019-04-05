package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public class GroupX3 extends HowManyGroups implements Cloneable {

	private static final String descriptorName = "groupX3";
	private Group group1;
	private Group group2;
	private Group group3;
	
	public GroupX3(boolean codingDescriptor, Group group1, Group group2, Group group3) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		this.group3 = group3.clone();
		List<SynTreeIntegrableElementImpl> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, componentDescriptors);
	}
	
	@Override
	protected GroupX3 clone() throws CloneNotSupportedException {
		GroupX3 cloneGroupX3;
		Group cloneableGroup1 = group1.clone();
		Group cloneableGroup2 = group2.clone();
		Group cloneableGroup3 = group3.clone();
		try {
			cloneGroupX3 = new GroupX3(isCodingDescriptor, cloneableGroup1, cloneableGroup2, cloneableGroup3);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("GroupX3 : error in clone() method.");
		}
		return cloneGroupX3;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(group1, group2, group3));
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
