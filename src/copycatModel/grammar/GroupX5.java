package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public class GroupX5 extends HowManyGroups implements Cloneable {

	private static final String descriptorName = "groupX5";
	private Group group1;
	private Group group2;
	private Group group3;
	private Group group4;
	private Group group5;
	
	public GroupX5(boolean codingDescriptor, Group group1, Group group2, Group group3, Group group4, Group group5) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		this.group3 = group3.clone();
		this.group4 = group4.clone();
		this.group5 = group5.clone();
		List<SynTreeIntegrableElementImpl> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, componentDescriptors);	
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
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("GroupX5 : error error in clone() method.");
		}
		return cloneGroupX5;
	}	

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(group1, group2, group3, group4, group5));
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
