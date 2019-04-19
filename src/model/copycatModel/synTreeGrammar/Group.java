package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.GroupOS;
import model.copycatModel.ordSetGrammar.ISizeOS;
import model.copycatModel.ordSetGrammar.RelationsOrLetterOS;
import model.copycatModel.ordSetGrammar.WhichPositionTypeOS;
import model.generalModel.IElement;
import model.orderedSetModel.ILowerSetElement;
import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Group extends HowManyGroups implements ISynTreeElementWithPosition, Cloneable {

	private static final String DESCRIPTOR_NAME = "group";
	private Size size;
	private WhichPositionType positionType;
	private RelationsOrLetter relationsOrLetter;
	
	public Group(boolean codingDescriptor, Size size, WhichPositionType positionType, RelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
		super(codingDescriptor);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
	}
	
	@Override
	public Group clone()  throws CloneNotSupportedException {
		Size cloneSize = size.clone();
		WhichPositionType clonePosition = positionType.clone();
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
	public String getDescriptorName() {
		return DESCRIPTOR_NAME;
	}	

	@Override
	public List<IElement> getListOfComponents(){
		List<IElement> componentDescriptors = new ArrayList<IElement>(
				Arrays.asList(size, positionType, relationsOrLetter));
		return componentDescriptors;
	}	
	
	@Override
	protected List<SynTreeElementImpl> buildListOfRelevantComponentsForRelationBuilding() {
		List<SynTreeElementImpl> listOfRelevantComponents = new ArrayList<SynTreeElementImpl>();
		listOfRelevantComponents.add(size);
		listOfRelevantComponents.add(relationsOrLetter);
		return listOfRelevantComponents;
	}	
	
	@Override
	protected void doUpdatePosition(String newPositionValue) throws SynTreeGenerationException {
		if (positionType.getDescriptorName().equals("position") 
				&& !newPositionValue.equals(Settings.NO_POSITION_INFORMATION)) {
			Position position = (Position) positionType;
			if (position.getPositionValue().equals(Settings.AWAITING_POSITION_VALUE)) {
				if (newPositionValue.contains(Settings.POSITION_VALUES_SEPARATOR)) {
					String[] newPositionValueArray = newPositionValue.split(Settings.POSITION_VALUES_SEPARATOR);
					position.doUpdatePosition(newPositionValueArray[0]);
					if (newPositionValueArray[1].equals(Settings.CENTRAL_POSITION)) {
						positionType = new CentralProminentPosition(position);
					}
					else if (newPositionValueArray[1].equals(Settings.FIRST_POSITION) 
							|| newPositionValueArray[1].equals(Settings.LAST_POSITION)) {
						EndPosition endPosition = new EndPosition(newPositionValueArray[1]);
						EndProminentPosition endProminentPosition = new EndProminentPosition(position, endPosition);
						positionType = endProminentPosition;
					}
				}
				else {
					position.doUpdatePosition(newPositionValue);
					positionType = position;
				}
			}
		}
	}
	
	@Override
	public ILowerSetElement upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) {
		ILowerSetElement groupOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer groupIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String groupID = getDescriptorName().concat(groupIndex.toString());
		ISizeOS sizeOS = (ISizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		WhichPositionTypeOS positionOS = 
				(WhichPositionTypeOS) positionType.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		RelationsOrLetterOS relationsOrLetterOS = 
				(RelationsOrLetterOS) relationsOrLetter.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		groupOS = new GroupOS(groupID, sizeOS, positionOS, relationsOrLetterOS);
		return groupOS;		
	}

}
