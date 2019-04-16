package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.GroupOS;
import model.copycatModel.ordSetGrammar.PositionOS;
import model.copycatModel.ordSetGrammar.RelationsOrLetterOS;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.generalModel.IElement;
import model.orderedSetModel.ISetElement;
import model.synTreeModel.ISynTreeElement;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Group extends HowManyGroups implements Cloneable, ISynTreeElement {

	private static final String DESCRIPTOR_NAME = "group";
	private Size size;
	private Position position;
	private RelationsOrLetter relationsOrLetter;
	
	public Group(boolean codingDescriptor, Size size, Position position, RelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
		super(codingDescriptor);
		this.size = size;
		this.position = position;
		this.relationsOrLetter = relationsOrLetter;
	}
	
	@Override
	public Group clone()  throws CloneNotSupportedException {
		Size cloneSize = size.clone();
		Position clonePosition = position.clone();
		RelationsOrLetter cloneRelationsOrLetter;
		switch (relationsOrLetter.getDescriptorName()) {
			case "relations" : 
				Relations relationsCasted = (Relations) relationsOrLetter;
				cloneRelationsOrLetter = relationsCasted.clone();
				break;
			case "letter":
				Letter letterCasted = (Letter) relationsOrLetter;
				cloneRelationsOrLetter = letterCasted.clone();
				break;
			default : 
				throw new CloneNotSupportedException("Group : error in clone() method.");
		}
		Group cloneGroup;
		try {
			cloneGroup = new Group(isCodingByDecomposition, cloneSize, clonePosition, cloneRelationsOrLetter);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Group : error in clone() method.");
		}
		return cloneGroup;
	}

	@Override
	protected List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, position, relationsOrLetter));
		return componentDescriptors;
	}
	
	@Override
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}
	
	@Override
	public List<String> getListOfRelevantPropertiesWithPath(){
		List<String> listOfRelevantPropertiesWithPath = new ArrayList<String>();
		List<SynTreeElementImpl> listOfRelevantComponents = buildListOfRelevantComponentsForRelationBuilding();
		for (SynTreeElementImpl componentDescriptor : listOfRelevantComponents) {
			List<String> listOfComponentRelevantPropertiesWithPath = 
					componentDescriptor.getListOfRelevantPropertiesWithPath();
			for (String propertyWithPath : listOfComponentRelevantPropertiesWithPath){
				String propertyWithUpdatedPath = 
						this.getDescriptorName().concat(Settings.PATH_SEPARATOR + propertyWithPath);
				listOfRelevantPropertiesWithPath.add(propertyWithUpdatedPath);
			}
		}
		return listOfRelevantPropertiesWithPath;
	}
	
	@Override
	public ISetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ISetElement groupOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer groupIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String groupID = getDescriptorName().concat(groupIndex.toString());
		SizeOS sizeOS = (SizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		PositionOS positionOS = (PositionOS) position.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		RelationsOrLetterOS relationsOrLetterOS = 
				(RelationsOrLetterOS) relationsOrLetter.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		groupOS = new GroupOS(groupID, sizeOS, positionOS, relationsOrLetterOS);
		return groupOS;		
	}	

}
