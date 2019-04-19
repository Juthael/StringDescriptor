package model.copycatModel.ordSetGrammar;

import java.util.List;

import model.generalModel.IElement;
import model.orderedSetModel.impl.NonMinimalRelevantLowerSetElement;

public class LetterOS extends NonMinimalRelevantLowerSetElement implements RelationsOrLetterOS {

	private static final String NAME = "letter";
	private IPositionOS position;
	private IPlatonicLetterOS platonicLetter;
	
	public LetterOS(String elementID, IPositionOS position, IPlatonicLetterOS platonicLetter) {
		super(elementID);
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
