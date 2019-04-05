package copycatModel.grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import copycatModel.impl.SynTreeIntegrableElementImpl;
import exceptions.SynTreeGenerationException;
import settings.Settings;

public class GroupX2 extends HowManyGroups implements Cloneable {
	
	private static final String descriptorName = "groupX2";
	private Group group1;
	private Group group2;

	public GroupX2(boolean codingDescriptor, Group group1, Group group2) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		super(codingDescriptor);
		this.group1 = group1.clone();
		this.group2 = group2.clone();
		List<SynTreeIntegrableElementImpl> componentDescriptors = buildListOfComponents();
		updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, componentDescriptors);
	}
	
	@Override
	protected GroupX2 clone() throws CloneNotSupportedException {
		GroupX2 cloneGroupX2;
		Group cloneGroup1 = group1.clone();
		Group cloneGroup2 = group2.clone();
		try {
			cloneGroupX2 = new GroupX2(isCodingDescriptor, cloneGroup1, cloneGroup2);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("GroupX2 : error in clone() method.");
		}		
		return cloneGroupX2;
	}

	@Override
	protected List<SynTreeIntegrableElementImpl> buildListOfComponents(){
		List<SynTreeIntegrableElementImpl> componentDescriptors = new ArrayList<SynTreeIntegrableElementImpl>(
				Arrays.asList(group1, group2));
		return componentDescriptors;
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
	protected List<SynTreeIntegrableElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeIntegrableElementImpl> listOfRelevantComponents = new ArrayList<SynTreeIntegrableElementImpl>();
		listOfRelevantComponents.add(group1);
		return listOfRelevantComponents;
	}	
	
	@Override
	public String getDescriptorName() {
		return descriptorName;
	}


}
