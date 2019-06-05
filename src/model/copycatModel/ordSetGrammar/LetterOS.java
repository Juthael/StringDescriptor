package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.IOrderedSet;
import model.orderedSetModel.impl.NonMinimalOS;

public class LetterOS extends NonMinimalOS implements RelationsOrLetterOS {

	private static final String NAME = "letter";
	private PositionOS position;
	private PlatonicLetterOS platonicLetter;
	
	public LetterOS(String elementID, PositionOS position, PlatonicLetterOS platonicLetter) {
		super(elementID);
		this.position = position;
		this.platonicLetter = platonicLetter;
	}
	
	public LetterOS(String elementID, boolean isCodingElement, PositionOS position, PlatonicLetterOS platonicLetter) {
		super(elementID, isCodingElement);
		this.position = position;
		this.platonicLetter = platonicLetter;
	}	

	@Override
	public List<IElement> getListOfComponents() {
		List<IElement> listOfComponents = super.getListOfComponents();
		listOfComponents.add(position);
		listOfComponents.add(platonicLetter);
		return listOfComponents;
	}

	@Override
	public String getDescriptorName() {
		return NAME;
	}
	
	@Override
	public void eliminateRedundancies(IOrderedSet orderedSet) {
		super.eliminateRedundancies(orderedSet);
		if (position.getElementID().equals(orderedSet.getElementID()) && position != orderedSet) {
			position = (PositionOS) orderedSet;			
		}
		else position.eliminateRedundancies(orderedSet);
		if (platonicLetter.getElementID().equals(orderedSet.getElementID()) && platonicLetter != orderedSet) {
			platonicLetter = (PlatonicLetterOS) orderedSet;			
		}
		else platonicLetter.eliminateRedundancies(orderedSet);
	}		

}
