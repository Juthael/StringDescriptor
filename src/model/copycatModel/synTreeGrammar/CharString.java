package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.copycatModel.ordSetGrammar.CharStringOS;
import model.copycatModel.ordSetGrammar.GroupsOS;
import model.copycatModel.ordSetGrammar.IDirectionOS;
import model.copycatModel.ordSetGrammar.StructureOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;

public class CharString extends SynTreeElementImpl implements ISynTreeElement, Cloneable {
	
	private final static String DESCRIPTOR_NAME = "charString";
	protected Direction direction;
	protected Structure structure;
	protected Groups groups;

	public CharString(Direction direction, Structure structure, Groups groups) {
		this.direction = direction;
		this.structure = structure;
		this.groups = groups;
	}
	
	@Override 
	protected CharString clone() throws CloneNotSupportedException {
		Direction cloneDirection = direction.clone();
		Structure cloneStructuration = structure.clone();
		Groups cloneGroups = groups.clone();
		CharString cloneCharString = new CharString(cloneDirection, cloneStructuration, cloneGroups);
		return cloneCharString;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	
	
	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(direction, structure, groups));
		return componentDescriptors;
	}

	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement charStringOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer charStringIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String charStringID = getDescriptorName().concat(charStringIndex.toString());
		IDirectionOS directionOS = (IDirectionOS) direction.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		StructureOS structureOS = (StructureOS) structure.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		GroupsOS groupsOS = (GroupsOS) groups.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		charStringOS = new CharStringOS(charStringID, directionOS, structureOS, groupsOS);
		return charStringOS;
	}	
	
}
