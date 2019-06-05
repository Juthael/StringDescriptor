package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IFrameOS;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalOS;

public class FrameOS extends NonMinimalOS implements IOrderedSet, IFrameOS {

	private static final String NAME = "frame";
	private SizeOS size;
	private WhichPositionTypeOS positionType;
	private RelationsOrLetterOS relationsOrLetter;
	
	public FrameOS(String elementID, SizeOS size, WhichPositionTypeOS positionType, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;
	}

	public FrameOS(String elementID, boolean isCodingElement, SizeOS size, WhichPositionTypeOS positionType, 
			RelationsOrLetterOS relationsOrLetter) {
		super(elementID, isCodingElement);
		this.size = size;
		this.positionType = positionType;
		this.relationsOrLetter = relationsOrLetter;		
	}

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(size);
		listOfComponents.add(positionType);
		listOfComponents.add(relationsOrLetter);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}	
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (size.getElementID().equals(orderedSet.getElementID()) && size != orderedSet) {
			size = (SizeOS) orderedSet;
		}
		else size.eliminateRedundancies(orderedSet);
		if (positionType.getElementID().equals(orderedSet.getElementID()) && positionType != orderedSet) {
			positionType = (WhichPositionTypeOS) orderedSet;			
		}
		else positionType.eliminateRedundancies(orderedSet);
		if (relationsOrLetter.getElementID().equals(orderedSet.getElementID()) && relationsOrLetter != orderedSet) {
			relationsOrLetter = (RelationsOrLetterOS) orderedSet;			
		}
		else relationsOrLetter.eliminateRedundancies(orderedSet);
	}
}