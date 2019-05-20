package model.copycatModel.synTreeGrammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exceptions.OrderedSetsGenerationException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.ordSetGrammar.FrameOS;
import model.copycatModel.ordSetGrammar.RelationsOrLetterOS;
import model.copycatModel.ordSetGrammar.SizeOS;
import model.copycatModel.ordSetGrammar.WhichPositionTypeOS;
import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.synTreeModel.ISynTreeElementWithPosition;
import model.synTreeModel.impl.SynTreeElementImpl;
import settings.Settings;

public class Frame extends HowManyFrames implements ISynTreeElementWithPosition, Cloneable {

	private static final String DESCRIPTOR_NAME = "frame";
	private Size size;
	private WhichPositionType positionType;
	private RelationsOrLetter relationsOrLetter;
	
	public Frame(Size size, WhichPositionType positionType, RelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
	}
	
	public Frame(boolean codingElement, Size size, WhichPositionType positionType, RelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
		super(codingElement);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
	}
	
	@Override
	public Frame clone()  throws CloneNotSupportedException {
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
				throw new CloneNotSupportedException("Frame : error in clone() method.");
		}
		Frame cloneFrame;
		try {
			cloneFrame = new Frame(isCodingElement, cloneSize, clonePosition, cloneRelationsOrLetter);
		} catch (SynTreeGenerationException e) {
			throw new CloneNotSupportedException("Frame : error in clone() method.");
		}
		return cloneFrame;
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
	
	public void setLetterOrRelationsComponentsAsCodingDescriptor() {
		if (relationsOrLetter.getDescriptorName().equals("letter"))
				relationsOrLetter.setIsCodingElement(Settings.THIS_IS_A_CODING_ELEMENT);
		else if (relationsOrLetter.getDescriptorName().equals("relations")) {
			Relations relations = (Relations) relationsOrLetter;
			relations.setComponentsAsCodingDescriptors();
		}
	}
	
	@Override
	public IOrderedSet upgradeAsTheElementOfAnOrderedSet(Map<List<String>, Integer> listOfPropertiesToIndex) 
			throws OrderedSetsGenerationException {
		IOrderedSet frameOS;
		List<String> listOfPropertiesWithPath = getListOfPropertiesWithPath();
		Integer frameIndex = listOfPropertiesToIndex.get(listOfPropertiesWithPath);
		String frameID = getDescriptorName().concat(frameIndex.toString());
		SizeOS sizeOS = (SizeOS) size.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		WhichPositionTypeOS positionOS = 
				(WhichPositionTypeOS) positionType.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		RelationsOrLetterOS relationsOrLetterOS = 
				(RelationsOrLetterOS) relationsOrLetter.upgradeAsTheElementOfAnOrderedSet(listOfPropertiesToIndex);
		frameOS = new FrameOS(frameID, isCodingElement, sizeOS, positionOS, relationsOrLetterOS);
		return frameOS;		
	}

}
