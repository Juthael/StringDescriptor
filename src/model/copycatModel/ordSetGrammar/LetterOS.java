package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalRelevantSetElement;

public class LetterOS extends NonMinimalRelevantSetElement implements RelationsOrLetterOS {

	private static final String NAME = "letter";
	private PositionOS position;
	private PlatonicLetterOS platonicLetter;
	
	public LetterOS(String elementID, PositionOS position, PlatonicLetterOS platonicLetter) {
		super(elementID);
		this.position = position;
		this.platonicLetter = platonicLetter;
	}

	@Override
	protected List<IElement> getListOfComponents() {
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
