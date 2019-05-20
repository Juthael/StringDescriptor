package model.copycatModel.ordSetGrammar;

import java.util.List;
import java.util.Map;

import model.generalModel.IElement;
import model.orderedSetModel.impl.AbstractNonMinimalOS;
import model.orderedSetModel.IOrderedSet;

public class LetterOS extends AbstractNonMinimalOS implements RelationsOrLetterOS {

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
	public void eliminateRedundancies(Map<String, IOrderedSet> idToIOrderedSet) {
		super.eliminateRedundancies(idToIOrderedSet);
		if (!position.equals(idToIOrderedSet.get(position.getElementID())))
			position = (PositionOS) idToIOrderedSet.get(position.getElementID());
		position.eliminateRedundancies(idToIOrderedSet);
		if (!platonicLetter.equals(idToIOrderedSet.get(platonicLetter.getElementID())))
			platonicLetter = (PlatonicLetterOS) idToIOrderedSet.get(platonicLetter.getElementID());
		platonicLetter.eliminateRedundancies(idToIOrderedSet);
	}		

}
