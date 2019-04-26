package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.GroupsOS;
import model.copycatModel.ordSetGrammar.HowManyGroupsOS;
import model.copycatModel.ordSetGrammar.ISizeOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementWithPositionImpl;
import settings.Settings;

public class Groups extends SynTreeElementWithPositionImpl implements ISynTreeElementWithPosition, Cloneable {

	private static final String DESCRIPTOR_NAME = "groups";
	private Size size;
	private HowManyGroups groupHM;
	
	public Groups(Size size, HowManyGroups groupHM) 
			throws SynTreeGenerationException, CloneNotSupportedException {
		this.size = size;
		if (groupHM.getDescriptorName().equals("group")) {
			this.groupHM = (Group) groupHM;
			List<IElement> listWithSingleGroup = new ArrayList<IElement>(); 
			listWithSingleGroup.add(this.groupHM);
			updateComponentsPosition("1", listWithSingleGroup);
		}
		else this.groupHM = groupHM;
	}
	
	public Groups(Size size, HowManyGroups groupHM, boolean fullStringGroup) 
			throws SynTreeGenerationException {
		if (fullStringGroup == Settings.FULL_STRING_GROUP && groupHM.getDescriptorName().equals("group")){
			this.size = size;
			this.groupHM = groupHM;
			if (groupHM.getDescriptorName().equals("group")) {
				this.groupHM = (Group) groupHM;
				List<IElement> listWithSingleGroup = new ArrayList<IElement>(); 
				listWithSingleGroup.add(this.groupHM);
				updateComponentsPosition(Settings.CONVENTIONAL_POSITION_FOR_FULL_STRING_GROUP, listWithSingleGroup);
			}
			else {
				this.groupHM = (GroupX) groupHM;
				updateComponentsPosition(
						Settings.CONVENTIONAL_POSITION_FOR_FULL_STRING_GROUP, this.groupHM.getListOfComponents());
			}
				
		} else throw new SynTreeGenerationException("Groups : illegal parameter values in constructor");
	}
	
	@Override
	protected Groups clone() throws CloneNotSupportedException {
		Groups cloneGroups;
		Size cloneSize = size.clone();
		HowManyGroups cloneGroupHM = groupHM.clone();
		try {
			cloneGroups = new Groups(cloneSize, cloneGroupHM);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Groups : error in clone() method");
		}
		return cloneGroups;
	}

	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, groupHM));
		return componentDescriptors;
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		IOrderedSet groupsOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer groupsIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String groupsID = getDescriptorName().concat(groupsIndex.toString());
		ISizeOS sizeOS = (ISizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		HowManyGroupsOS groupHMOS = (HowManyGroupsOS) groupHM.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		groupsOS = new GroupsOS(groupsID, sizeOS, groupHMOS);
		return groupsOS;		
	}	

}
