package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.GroupOS;
import model.copycatModel.ordSetGrammar.GroupXOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class GroupX extends HowManyGroups implements ISynTreeElement, Cloneable {

	private static final String DESCRIPTOR_PARTIAL_NAME = "groupX";
	private List<Group> listOfGroups;
	
	public GroupX(List<Group> listOfGroups) throws SynTreeGenerationException {
		super(false);
		if (listOfGroups.size() < 2 || listOfGroups.size() > Settings.MAX_NB_OF_GROUPS_IN_RELATIONS)
			throw new SynTreeGenerationException("GroupX() : illegal number of groups (" + listOfGroups.size() + ")");
		else {
			this.listOfGroups = listOfGroups;
			List<SynTreeElementImpl> synTreeComponents = new ArrayList<SynTreeElementImpl>();
			for (IElement component : getListOfComponents())
				synTreeComponents.add((SynTreeElementImpl) component);
			updateComponentsPosition(Settings.COMPONENT_AUTO_POSITIONING, synTreeComponents);	
		}
	}
	
	@Override
	protected HowManyGroups clone() throws CloneNotSupportedException {
		GroupX cloneGroupX;
		List<Group> cloneListOfGroups = new ArrayList<Group>();
		for (Group group : listOfGroups)
			cloneListOfGroups.add(group.clone());
		try {
			cloneGroupX = new GroupX(cloneListOfGroups);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("GroupX.clone() : " + e.getMessage());
		}
		return cloneGroupX;
	}	

	@Override
	protected List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = new ArrayList<IElement>();
		for (Group group : listOfGroups)
			listOfComponents.add(group);
		return listOfComponents;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			List<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			listOfRelevantPropertiesWithPath.addAll(listOfComponentRelevantPropertiesWithPath);
		}
		return listOfRelevantPropertiesWithPath;
	}	
	
	@Override
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>();
		listOfRelevantComponents.add(listOfGroups.get(0));
		return listOfRelevantComponents;
	}
	
	@Override
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement groupXOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer groupXIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String groupXID = getDescriptorName().concat(groupXIndex.toString());
		List<GroupOS> listOfGroupsOS = new ArrayList<GroupOS>();
		for (Group group : listOfGroups) {
			GroupOS groupOS = (GroupOS) group.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
			listOfGroupsOS.add(groupOS);
		}
		groupXOS = new GroupXOS(groupXID, listOfGroupsOS);
		return groupXOS;		
	}	

	@Override
	public String getDescriptorName() {
		String name = DESCRIPTOR_PARTIAL_NAME.concat(Integer.toString(listOfGroups.size()));
		return name;
	}

}
