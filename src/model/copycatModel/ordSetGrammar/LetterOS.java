package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.AbstractNonMinimalOS;

public class LetterOS extends AbstractNonMinimalOS implements RelationsOrLetterOS {

	private static final String NAME = "letter";
	private PositionOS position;
	private PlatonicLetterOS platonicLetter;
	
	public LetterOS(String elementID, PositionOS position, PlatonicLetterOS platonicLetter) {
		super(elementID);
		this.position = position;
		this.platonicLetter = platonicLetter;
	}
	
	public LetterOS(String elementID, boolean isCodingByDecomposition, PositionOS position, PlatonicLetterOS platonicLetter) {
		super(elementID, isCodingByDecomposition);
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

}
