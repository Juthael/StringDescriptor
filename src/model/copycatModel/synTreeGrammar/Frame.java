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
import model.synTreeModel.IFrame;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.GrammaticalST;
import model.synTreeModel.IPositionableST;
import settings.Settings;

public class Frame extends GrammaticalST implements IGrammaticalST, IOneOrManyFrames, IFrame, IPositionableST, Cloneable {

	private static final String DESCRIPTOR_NAME = "frame";
	private Size size;
	private WhichPositionType positionType;
	private IRelationsOrLetter relationsOrLetter;
	
	public Frame(Size size, WhichPositionType positionType, IRelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
		setHashCode();
	}
	
	public Frame(boolean codingElement, Size size, WhichPositionType positionType, IRelationsOrLetter relationsOrLetter) 
			throws SynTreeGenerationException {
		super(codingElement);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
		setHashCode();
	}
	
	@Override
	public Frame clone() throws CloneNotSupportedException {
		Size cloneSize = size.clone();
		WhichPositionType clonePosition = positionType.clone();
		IRelationsOrLetter cloneRelationsOrLetter;
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
	protected List<IGrammaticalST> buildListOfRelevantComponentsForRelationBuilding() {
		List<IGrammaticalST> listOfRelevantComponents = new ArrayList<IGrammaticalST>();
		listOfRelevantComponents.add(size);
		listOfRelevantComponents.add(relationsOrLetter);
		return listOfRelevantComponents;
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
	
	@Override
	public void updatePosition(String newPosition, List<IElement>componentDescriptors) throws SynTreeGenerationException {
		doUpdatePosition(newPosition);
		updateComponentsPosition(newPosition, componentDescriptors);
	}
	
	protected void doUpdatePosition(String newPositionValue) throws SynTreeGenerationException {
		if (positionType.getDescriptorName().equals("position") 
				&& !newPositionValue.equals(Settings.NO_POSITION_INFORMATION)) {
			Position position = (Position) positionType;
			if (position.getValue().equals(Settings.AWAITING_POSITION_VALUE)) {
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
	
	protected void updateComponentsPosition(String newPosition,	List<IElement> componentDescriptors) 
			throws SynTreeGenerationException {
		for (IElement componentDescriptor : componentDescriptors) {
			if (checkIfThisElementIfPositionable(componentDescriptor) == true) {
				List<IElement> listOfPositionnableSubComponents = new ArrayList<IElement>();
				for (IElement subComponent : componentDescriptor.getListOfComponents()) {
					boolean thisSubComponentIsPositionable = checkIfThisElementIfPositionable(subComponent);
					if (thisSubComponentIsPositionable == true)
						listOfPositionnableSubComponents.add((IPositionableST) subComponent);
				}
				IPositionableST synTreeComponent = (IPositionableST) componentDescriptor;
				synTreeComponent.updatePosition(newPosition, listOfPositionnableSubComponents);
			}
		}
	}	
	
	protected void updateComponentsPosition(int autoPosition, List<IPositionableST> componentDescriptors) 
			throws SynTreeGenerationException {
		if (autoPosition == Settings.COMPONENT_AUTO_POSITIONING) {
			int positionIndex = 1;
			StringBuilder sB;
			for (IPositionableST componentDescriptor : componentDescriptors) {
				if (checkIfThisElementIfPositionable(componentDescriptor) == true) {
					sB = new StringBuilder();
					String positionValue = Integer.toString(positionIndex);
					String specialPositionValue = getSpecialPositionValue(positionIndex, componentDescriptors.size());
					sB.append(positionValue);
					if (!specialPositionValue.isEmpty()) {
						sB.append(Settings.POSITION_VALUES_SEPARATOR);
						sB.append(specialPositionValue);	
					}
					List<IElement> listOfPositionnableSubComponents = new ArrayList<IElement>();
					for (IElement subComponent : componentDescriptor.getListOfComponents()) {
						boolean thisSubComponentIsPositionnable = checkIfThisElementIfPositionable(subComponent);
						if (thisSubComponentIsPositionnable == true)
							listOfPositionnableSubComponents.add((IPositionableST) subComponent);
					}
					componentDescriptor.updatePosition(sB.toString(), listOfPositionnableSubComponents);
					positionIndex++;
				}
			}
		} else throw new SynTreeGenerationException(
				"AbstractDescriptor.updateComponents() : illegal constant value.");
	}		
	
	private String getSpecialPositionValue(int positionIndex, int nbOfComponentDescriptors) {
		String specialPositionValue = "";
		if (nbOfComponentDescriptors > 1) {
			if (positionIndex == 1)
				specialPositionValue = Settings.FIRST_POSITION;
			else if (positionIndex == nbOfComponentDescriptors) {
				specialPositionValue = Settings.LAST_POSITION;
			}
			else if (nbOfComponentDescriptors % 2 == 1) {
				int halfIntegerPart = (int) nbOfComponentDescriptors/2;
				if (positionIndex == (halfIntegerPart + 1))
					specialPositionValue = Settings.CENTRAL_POSITION;
			}
		}
		return specialPositionValue;
	}	
	
	private boolean checkIfThisElementIfPositionable(IElement element) {
		boolean thisElementIsPositionnable = false;
		Class<?> subComponentClass = element.getClass();
		Class<?>[] subComponentInterfaceClasses = subComponentClass.getInterfaces();
		for (Class<?> interfaceClass : subComponentInterfaceClasses) {
			if (interfaceClass == IPositionableST.class)
				thisElementIsPositionnable = true;
		}
		return thisElementIsPositionnable;
	}	

}
